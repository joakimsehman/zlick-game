package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;






/* (tutorial start in abilities.Ability.java)
 * 
 * 
 * INDEX:
 * 	part 1 - abilities.Ability.java
 * 	part 2 - abilities.Fireball.java
 * 	part 3 - entities.SpellAreaOfEffect.java
 * 	part 4 - entities.FireballEffect.java
 * 	part 5 - abilities.AbilityInfo.java
 * 
 * 
HOW TO CREATE AN ABILITY TUTORIAL:

PART 1.

create a new class in abilities that extends Ability
add a constructor
implement useAbility - called when an ability is used
implement other abstract methods, that are called in various situations

For help, inspect other abilities and their belonging SpellAreaOfEffects:
One of the most simple: Fireball ability, dispatching a FireballEffect entity on use
TO FIND PART 2 OF TUTORIAL GOTO abilities.Fireball.java

-----------------------------------------------------------------------------------------

Abilities can add SpellAreaOfEffect entities to the game
SpellAreaOfEffect is often created and added when an ability is used
SpellAreaOfEffect is an abstract class that can be extended to act as a spell entity,

Some Examples:
	Ability that creates SpellAreaOfEffect: Fireball(FireballEffect), Icelance(IcelanceEffect), MassPolymorph(PolymorphingEffect)
	Ability that does not create SpellAreaOfEffect: Teleport
TO FIND PART 3 OF TUTORIAL GOTO entities.SpellAreaOfEffect.java

------------------------------------------------------------------------------------------

To add the ability to the game lobby:
add your ability to AbilityInfo, as well as increase the amount of abilities in AbilityInfo
TO FIND PART 5 OF TUTORIAL GOTO abilities.AbilityInfo.java


other spell related content:
	buffs - buffs can be applied to players, check out the buffs package

 */

public abstract class Ability{

	private String name;
	private int playerCreatedId;
	private int id;
	private int msSinceLastUsed;
	
	public enum AbilityType{
		FIRE, FROST, PHYSICAL, HEAL, MAGIC, POISON, ULTIMATE
	};
	
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
		return AbilityInfo.getInstance().getSpellIconFromId(id);
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
