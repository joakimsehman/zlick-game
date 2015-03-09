package abilities;

import entities.Player;
import game.Model;
import org.newdawn.slick.Image;

/**
 * Created by joakim on 2015-01-31.
 */
public class SoulRelease extends Ability{

    public SoulRelease(String name, int id, Image icon, int playerCreatedId){
       super(name, id, playerCreatedId);


    }

    public void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId){
        Player usingPlayer = Model.model.getPlayer(id);
    }


    //du kan använda getPlayerCreatedId() för att komma åt usingPlayer
    public int getCost(){
    	return 0;
    }

    public int getSpellEffectAmount(){
    	return 0;
    }

    public int getCastTime(){
        return 0;

    }

    public boolean isCastableWhileMoving(){
        return true;
    }

	@Override
	public int getCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		// TODO Auto-generated method stub
		return false;
	}

}
