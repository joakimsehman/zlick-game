package abilities;

import org.newdawn.slick.geom.Vector2f;

public abstract class Ability{

	private String name;
	
	public Ability(String name){
		this.name = name;
	}
	
	//should contain mouse coordinates, and probobly alot else..
	public abstract void useAbility();
	
	public String getName(){
		return name;
	}
}
