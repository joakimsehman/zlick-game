package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

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
	
}
