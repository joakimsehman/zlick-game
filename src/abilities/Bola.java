package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import entities.BolaEffect;
import entities.Player;
import game.Model;
import utilities.TextureHandler;

public class Bola extends Ability{

	private int duration;
	
	public Bola(String name, int duration) {
		super(name, 2, TextureHandler.getInstance().getImageByName("bolaIcon.png"));
		this.duration = duration;
	}

	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY,
			int[] spellEffectId) {
		Player usingPlayer = Model.model.getPlayer(id);
		
		float angle = (float) Math.toDegrees(Math.atan2(mouseGameY - usingPlayer.getYPos(),mouseGameX - usingPlayer.getXPos()));
		
		BolaEffect spell = new BolaEffect(usingPlayer.getXPos(), usingPlayer.getYPos(), new Vector2f(0,0), duration, id, spellEffectId[0]);
		spell.setVectorByDegree(100, angle);
		
		Model.model.addActiveSpell(spell);
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 20;
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

}
