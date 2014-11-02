package entities;

import java.util.ArrayList;

import game.Model;
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
        damage = 10;
	}

	
	// if(dissappearsIfTouched == false) {damage is damage/sec}
	public void update(int delta, ArrayList<Player> entities, Player myself){
		super.update(delta, null);
		durationLeft = durationLeft - delta;
		if (entities != null) {
            if(disappearsIfTouched){

                if(Model.model.isServer())
                    if(this.getBoundingBox().intersects(myself.getBoundingBox())) {
                        applyEffect(myself);
                    }
		           	for (Player p : entities) {
                        if (this.getBoundingBox().intersects(p.getBoundingBox())) {
                            applyEffect(p);

                        }
                }

			}
		}
	}
	
	public int getDurationLeft(){
		return durationLeft;
	}
	
	public void applyEffect(Player player){
		player.applyDamageModifyer(damage);
	}
	
	public void onExpire(){
		
	}
}
