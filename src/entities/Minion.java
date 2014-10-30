package entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import buffs.Buff;


//Everything is coded as minions...

public abstract class Minion extends Entity {

	
	private int maxHealthPoints;
	private int hp;
	private int speedDurationLeft;
	private ArrayList<Buff> activeBuffs;

	public Minion(float xPos, float yPos, Vector2f vector,
			Shape boundingBox, Image image, int maxHealthPoints) {
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
		this.setSpeedModifyer(amount);
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
	
	public void update(int delta, ArrayList<Entity> entities){
		super.update(delta, entities);
		if(speedDurationLeft > 0){
			speedDurationLeft = speedDurationLeft - delta;
		}else{
			this.setSpeedModifyer(1);
		}
	}

}
