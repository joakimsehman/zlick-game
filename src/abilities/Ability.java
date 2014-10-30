package abilities;

import org.newdawn.slick.geom.Vector2f;

public abstract class Ability{

	private String name;
	private static int abilityID = 0;
	
	public Ability(String name){
		this.name = name;
	}
	
	//should contain mouse coordinates, and probobly alot else..
	public abstract void useAbility(int id, float mouseGameX, float mouseGameY);
	
	public String getName(){
		return name;
	}
	
	public abstract int getID();
	
	public static int getNextAbilityID(){
		return abilityID++;
	}
}
