package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;

import utilities.TextureHandler;
import buffs.Polymorph;

public class PolymorphingEffect extends SpellAreaOfEffect {

	private Polymorph polymorph;

	//change to be actual size of new image
	public PolymorphingEffect(float xPos, float yPos, Vector2f vector,
			int duration, int playerId,
			int spellEffectId) {
		super(xPos-130, yPos-60, vector, new Ellipse(xPos, yPos, 130, 60), TextureHandler
				.getInstance().getImageByName("spell_circle.png"), duration,
				false, playerId, spellEffectId);
		polymorph = new Polymorph(3000);
	}

	protected void onTic(int delta, Player player) {
		player.addBuff(polymorph);
	}
	
	

	public static int getEffectId() {
		return 1;
	}

}
