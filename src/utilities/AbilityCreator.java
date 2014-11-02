package utilities;

import abilities.Ability;
import abilities.Fireball;

public class AbilityCreator {
	
	public static Ability getNewAbility(int abilityID){
		
		if(abilityID == 0){
			return new Fireball("fireball",1000);
		}
		return null;
		
	}
	
	public static int getNumberOfAbilities(){
		return 1;
	}
}
