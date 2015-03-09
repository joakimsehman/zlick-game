package entities;

import java.util.ArrayList;

import game.Model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;
import animation.DirectedAnimation;

public class ElementalDischargeEffect extends SpellAreaOfEffect{
	
	
	private AnimationGroup animation;

	public ElementalDischargeEffect(float xPos, float yPos, Vector2f vector,
			int duration, int playerId, int spellEffectId) {
		super(xPos, yPos, vector, new Circle(xPos, yPos, 10), null, duration, false,
				playerId, spellEffectId);
		// TODO Auto-generated constructor stub
		
		if(animation == null){
			animation = new AnimationGroup();
			animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("elementaldischarge.png", 0, 4, 0, 1)));
		}
	}
	
	@Override
	public void onTic(int delta, Player player){
		player.applyMovementModifyer(0.3f, 2000);
	}
	
	public static int getEffectId(){
		return 5;
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
	
	public void onActivate(){
		setDurationLeft(-1);
		if(getPlayerUsedId() == Model.model.getMyself().getID()){
			Model.model.launchCustomSpellAreaOfEffect(3, this.getXPos(), this.getYPos(), 80, 0, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			Model.model.launchCustomSpellAreaOfEffect(3, this.getXPos(), this.getYPos(), -80, 0, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			Model.model.launchCustomSpellAreaOfEffect(3, this.getXPos(), this.getYPos(), 0, 80, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			Model.model.launchCustomSpellAreaOfEffect(3, this.getXPos(), this.getYPos(), 0, -80, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			
			Model.model.launchCustomSpellAreaOfEffect(4, this.getXPos(), this.getYPos(), 70, 70, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			Model.model.launchCustomSpellAreaOfEffect(4, this.getXPos(), this.getYPos(), -70, 70, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			Model.model.launchCustomSpellAreaOfEffect(4, this.getXPos(), this.getYPos(), 70, -70, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
			Model.model.launchCustomSpellAreaOfEffect(4, this.getXPos(), this.getYPos(), -70, -70, 1000, this.getPlayerUsedId(), Model.model.getNextSpellEffectId());
		}
	}

}
