package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import utilities.AbilityCreator;






/*
 * create a new class in abilities that extends Ability
add a constructor
add your ability to AbilityCreator, as well as increase the amount of abilities in AbilityCreator

implement useAbility, and decide cost and castTime for ability in getCost() and getCastTime
 */

public abstract class Ability{

	private String name;
	private int playerCreatedId;
	private int id;
	private int msSinceLastUsed;
	
	public Ability(String name, int id, int playerCreatedId){
		this.name = name;
		this.id = id;
        this.playerCreatedId = playerCreatedId;
        msSinceLastUsed = getCooldown();
	}
	
	
	
	//TODO change id to be player used on if any instead of the player self, change in all abilities and all places where abilities are used
	public void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId){
		msSinceLastUsed = 0;
	}
	
	public void update(int delta){
		msSinceLastUsed += delta;
	}
	
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
		return AbilityCreator.getInstance().getSpellIconFromId(id);
	}
	
	public abstract int getSpellEffectAmount();
	
	public abstract int getCastTime();
	
	public abstract boolean isCastableWhileMoving();
	
	public abstract int getCooldown();
	
	public abstract boolean isCastable(int id, float mouseGameX, float mouseGameY);
	
	public int getMsSinceLastUse(){
		return msSinceLastUsed;
	}
	
	public void resetCooldown(){
		msSinceLastUsed = getCooldown();
	}
	
	
	
}
