package abilities;

import org.newdawn.slick.geom.Vector2f;

public abstract class Ability{

	private String name;
	private int playerCreatedId;
	private int id;
	
	public Ability(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	
	
	//should contain mouse coordinates, and probobly alot else..
	public abstract void useAbility(int id, float mouseGameX, float mouseGameY);
	
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
	
	
}
