package abilities;

import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import entities.AnimatedDecoration;
import game.Model;

public class Teleport extends Ability{

	public Teleport(String name, int playerCreatedId) {
		super(name, 3, playerCreatedId);
		
	}
	
	public void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId){
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		AnimationGroup animation = new AnimationGroup();
		animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("teleport_rune.png", 0, 4, 0, 1)));
		AnimatedDecoration decoration = new AnimatedDecoration(Model.model.getPlayer(this.getPlayerCreatedId()).getXPos()-7, Model.model.getPlayer(this.getPlayerCreatedId()).getYPos(), new Vector2f(0,0), animation);
		Model.model.addTemporaryDecoration(decoration, 400, false);
		animation = new AnimationGroup();
		animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("teleport_rune.png", 0, 4, 0, 1)));
		decoration = new AnimatedDecoration(mouseGameX-35, mouseGameY-15, new Vector2f(0,0), animation);
		Model.model.addTemporaryDecoration(decoration, 400, false);
		Model.model.getPlayer(this.getPlayerCreatedId()).setPos(mouseGameX-25, mouseGameY-25);
	}

	@Override
	public int getCost() {
		return 20;
	}

	@Override
	public int getSpellEffectAmount() {
		return 0;
	}

	@Override
	public int getCastTime() {
		return 0;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return true;
	}

	@Override
	public int getCooldown() {
		return 6000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		if(((Math.pow((Model.model.getPlayer(this.getPlayerCreatedId()).getXPos() - mouseGameX-25),2) + Math.pow((Model.model.getPlayer(this.getPlayerCreatedId()).getYPos() - mouseGameY-25),2) < Math.pow(500, 2)))){
			if(Model.model.isNonSolidNonNullPosition(mouseGameX-25, mouseGameY-25) && Model.model.isNonSolidNonNullPosition(mouseGameX-25, mouseGameY+25) 
					&& Model.model.isNonSolidNonNullPosition(mouseGameX+25, mouseGameY-25) && Model.model.isNonSolidNonNullPosition(mouseGameX+25, mouseGameY+25)){
					return true;
				}
		}
		return false;
	}

}
