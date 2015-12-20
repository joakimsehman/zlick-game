package entities;

import java.util.ArrayList;

import game.Model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import buffs.Ignite;
import animation.AnimationGroup;
import animation.DirectedAnimation;
import utilities.SoundHandler;
import utilities.TextureHandler;

/*(tutorial start in abilities.Ability.java)
 * 
 * 
 * 
 * HOW TO CREATE AN ABILITY TUTORIAL:
 * 
 * 
 * PART 4:
 * 
 * This is an example of a spell effect implementation
 * 
 * notes:
 * sets dissappearsIfTouched to true when calling super constructor
 * overrides applyEffect(Player player) to apply effects
 * 
 * return to abilities.Ability.java
 */



public class FireballEffect extends SpellAreaOfEffect {

	private int damage;
	private Ignite ignite;
	private AnimationGroup animation;

	public FireballEffect(float xPos, float yPos, Vector2f vector,
			int duration, int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos, yPos, 10), null, duration,
				true, playerId, spellEffectId);
		damage = 10;
		if (animation == null) {
			animation = new AnimationGroup();
			animation.addDirectedAnimation(new DirectedAnimation(
					DirectedAnimation.getSpritesAlongX("fireball.png", 0, 8, 0,
							8)));
		}

		ignite = new Ignite(10000);

		if (Model.model.isOnScreen(xPos, yPos)) {
			SoundHandler.getInstance().fireballSound.play(1.0f, 0.3f);
		}
	}

	public void applyEffect(Player player) {
		super.applyEffect(player);
		if (player.hasBuff(ignite)) {
			player.applyDamage(-(damage + damage), getPlayerUsedId());
		} else {
			player.applyDamage(-damage, getPlayerUsedId());
			player.addBuff(ignite);
		}
	}

	public void update(int delta, ArrayList<Player> entities, Player myself) {
		super.update(delta, entities, myself);

		double angle = this.getAngleToPoint(getXPos() + getVectorX(), getYPos()
				+ getVectorY());

		animation.update2(delta, angle);
	}

	public void draw(Graphics g, float cameraX, float cameraY) {
		animation.draw(g, getXPos() - cameraX - 12, getYPos() - cameraY - 12);
	}

	public static int getEffectId() {
		return 3;
	}

}
