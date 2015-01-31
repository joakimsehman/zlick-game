package abilities;

import entities.Player;
import game.Model;
import org.newdawn.slick.Image;

/**
 * Created by joakim on 2015-01-31.
 */
public class SoulRelease extends Ability{

    public SoulRelease(String name, int id, Image icon){
       super(name, id, icon);


    }

    public void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId){
        Player usingPlayer = Model.model.getPlayer(id);
    }

    public int getCost(){

    }

    public int getSpellEffectAmount(){

    }

    public int getCastTime(){
        return 0;

    }

    public boolean isCastableWhileMoving(){
        return true;
    }

}
