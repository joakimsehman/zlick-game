package utilities;

import org.newdawn.slick.Image;

import abilities.Ability;
import abilities.Bola;
import abilities.ElementalDischarge;
import abilities.Fireball;
import abilities.Healcharge;
import abilities.IceLance;
import abilities.MassPolymorph;
import abilities.Teleport;


/*
 * Whenever a new ability is created in game(for example when you or another player has chosen an ability) it will be created from this class, 
 * that is why every time you make a new ability you will need to change this class in the following ways:
 * - increase getNumberOfAbilities() by 1
 * - make loadAbilityIcons load your abilitys icon (remember to add icon to textureHandler)
 * - make getNewAbility able to return your ability(each ability should have a unique Id, an abilitysId is set in the Ability constructor, ie in the super constructor for every class extending Ability)
 * 
 * this will make the ability visible and selectable in the lobby, so this should be done when you first want to start testing your ability. ie it should contain no compilationerrors
 */
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
		abilityIcons[4] = TextureHandler.getInstance().getImageByName("icelanceIcon.png");
		abilityIcons[5] = TextureHandler.getInstance().getImageByName("elementalDischargeIcon.png");
		abilityIcons[6] = TextureHandler.getInstance().getImageByName("cure-3.png");
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
		}else if(abilityID == 4){
			return new IceLance("icelance", 1000, usingPlayerId);
		}else if(abilityID == 5){
			return new ElementalDischarge("elemental discharge", 10000, usingPlayerId);
		}else if(abilityID == 6){
			return new Healcharge("heal", usingPlayerId);
		}
		return null;
		
	}
	
	//increase this everytime you add an ability
	public int getNumberOfAbilities(){
		return 7;
	}
	
	
	public Image getSpellIconFromId(int id){
		return abilityIcons[id];
	}
}
