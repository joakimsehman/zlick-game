package entities;

import java.util.ArrayList;

import game.Model;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class SpellAreaOfEffect extends Entity {

	
	//some of these are never initialized and used, remember if using something new
	private int movementSpeedModifier;
	private int damage;
	private int shield;
	private int damageReduction;
	private int buffDuration;
	private int spellDuration;
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
	public void update(int delta, ArrayList<Player> entities, Player myself) {
		super.update(delta, null);
		durationLeft = durationLeft - delta;
		if (entities != null) {
			if (disappearsIfTouched) {
				if (Model.model.isServer()) {
					if (this.getBoundingBox().intersects(
							myself.getBoundingBox())) {
						if(Model.model.getPlayer(getPlayerUsedId()).getTeam() != Model.model.getMyself().getTeam()){
							applyEffect(myself);
							Model.model.sendSpellHitReport(combinedId,
									Model.model.getMyself().getID());
						}
					}
					for (Player p : entities) {
						if (this.getBoundingBox()
								.intersects(p.getBoundingBox())) {
							if (Model.model.getPlayer(getPlayerUsedId())
									.getTeam() != p.getTeam()) {
								applyEffect(p);
								Model.model.sendSpellHitReport(combinedId,
										p.getID());
							}
						}
					}
				}
			} else {
				for (Player p : entities) {
					if (this.getBoundingBox().intersects(p.getBoundingBox())) {
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

	public void onTic(int delta, Player player) {

	}

	public void onExpire() {

	}
}
