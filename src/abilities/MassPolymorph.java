package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import entities.Player;
import entities.PolymorphingEffect;
import entities.SpellAreaOfEffect;
import game.Model;
import utilities.TextureHandler;

public class MassPolymorph extends Ability{

	private int duration;
	
	public MassPolymorph(String name, int duration, int playerCreatedId) {
		super(name, 1, playerCreatedId);
		this.duration = duration;
	}

	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY,
			int[] spellEffectId) {
		
		
		// -140 -66
		PolymorphingEffect spell = new PolymorphingEffect(mouseGameX, mouseGameY, new Vector2f(0,0), duration, id, spellEffectId[0]);
	
		Model.model.addActiveSpell(spell);
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public int getSpellEffectAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getCastTime() {
		return 1000;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return false;
	}

	@Override
	public int getCooldown() {
		return 0;
	}

	
	
}
