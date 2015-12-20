package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import entities.Player.EffectAnimation;
import utilities.TextureHandler;


public class Arrow extends SpellAreaOfEffect{
	
	private Image arrowImage;

	public Arrow(float xPos, float yPos, Vector2f vector,
			int duration,
			int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos+15, yPos+15, 15), null, duration, true,
				playerId, spellEffectId);
		
		switch (getDirection()) {
		case WEST:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(0, 0, "projectiles.png");
			break;
		case NORTHWEST:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(1, 0, "projectiles.png");
			break;
		case NORTH:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(2, 0, "projectiles.png");
			break;
		case NORTHEAST:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(3, 0, "projectiles.png");
			break;
		case EAST:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(4, 0, "projectiles.png");
			break;
		case SOUTHEAST:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(5, 0, "projectiles.png");
			break;
		case SOUTH:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(6, 0, "projectiles.png");
			break;
		case SOUTHWEST:
			arrowImage = TextureHandler.getInstance().getImageFromSpriteSheet(7, 0, "projectiles.png");
			break;
		}
		
		
	}
	
	public void draw(Graphics g, float cameraX, float cameraY) {
		g.drawImage(arrowImage, getXPos() - cameraX-10, getYPos() - cameraY-15);
//		draw hitbox
//		g.setColor(Color.black);
//		g.drawOval(getBoundingBox().getX()-cameraX, getBoundingBox().getY()-cameraY, 30, 30);
	}
	
	public void applyEffect(Player player) {
		super.applyEffect(player);
		player.applyDamage(-5, EffectAnimation.BLOOD, this.getPlayerUsedId());
	}
	
	public static int getEffectId(){
		return 6;
	}
	

}
