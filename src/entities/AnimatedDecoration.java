package entities;

import java.util.ArrayList;

import game.Model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;

public class AnimatedDecoration extends Entity {

	AnimationGroup animation;

	public AnimatedDecoration(float xPos, float yPos, Vector2f vector,
			AnimationGroup animation) {
		super(xPos, yPos, vector, null, null);
		this.animation = animation;
	}

	public void draw(Graphics g, float cameraX, float cameraY) {
		if (Model.model.isOnScreen(getXPos(), getYPos())) {
			animation.draw(g, getXPos()-cameraX, getYPos()-cameraY);
		}
	}
	
	public void update(int delta){
		super.update(delta, null, false);
		animation.update1(delta, 0);
	}

}
