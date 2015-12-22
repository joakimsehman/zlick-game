package abilities;

import entities.BolaEffect;
import entities.Player;
import entities.SoulReleaseEffect;
import game.Model;

import org.newdawn.slick.Image;

/**
 * Created by joakim on 2015-01-31.
 */
public class SoulRelease extends Ability {

	private int cooldown;

	public SoulRelease(String name, int duration, int playerCreatedId) {
		super(name, playerCreatedId);

		cooldown = 0;

	}

	public void useAbility(int id, float mouseGameX, float mouseGameY,
			int[] spellEffectId) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		if (Model.model.getMyself().getID() == getPlayerCreatedId()) {
			Player usingPlayer = Model.model.getPlayer(id);

			float angle = (float) Math
					.toDegrees(Math.atan2(mouseGameY - usingPlayer.getYPos(),
							mouseGameX - usingPlayer.getXPos()));

			// SoulReleaseEffect spell = new SoulReleaseEffect(angle, angle,
			// null, null, id, id)
		}
	}

	// du kan använda getPlayerCreatedId() för att komma åt usingPlayer
	public int getCost() {
		return (int) Model.model.getPlayer(getPlayerCreatedId()).getEnergy() / 2;
	}

	public int getSpellEffectAmount() {
		return 1;
	}

	public int getCastTime() {
		return 0;

	}

	public boolean isCastableWhileMoving() {
		return true;
	}

	@Override
	public int getCooldown() {
		// TODO Auto-generated method stub
		return cooldown;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getId() {
		return 6;
	}

	

}
