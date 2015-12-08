package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class SoulReleaseEffect extends SpellAreaOfEffect{

	public SoulReleaseEffect(float xPos, float yPos, Vector2f vector, Circle circle,
		int playerId, int spellEffectId){
		super(xPos, yPos, vector, circle,null,0,true, playerId, spellEffectId);
	}
	
	
	
	public static int getEffectId(){
		return 5;
	}
	
}
