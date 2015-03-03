package abilities;

import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import entities.AttachedAnimatedDecoration;
import entities.FireballEffect;
import entities.IceLanceEffect;
import entities.Player;
import game.Model;

public class IceLance extends Ability {

	private int duration;
	
	public IceLance(String name, int duration, int playerCreatedId) {
		super(name, 4, playerCreatedId);
		this.duration = duration;
	}

	
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		Player usingPlayer = Model.model.getPlayer(id);
		
		float angle = (float) Math.toDegrees(Math.atan2(mouseGameY - usingPlayer.getYPos(),mouseGameX - usingPlayer.getXPos()));
		
		IceLanceEffect spell = new IceLanceEffect(usingPlayer.getXPos(), usingPlayer.getYPos(), new Vector2f(0,0), duration, id, spellEffectId[0]);
		spell.setVectorByDegree(100, angle);
		
		Model.model.addActiveSpell(spell);
		
	}
	
	
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getSpellEffectAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getCastTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCastableWhileMoving() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getCooldown() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		// TODO Auto-generated method stub
		return true;
	}

}
