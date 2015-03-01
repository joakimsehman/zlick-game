package utilities;

import org.newdawn.slick.Image;

import abilities.Ability;
import abilities.Bola;
import abilities.Fireball;
import abilities.MassPolymorph;
import abilities.Teleport;

public class AbilityCreator {
	
	private static AbilityCreator abilityCreator;
	private static Image[] abilityIcons;
	
	private AbilityCreator(){
		
	}
	
	public static AbilityCreator getInstance(){
		
			if (abilityCreator == null) {
				abilityCreator = new AbilityCreator();
				abilityCreator.loadAbilityIcons();
			}
			return abilityCreator;
		
	}
	
	public void loadAbilityIcons(){
		abilityIcons = new Image[getNumberOfAbilities()];
		
		abilityIcons[0] = TextureHandler.getInstance().getImageByName("fireballIcon.png");
		abilityIcons[1] = TextureHandler.getInstance().getImageByName("massPolymorphIcon.png");
		abilityIcons[2] = TextureHandler.getInstance().getImageByName("bolaIcon.png");
		abilityIcons[3] = TextureHandler.getInstance().getImageByName("teleportIcon.png");
	}
	
	public Ability getNewAbility(int abilityID, int usingPlayerId){
		
		if(abilityID == 0){
			return new Fireball("fireball", 1000, usingPlayerId);
		}else if(abilityID == 1){
			return new MassPolymorph("massPolymorph", 5000, usingPlayerId);
		}else if(abilityID == 2){
			return new Bola("bola", 500, usingPlayerId);
		}else if(abilityID == 3){
			return new Teleport("teleport", usingPlayerId);
		}
		return null;
		
	}
	
	public int getNumberOfAbilities(){
		return 4;
	}
	
	
	public Image getSpellIconFromId(int id){
		return abilityIcons[id];
	}
}
