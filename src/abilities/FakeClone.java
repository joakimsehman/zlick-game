package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import entities.Minion;
import entities.Player;
import game.Model;

public class FakeClone extends Ability{

	private int duration;
	
	public FakeClone(String name, int playerCreatedId) {
		super(name, 7, playerCreatedId);
		duration = 4000;
	}
	
	public void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId){
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		Player usingPlayer = Model.model.getPlayer(id);
		
		
		Player playerClone = new Player(usingPlayer.getXPos(), usingPlayer.getYPos(), usingPlayer.getVector().copy(), new Rectangle(
				usingPlayer.getXPos(), usingPlayer.getYPos(), 50, 50), 100, usingPlayer.getName(), 1000+usingPlayer.getID());
		
		playerClone.setCustomization(usingPlayer.getGender(), usingPlayer.getClothes(), usingPlayer.getHair(), usingPlayer.getWeapon());
		playerClone.setTeam(usingPlayer.getTeam());
		playerClone.setHealthPoints(usingPlayer.getHealthPoints());
		
		
		usingPlayer.applyInvis(duration);
		Model.model.addTemporaryMinion(playerClone, duration);
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public int getSpellEffectAmount() {
		// TODO Auto-generated method stub
		return 0;
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
		return 20000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		// TODO Auto-generated method stub
		return true;
	}

}
