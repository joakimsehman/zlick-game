package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import buffs.Polymorph;

public class PolymorphingEffect extends SpellAreaOfEffect{

	private Polymorph polymorph;
	
	public PolymorphingEffect(float xPos, float yPos, Vector2f vector,
			Shape boundingBox, Image image, int duration,
			boolean disappearsIfTouched, int playerId, int spellEffectId) {
		super(xPos, yPos, vector, boundingBox, image, duration, disappearsIfTouched,
				playerId, spellEffectId);
		polymorph = new Polymorph(3000);
	}
	
	protected void onTic(int delta, Player player){
		player.addBuff(polymorph);
	}

}
