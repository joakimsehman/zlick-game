package abilities;

import entities.Player;
import entities.SpellAreaOfEffect;
import game.Model;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import utilities.TextureHandler;

public class SimpleDamageAbility extends Ability{

	
	private int duration;
	
	
	public SimpleDamageAbility(String name, int duration) {
		super(name, 0);
		this.duration = duration;
		
	}
	
	

	

	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY) {
		Player usingPlayer = Model.model.getPlayer(id);
		
		float angle = (float) Math.toDegrees(Math.atan2(usingPlayer.getYPos() - mouseGameY ,mouseGameX - usingPlayer.getXPos()  ));
		
		SpellAreaOfEffect spell = new SpellAreaOfEffect(usingPlayer.getXPos(), usingPlayer.getYPos(), new Vector2f(0,0), new Circle(usingPlayer.getXPos() + 25,usingPlayer.getYPos() + 25, 10) , TextureHandler.getInstance().getImageByName("fireball.png"), duration, true);
		spell.setVectorByDegree(80, angle);
		
		Model.model.addActiveSpell(spell);
		
	}

	@Override
	public int getCost() {
		return 10;
	}

}
