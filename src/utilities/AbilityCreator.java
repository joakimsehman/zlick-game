package utilities;

import abilities.Ability;
import abilities.Bola;
import abilities.Fireball;
import abilities.MassPolymorph;

public class AbilityCreator {
	
	public static Ability getNewAbility(int abilityID, int usingPlayerId){
		
		if(abilityID == 0){
			return new Fireball("fireball",1000, usingPlayerId);
		}else if(abilityID == 1){
			return new MassPolymorph("massPolymorph", 3000, usingPlayerId);
		}else if(abilityID == 2){
			return new Bola("bola", 500, usingPlayerId);
		}
		return null;
		
	}
	
	public static int getNumberOfAbilities(){
		return 3;
	}
}
