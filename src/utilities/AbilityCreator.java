package utilities;

import abilities.Ability;
import abilities.SimpleDamageAbility;

public class AbilityCreator {
	
	public static Ability getNewAbility(int abilityID){
		
		if(abilityID == 0){
			return new SimpleDamageAbility("fireball",1000);
		}
		return null;
		
	}
}
