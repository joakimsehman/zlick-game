package abilities;

import entities.Player;
import entities.SpellAreaOfEffect;
import game.Model;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import utilities.TextureHandler;

public class SimpleDamageAbility extends Ability{

	
	
	
	
	public SimpleDamageAbility(String name) {
		super(name);
		
	}

	

	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY) {
		Player usingPlayer = Model.model.getPlayer(id);
		System.out.println("ability used");
		float angle = (float) Math.toDegrees(Math.atan2(usingPlayer.getYPos() - mouseGameY ,mouseGameX - usingPlayer.getXPos()  ));
		
		SpellAreaOfEffect spell = new SpellAreaOfEffect(usingPlayer.getXPos(), usingPlayer.getYPos(), new Vector2f(0,0), new Circle(usingPlayer.getXPos(),usingPlayer.getYPos(), 10) , TextureHandler.getInstance().getImageByName("fireball.png"), true);
		spell.setVectorByDegree(60, angle);
		
		Model.model.addActiveSpell(spell);
		
	}

}
