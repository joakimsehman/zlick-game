package entities;

import java.util.ArrayList;

import entities.Player.EffectAnimation;
import game.Model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import buffs.Chill;
import buffs.Freeze;
import utilities.SoundHandler;
import animation.AnimationGroup;
import animation.DirectedAnimation;

public class IceLanceEffect extends SpellAreaOfEffect{

	private int damage;
	private AnimationGroup animation;
	private Chill chillBuff;
	private Freeze freezeBuff;
	
	public IceLanceEffect(float xPos, float yPos, Vector2f vector, int duration,
			int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos, yPos, 10), null, duration, true,
				playerId, spellEffectId);
		damage = 10;
		if(animation == null){
			animation = new AnimationGroup();
			animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("icelance.png", 0, 8, 0, 8)));
		}
		
		chillBuff = new Chill(3000);
		freezeBuff = new Freeze(3000);
//		if(Model.model.isOnScreen(xPos, yPos)){
//			SoundHandler.getInstance().fireballSound.play(1.0f, 0.3f);
//		}
	}
	
	public void applyEffect(Player player){
		super.applyEffect(player);
		player.applyDamage(-damage, EffectAnimation.ICE);
		if(player.hasBuff(chillBuff)){
			player.addBuff(freezeBuff);
		}else{
			player.addBuff(chillBuff);
		}
	}
	
	public void update(int delta, ArrayList<Player> entities, Player myself) {
		super.update(delta, entities, myself);
		
		double angle = this.getAngleToPoint(getXPos() + getVectorX(), getYPos() + getVectorY());
		
		animation.update2(delta, angle);
	}
	
	public void draw(Graphics g, float cameraX, float cameraY){
		animation.draw(g, getXPos() - cameraX -12, getYPos() - cameraY -12);
	}
	
	public static int getEffectId(){
		return 4;
	}

}
