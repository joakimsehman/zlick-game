package entities;

import game.Model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;

import utilities.SoundHandler;
import utilities.TextureHandler;
import buffs.Polymorph;

public class PolymorphingEffect extends SpellAreaOfEffect {


	//change to be actual size of new image
	public PolymorphingEffect(float xPos, float yPos, Vector2f vector,
			int duration, int playerId,
			int spellEffectId) {
		super(xPos-130, yPos-60, vector, new Ellipse(xPos, yPos, 130, 60), TextureHandler
				.getInstance().getImageByName("spell_circle.png"), duration,
				false, playerId, spellEffectId);
		if(Model.model.isOnScreen(xPos, yPos)){
			SoundHandler.getInstance().spellCircleSound.play(1.0f, 0.1f);
		}
	}

	protected void onTic(int delta, Player player) {
		player.addBuff(new Polymorph(3000));
	}
	
//	public void draw(Graphics g, float cameraX, float cameraY) {
//		super.draw(g, cameraX, cameraY);
//		g.drawOval(getBoundingBox().getMinX() - cameraX, getBoundingBox().getMinY() - cameraY, getBoundingBox().getWidth(), getBoundingBox().getHeight());
//	}

	public static int getEffectId() {
		return 1;
	}
	
	protected void setBoundingBox(float xPos, float yPos){
		this.getBoundingBox().setCenterX(getXPos() + 130);
		this.getBoundingBox().setCenterY(getYPos() + 60);
	}

}
