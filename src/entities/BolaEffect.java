package entities;

import game.Model;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import utilities.SoundHandler;
import utilities.TextureHandler;

public class BolaEffect extends SpellAreaOfEffect {

	
	private int playerId;
	private static int damage = 8;
	private static AnimationGroup animation;
	

	public BolaEffect(float xPos, float yPos, Vector2f vector, int duration,
			int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos, yPos, 20), null,
				duration, true, playerId, spellEffectId);
		this.playerId = playerId;
		
		if(animation == null){
			animation = new AnimationGroup();
			animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("projectiles.png", 0, 4, 2,
						1)));
		}
		
		if(Model.model.isOnScreen(xPos, yPos)){
			SoundHandler.getInstance().bolaSound.play();
		}
	}
	
	public static int getDamage(){
		return damage;
	}

	// make it multiply? Model.model.launchCustomSpellAreaOfEffect?
	public void applyEffect(Player p) {
		super.applyEffect(p);
		p.applyMovementModifyer(0.3f, 2000);
		p.applyDamage(-damage);
		if (playerId == Model.model.getMyself().getID()) {
			Model.model.launchCustomSpellAreaOfEffect(getEffectId(), this.getXPos() + this.getVectorX()/2, this.getYPos() + this.getVectorY()/2, getVectorX(), getVectorY(), 500, this.playerId, Model.model.getNextSpellEffectId());
		}
	}

	public void draw(Graphics g, float cameraX, float cameraY) {
		g.setColor(new Color(150, 75, 0));
		g.drawOval(getXPos() - cameraX, getYPos() - cameraY, this.getBoundingBox().getWidth(), this.getBoundingBox().getHeight());
		
		
		animation.draw(g, getXPos() - cameraX -12, getYPos() - cameraY -12);
		
		g.fillOval(getXPos() - cameraX+getBoundingBox().getWidth()/3, getYPos() - cameraY+getBoundingBox().getWidth()/3, this.getBoundingBox().getWidth()/3, this.getBoundingBox().getHeight()/3);
	}

	public void update(int delta, ArrayList<Player> entities, Player myself) {
		super.update(delta, entities, myself);

		
		animation.update(delta, 0);
	}

	public static int getEffectId() {
		return 2;
	}

}
