package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import buffs.Buff;

//Everything is coded as minions...

public abstract class Minion extends Entity {

	private boolean isPolymorphed;
	private float maxHealthPoints;
	private float hp;
	private int speedDurationLeft;
	private ArrayList<Buff> activeBuffs;

	public Minion(float xPos, float yPos, Vector2f vector, Shape boundingBox,
			Image image, float maxHealthPoints) {
		super(xPos, yPos, vector, boundingBox, image);
		this.maxHealthPoints = maxHealthPoints;
		hp = maxHealthPoints;
		isPolymorphed = false;
		activeBuffs = new ArrayList<Buff>();
	}

	public void setMaxHealthPoints(int healthPoints) {
		this.maxHealthPoints = healthPoints;
	}

	public void applyMovementModifyer(float amount, int duration) {
		speedDurationLeft = duration;
		this.setSpeedModifier(amount);
	}

	public void applyDamageModifyer(int amount) {
		hp = hp + amount;
		if (hp > maxHealthPoints) {
			hp = maxHealthPoints;
		}
	}

	public void applyPlayerVector(Vector2f vector, int duration,
			boolean canPlayerMove) {

	}

	public void applyInvis(int duration) {

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
		
		checkForExpiredBuffs();
	}
	
	private void checkForExpiredBuffs() {
		for(int i = 0; i < activeBuffs.size(); i++){
			if(activeBuffs.get(i).getDurationLeft() < 0){
				activeBuffs.get(i).onRemove(this);
				activeBuffs.remove(i);
				break;
			}
		}
		
	}

	public void setPolymorphed(boolean isPolymorphed, int duration){
		this.isPolymorphed = isPolymorphed;
		if(isPolymorphed){
			applyMovementModifyer(0.2f, duration);
		}
	}
	
	public boolean isPolymorphed(){
		return isPolymorphed;
	}

}
