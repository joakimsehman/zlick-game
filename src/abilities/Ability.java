package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;






/*
 * create a new class in abilities that extends Ability
add a constructor and pass Ability lobbyicon to super constructor
add your ability to AbilityCreator
in lobby, in the enter method add your Ability

implement useAbility, and decide cost and castTime for ability in getCost() and getCastTime
 */

public abstract class Ability{

	private String name;
	private int playerCreatedId;
	private int id;
	private Image icon;
	
	public Ability(String name, int id, Image icon){
		this.name = name;
		this.id = id;
		this.icon = icon;
	}
	
	
	
	
	public abstract void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId);
	
	public final String getName(){
		return name;
	}
	
	public final int getID(){
		return id;
	}
	
	public int getPlayerCreatedId(){
		return playerCreatedId;
	}
	
	public abstract int getCost();
	
	public Image getIcon(){
		return icon;
	}
	
	public abstract int getSpellEffectAmount();
	
	public abstract int getCastTime();
	
	public abstract boolean isCastableWhileMoving();
	
}
