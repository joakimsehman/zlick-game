package entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import animation.AnimationGroup;
import buffs.Buff;

//Everything is coded as minions...

public abstract class Minion extends Entity {

	private float maxHealthPoints;
	private float hp;
	private int speedDurationLeft;
	private ArrayList<Buff> activeBuffs;

	private float spawnX, spawnY;

	private int transformWalkingAnimationNumber;
	private int transformStandingAnimationNumber;

	private AnimationGroup transformAnimation;

	private boolean isTransformed;

	private boolean isAbleToCast;

	private boolean isStunned;
	
	private int notAbleToMoveDuration;
	
	private boolean isInvisible;
	private int invisibilityDuration;

	private int totalTransformationTime;
	private int transformationTimeLeft;

	public Minion(float xPos, float yPos, Vector2f vector, Shape boundingBox,
			Image image, float maxHealthPoints) {
		super(xPos, yPos, vector, boundingBox, image);
		this.maxHealthPoints = maxHealthPoints;
		hp = maxHealthPoints;
		activeBuffs = new ArrayList<Buff>();

		isTransformed = false;
		isAbleToCast = true;

		transformWalkingAnimationNumber = -1;
		transformStandingAnimationNumber = -1;

		totalTransformationTime = -1;
		transformationTimeLeft = -1;

		spawnX = 0;
		spawnY = 0;
		
		isInvisible = false;
		invisibilityDuration = 0;
		
		notAbleToMoveDuration = -1;
	}

	public void setMaxHealthPoints(int healthPoints) {
		this.maxHealthPoints = healthPoints;
	}

	public void applyMovementModifyer(float amount, int duration) {
		speedDurationLeft = duration;
		this.setSpeedModifier(amount);
	}

	public void applyDamage(int amount) {
		hp = hp + amount;
		if (hp > maxHealthPoints) {
			hp = maxHealthPoints;
		}
		if (hp <= 0) {
			onDying();
		}
	}

	protected void onDying() {
		// TODO Auto-generated method stub

	}

	public void setHealthToMax() {
		hp = maxHealthPoints;
	}

	public void applyPlayerVector(Vector2f vector, int duration,
			boolean canPlayerMove) {

	}

	public void applyInvis(int duration) {
		isInvisible = true;
		invisibilityDuration = duration;
		onInvis(isInvisible);
	}
	
	public boolean isInvisible(){
		return isInvisible;
	}

	public float getSpeedDurationLeft() {
		return speedDurationLeft;
	}

	public void addBuff(Buff buff) {
		
		boolean hasBuff = false;
		
		for (int i = 0; i < activeBuffs.size(); i++) {
			if (activeBuffs.get(i).getID() == buff.getID()) {
				hasBuff = true;
			}
		}

		if (!hasBuff) {
			buff.onApply(this);
			activeBuffs.add(buff);
		}

	}

	public float getHealthPoints() {
		return hp;
	}

	public void draw(Graphics g, float cameraX, float cameraY) {

		if (isTransformed()) {
			transformAnimation
					.draw(g, getXPos() - cameraX, getYPos() - cameraY);
		}
	}

	public void update(int delta, ArrayList<Entity> entities,
			boolean collidesWithTerrain) {
		super.update(delta, entities, collidesWithTerrain);
		if (speedDurationLeft > 0) {
			speedDurationLeft = speedDurationLeft - delta;
		} else {
			this.setSpeedModifier(1);
		}
		for (int i = 0; i < activeBuffs.size(); i++) {
			activeBuffs.get(i).update(delta, this);
		}
		if (isTransformed()) {
			if (totalTransformationTime != -1) {
				transformationTimeLeft -= delta;
				if (transformationTimeLeft < 0) {
					isTransformed = false;
				}
			}
			double animSpritePercent = 0;
			switch (getDirection()) {
			case WEST:
			case NORTHWEST:
				animSpritePercent = 0.25;
				break;
			case NORTH:
			case NORTHEAST:
				animSpritePercent = 0.9;
				break;
			case EAST:
			case SOUTHEAST:
				animSpritePercent = 0.5;
				break;
			case SOUTH:
			case SOUTHWEST:
				animSpritePercent = 0;
				break;
			}
			//		case WEST:
			//			animDegrees = 0;
			//			break;
			//		case NORTHWEST:
			//			animDegrees = 45;
			//			break;
			//		case NORTH:
			//			animDegrees = 90;
			//			break;
			//		case NORTHEAST:
			//			animDegrees = 135;
			//			break;
			//		case EAST:
			//			animDegrees = 180;
			//			break;
			//		case SOUTHEAST:
			//			animDegrees = 225;
			//			break;
			//		case SOUTH:
			//			animDegrees = 270;
			//			break;
			//		case SOUTHWEST:
			//			animDegrees = 315;
			//			break;
			//		}
			transformAnimation.update1(delta, animSpritePercent);

		}
		if(isInvisible){
			invisibilityDuration -= delta;
			if(invisibilityDuration < 0){
				isInvisible = false;
				onInvis(isInvisible);
			}
		}
		if(notAbleToMoveDuration > 0){
			notAbleToMoveDuration -= delta;
			if(notAbleToMoveDuration <= 0){
				setIsAbleToMove(true);
			}
		}
		checkForExpiredBuffs();
	}

	private void checkForExpiredBuffs() {
		for (int i = 0; i < activeBuffs.size(); i++) {
			if (activeBuffs.get(i).getDurationLeft() < 0) {
				activeBuffs.get(i).onRemove(this);
				activeBuffs.remove(i);
			}
		}
	}

	protected void setIsMoving(boolean isMoving) {

		if (isMoving != isMoving() && isTransformed) {
			if (isMoving && transformWalkingAnimationNumber != -1) {

				transformAnimation
						.setCurrentAnimation(transformWalkingAnimationNumber);

			} else {
				if (transformStandingAnimationNumber != -1) {
					transformAnimation
							.setCurrentAnimation(transformStandingAnimationNumber);
				}
			}
		}
		super.setIsMoving(isMoving);
	}

	public void setIsAbleToCast(boolean isAbleToCast) {
		this.isAbleToCast = isAbleToCast;
	}

	public boolean isAbleToCast() {
		return isAbleToCast;
	}

	public void setIsStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}

	public boolean isTransformed() {
		return isTransformed;
	}

	public void cancelTransformation() {
		isTransformed = false;
	}

	public void setTransformed(AnimationGroup transAnim, int transformDuration,
			int transformWalkingAnimationNumber,
			int transformStandingAnimationNumber) {
		isTransformed = true;
		this.transformAnimation = transAnim;
		this.totalTransformationTime = transformDuration;
		this.transformationTimeLeft = transformDuration;
		this.transformWalkingAnimationNumber = transformWalkingAnimationNumber;
		this.transformStandingAnimationNumber = transformStandingAnimationNumber;

		if (isMoving()) {
			transformAnimation
					.setCurrentAnimation(transformWalkingAnimationNumber);
		} else {
			transformAnimation
					.setCurrentAnimation(transformStandingAnimationNumber);
		}

	}

	public boolean hasBuff(Buff buff) {
		for (int i = 0; i < activeBuffs.size(); i++) {
			if (activeBuffs.get(i).getID() == buff.getID()) {
				return true;
			}
		}
		return false;
	}

	public void setSpawnPoint(float spawnX, float spawnY) {
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}

	public void goToSpawnPoint() {
		setPos(spawnX, spawnY);
	}

	public float getSpawnX() {
		return spawnX;
	}

	public float getSpawnY() {
		return spawnY;
	}

	public void setHealthPoints(float hp) {
		if (hp <= this.maxHealthPoints) {
			this.hp = hp;
		}
	}
	
	//only works if isAbleToMove is not already set to false without a duration
	public void setIsAbleToMove(boolean isAbleToMove, int duration){
		if(!isAbleToMove() && notAbleToMoveDuration <= 0){
			return;
		}else{
			setIsAbleToMove(isAbleToMove);
			if(!isAbleToMove){
				notAbleToMoveDuration = duration;
			}
		}
	}
	
	public abstract void onInvis(boolean invis);
	
	public abstract int getID();

}
