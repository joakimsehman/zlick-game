package entities;

import game.Model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import utilities.SoundHandler;
import utilities.TextureHandler;

public class FireballEffect extends SpellAreaOfEffect{

	private int damage;
	
	public FireballEffect(float xPos, float yPos, Vector2f vector, int duration,
			int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos, yPos, 10), TextureHandler.getInstance().getImageByName("fireball.png"), duration, true,
				playerId, spellEffectId);
		damage = 10;
		if(Model.model.isOnScreen(xPos, yPos)){
			SoundHandler.getInstance().fireballSound.play(1.0f, 0.3f);
		}
	}
	
	public void applyEffect(Player player){
		super.applyEffect(player);
		player.applyDamageModifyer(-damage);
	}
	
	public static int getEffectId(){
		return 3;
	}

}
