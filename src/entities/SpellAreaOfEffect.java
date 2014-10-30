package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class SpellAreaOfEffect extends Entity{

	private int movementSpeedModifier;
	private int damage;
	private int shield;
	private int damageReduction;
	private int buffDuration;
	private int spellDuration;
	private boolean disappearsIfTouched;
	private int durationLeft;
	
	public SpellAreaOfEffect(float xPos, float yPos, Vector2f vector,
			Shape boundingBox, Image image,int duration,  boolean disappearsIfTouched) {
		super(xPos, yPos, vector, boundingBox, image);
		this.disappearsIfTouched = disappearsIfTouched;
		this.durationLeft = duration;
	}

	
	// if(dissappearsIfTouched == false) {damage is damage/sec}
	public void update(int delta, ArrayList<Entity> entities){
		super.update(delta, null);
		durationLeft = durationLeft - delta;
		if (entities != null) {
			for (Entity e : entities) {
				if (this.getBoundingBox().intersects(e.getBoundingBox())) {
					applyEffect((Player)e);
				}
			}
		}
	}
	
	public int getDurationLeft(){
		return durationLeft;
	}
	
	public void applyEffect(Player player){
		
	}
	
	public void onExpire(){
		
	}
}
