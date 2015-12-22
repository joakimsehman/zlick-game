package abilities;

import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import entities.AttachedAnimatedDecoration;
import entities.BolaEffect;
import entities.FireballEffect;
import entities.IceLanceEffect;
import entities.Player;
import game.Model;

public class IceLance extends Ability {

	private int duration;
	
	public IceLance(String name, int duration, int playerCreatedId) {
		super(name, playerCreatedId);
		this.duration = duration;
	}

	
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		Player usingPlayer = Model.model.getPlayer(id);
		
		float angle = (float) Math.toDegrees(Math.atan2(mouseGameY - usingPlayer.getYPos(),mouseGameX - usingPlayer.getXPos()));
		
		IceLanceEffect spell = new IceLanceEffect(usingPlayer.getXPos(), usingPlayer.getYPos(), new Vector2f(0,0), duration, getPlayerCreatedId(), spellEffectId[0]);
		spell.setVectorByDegree(100, angle);
		
		Model.model.addActiveSpell(spell);
		
	}
	
	
	@Override
	public int getCost() {
		
		return 10;
	}

	@Override
	public int getSpellEffectAmount() {
		
		return 1;
	}

	@Override
	public int getCastTime() {
		
		return 0;
	}

	@Override
	public boolean isCastableWhileMoving() {
		
		return true;
	}

	@Override
	public int getCooldown() {
		
		return 500;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		
		return true;
	}


	@Override
	public int getId() {
		return 4;
	}


	

}
