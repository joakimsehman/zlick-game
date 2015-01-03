package utilities;

import abilities.Ability;
import abilities.Fireball;
import abilities.MassPolymorph;

public class AbilityCreator {
	
	public static Ability getNewAbility(int abilityID){
		
		if(abilityID == 0){
			return new Fireball("fireball",1000);
		}else if(abilityID == 1){
			return new MassPolymorph("massPolymorph", 3000);
		}
		return null;
		
	}
	
	public static int getNumberOfAbilities(){
		return 2;
	}
}
