package entities;

import game.Model;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import utilities.TextureHandler;

public class BolaEffect extends SpellAreaOfEffect {

	private Image currentImage;
	private int playerId;

	public BolaEffect(float xPos, float yPos, Vector2f vector, int duration,
			int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos, yPos, 20), null,
				duration, true, playerId, spellEffectId);
		currentImage = TextureHandler.getInstance().getBola(0);
		this.playerId = playerId;
		setDamage(5);
	}

	// make it multiply? Model.model.launchCustomSpellAreaOfEffect?
	public void applyEffect(Player p) {
		super.applyEffect(p);
		p.applyMovementModifyer(0.1f, 2000);
		if (playerId == Model.model.getMyself().getID()) {
			Model.model.launchCustomSpellAreaOfEffect(getEffectId(), this.getXPos() + this.getVectorX()/2, this.getYPos() + this.getVectorY()/2, getVectorX(), getVectorY(), 500, this.playerId, Model.model.getNextSpellEffectId());
		}
	}

	public void draw(Graphics g, float cameraX, float cameraY) {
		g.setColor(new Color(150, 75, 0));
		g.drawOval(getXPos() - cameraX, getYPos() - cameraY, this.getBoundingBox().getWidth(), this.getBoundingBox().getHeight());
		g.drawImage(currentImage, getXPos() - cameraX -12, getYPos() - cameraY - 12);
		g.fillOval(getXPos() - cameraX+getBoundingBox().getWidth()/3, getYPos() - cameraY+getBoundingBox().getWidth()/3, this.getBoundingBox().getWidth()/3, this.getBoundingBox().getHeight()/3);
	}

	public void update(int delta, ArrayList<Player> entities, Player myself) {
		super.update(delta, entities, myself);
		currentImage = TextureHandler.getInstance().getBola(
				Model.model.getMyself().getTileCounter() % 4);
	}

	public static int getEffectId() {
		return 2;
	}

}
