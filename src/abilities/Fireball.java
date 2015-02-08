package abilities;

import entities.FireballEffect;
import entities.Player;
import entities.SpellAreaOfEffect;
import game.Model;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import utilities.TextureHandler;

public class Fireball extends Ability{

	
	private int duration;
	
	
	public Fireball(String name, int duration, int playerCreatedId) {
		super(name, 0, playerCreatedId);
		this.duration = duration;
		
	}
	
	

	

	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		Player usingPlayer = Model.model.getPlayer(id);
		
		float angle = (float) Math.toDegrees(Math.atan2(mouseGameY - usingPlayer.getYPos(),mouseGameX - usingPlayer.getXPos()));
		
		FireballEffect spell = new FireballEffect(usingPlayer.getXPos(), usingPlayer.getYPos(), new Vector2f(0,0), duration, id, spellEffectId[0]);
		spell.setVectorByDegree(80, angle);
		
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
		return 500;
	}



	public boolean isCastableWhileMoving() {
		return true;
	}





	@Override
	public int getCooldown() {
		return 1000;
	}

}
