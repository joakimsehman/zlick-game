
package entities;

import java.util.ArrayList;

import game.Model;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;




/*(tutorial start in abilities.Ability.java)
 * 
 * 
 * 
 * HOW TO CREATE AN ABILITY TUTORIAL:
 * 
 * 
 * PART 3:
 * 
 * This abstract class is an extension of entity, it is an entity on the game field
 * It represents a spell effect with an area (Example: Could be a single fireball or a healing area)
 * it is extended to build spell functionality
 * 
 * note that the constructor takes boolean argument disappearsIfTouched
 * if disappearsIfTouched is set to true the applyEffect method will be called on player hit and then the effect will delete itself
 * if disappearsIfTouched is set to false, instead the onTic method will be called each update for each player affected
 * 
 * 
 * Check out this class quickly and continue to entities.FireballEffect.java for part 4
 */



//Ex
//dont forget to overwrite getEffectId() if you extend this class
public abstract class SpellAreaOfEffect extends Entity {

	
	//some of these are never initialized and used, remember if using something new
	private boolean disappearsIfTouched;
	private int durationLeft;
	private int combinedId;

	public SpellAreaOfEffect(float xPos, float yPos, Vector2f vector,
			Shape boundingBox, Image image, int duration,
			boolean disappearsIfTouched, int playerId, int spellEffectId) {
		super(xPos, yPos, vector, boundingBox, image);
		this.disappearsIfTouched = disappearsIfTouched;
		this.durationLeft = duration;
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

	//override this if your ability disappearsIfTouched
	public void applyEffect(Player player) {
	}

	//override this if your ability does not disappearsIfTouched
	protected void onTic(int delta, Player player) {
	}
	
	private void onHit(Player p){
		applyEffect(p);
		durationLeft = -1;
		Model.model.sendSpellHitReport(combinedId,
				p.getID());
	}

	//override this to do stuff on expire
	public void onExpire() {

	}
	
	public static int getEffectId(){
		return 0;
	}
	
	public void setDurationLeft(int duration){
		this.durationLeft = duration;
	}
	
}
