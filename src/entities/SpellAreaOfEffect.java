
package entities;

import java.util.ArrayList;

import game.Model;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;


//dont forget to overwrite getEffectId() if you extend this class
public class SpellAreaOfEffect extends Entity {

	
	//some of these are never initialized and used, remember if using something new
	private int damage;
	private boolean disappearsIfTouched;
	private int durationLeft;
	private int combinedId;

	public SpellAreaOfEffect(float xPos, float yPos, Vector2f vector,
			Shape boundingBox, Image image, int duration,
			boolean disappearsIfTouched, int playerId, int spellEffectId) {
		super(xPos, yPos, vector, boundingBox, image);
		this.disappearsIfTouched = disappearsIfTouched;
		this.durationLeft = duration;
		damage = 10;
		combinedId = playerId * 1000000 + spellEffectId;
	}

	// if(dissappearsIfTouched == false) {damage is damage/sec}
	//either onHit will be called or onTic, never both
	public void update(int delta, ArrayList<Player> entities, Player myself) {
		super.update(delta, null, false);
		durationLeft = durationLeft - delta;
		if (entities != null) {
			
			if (disappearsIfTouched) {
				//server handles all collision for these kinds of spells it seems
				if (Model.model.isServer()) {
					if (this.getBoundingBox().intersects(
							myself.getBoundingBox())) {
						if(Model.model.getPlayer(getPlayerUsedId()).getTeam() != Model.model.getMyself().getTeam()){
							onHit(myself);
						}
					}
					for (Player p : entities) {
						if (this.getBoundingBox()
								.intersects(p.getBoundingBox())) {
							if (Model.model.getPlayer(getPlayerUsedId())
									.getTeam() != p.getTeam()) {
								onHit(p);
							}
						}
					}
				}
			} else {
				//here each client is responsible for applying the spell themself
				if(Model.model.getPlayer(getPlayerUsedId()).getTeam() != myself.getTeam() && this.getBoundingBox().intersects(myself.getBoundingBox())){
					onTic(delta, myself);
				}
				for (Player p : entities) {
					
					if (Model.model.getPlayer(getPlayerUsedId()).getTeam() != p.getTeam() && this.getBoundingBox().intersects(p.getBoundingBox())) {
						onTic(delta, p);
					}
				}
			}
		}
	}

	public final int getCombinedId() {
		return combinedId;
	}

	public final int getPlayerUsedId() {
		return combinedId / 1000000;
	}

	public final int getSpellEffectId() {
		return combinedId % 1000000;
	}

	public int getDurationLeft() {
		return durationLeft;
	}

	public void applyEffect(Player player) {
		player.applyDamageModifyer(-damage);
		durationLeft = -1;
	}

	protected void onTic(int delta, Player player) {
	}
	
	private void onHit(Player p){
		applyEffect(p);
		Model.model.sendSpellHitReport(combinedId,
				p.getID());
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}

	public void onExpire() {

	}
	
	public static int getEffectId(){
		return 0;
	}
}
