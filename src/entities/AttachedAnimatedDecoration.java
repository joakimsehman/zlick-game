package entities;

import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;

public class AttachedAnimatedDecoration extends AnimatedDecoration{

	Entity attachedEntity;
	
	public AttachedAnimatedDecoration(Entity entity,
			AnimationGroup animation) {
		super(entity.getXPos(), entity.getYPos(), new Vector2f(0,0), animation);
		this.attachedEntity = entity;
	}
	
	public void update(int delta){
		super.update(delta);
		this.setPos(attachedEntity.getXPos(), attachedEntity.getYPos());
	}

}
