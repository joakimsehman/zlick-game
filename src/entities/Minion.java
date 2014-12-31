package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import buffs.Buff;


//Everything is coded as minions...

public abstract class Minion extends Entity {

	
	private float maxHealthPoints;
	private float hp;
	private int speedDurationLeft;
	private ArrayList<Buff> activeBuffs;

	public Minion(float xPos, float yPos, Vector2f vector,
			Shape boundingBox, Image image, float maxHealthPoints) {
		super(xPos, yPos, vector, boundingBox, image);
		this.maxHealthPoints = maxHealthPoints;
		hp = maxHealthPoints;
		activeBuffs = new ArrayList<Buff>();
	}

	public void setMaxHealthPoints(int healthPoints) {
		this.maxHealthPoints = healthPoints;
	}
	
	public void applyMovementModifyer(float amount, int duration){
		speedDurationLeft = duration;
		this.setSpeedModifier(amount);
	}
	
	public void applyDamageModifyer(int amount){
		hp = hp + amount;
		if(hp > maxHealthPoints){
			hp = maxHealthPoints;
		}
	}
	
	public  void applyPlayerVector(Vector2f vector, int duration, boolean canPlayerMove){
		
	}
	
	public void applyInvis(int duration){
		
	}
	
	public float getSpeedDurationLeft(){
		return speedDurationLeft;
	}
	
	public void addBuff(Buff buff){
		buff.onApply(this);
		activeBuffs.add(buff);
	}
	
	public float getHealthPoints(){
		return hp;
	}
	
	public void update(int delta, ArrayList<Entity> entities, boolean collidesWithTerrain){
		super.update(delta, entities, collidesWithTerrain);
		if(speedDurationLeft > 0){
			speedDurationLeft = speedDurationLeft - delta;
		}else{
			this.setSpeedModifier(1);
		}
	}

}
