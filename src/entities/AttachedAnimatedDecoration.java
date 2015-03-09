package entities;

import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;

public class AttachedAnimatedDecoration extends AnimatedDecoration{

	private Entity attachedEntity;
	private int xOffset, yOffset;
	
	public AttachedAnimatedDecoration(Entity entity,
			AnimationGroup animation, int xOffset, int yOffset) {
		super(entity.getXPos(), entity.getYPos(), new Vector2f(0,0), animation);
		this.attachedEntity = entity;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void update(int delta){
		super.update(delta);
		this.setPos(attachedEntity.getXPos() + xOffset, attachedEntity.getYPos() + yOffset);
	}

}
