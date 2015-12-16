package abilities;

import org.newdawn.slick.Image;

import abilities.Ability.AbilityType;
import utilities.TextureHandler;
import entities.BolaEffect;
import entities.FireballEffect;

/*
 * 
 * /*(tutorial start in abilities.Ability.java)
 * 
 * 
 * 
 * HOW TO CREATE AN ABILITY TUTORIAL:
 * 
 * 
 * PART 5:
 * 
 *
 * Whenever a new ability is created in game(for example when you or another player has chosen an ability) it will be created from this class, 
 * that is why every time you make a new ability you will need to change this class in the following ways:
 * - increase getNumberOfAbilities() by 1
 * - make loadAbilityIcons load your abilitys icons (remember to add the icons to textureHandler)
 * - make getNewAbility able to return your ability(each ability should have a unique Id, an abilitysId is set in the Ability constructor, ie in the super constructor for every class extending Ability)
 * 
 * this will make the ability visible and selectable in the lobby, so this should be done when you first want to start testing your ability. ie it should contain no compilationerrors
 */
public class AbilityInfo {

	private static AbilityInfo abilityCreator;
	private static Image[] abilityIcons;
	private static Image[] smallAbilityIcons;

	private AbilityInfo() {

	}

	public static AbilityInfo getInstance() {

		if (abilityCreator == null) {
			abilityCreator = new AbilityInfo();
			abilityCreator.loadAbilityIcons();
		}
		return abilityCreator;

	}

	public void loadAbilityIcons() {
		abilityIcons = new Image[getNumberOfAbilities()];

		abilityIcons[0] = TextureHandler.getInstance().getImageByName(
				"fireballIcon.png");
		abilityIcons[1] = TextureHandler.getInstance().getImageByName(
				"massPolymorphIcon.png");
		abilityIcons[2] = TextureHandler.getInstance().getImageByName(
				"bolaIcon.png");
		abilityIcons[3] = TextureHandler.getInstance().getImageByName(
				"teleportIcon.png");
		abilityIcons[4] = TextureHandler.getInstance().getImageByName(
				"icelanceIcon.png");
		abilityIcons[5] = TextureHandler.getInstance().getImageByName(
				"elementalDischargeIcon.png");
		abilityIcons[6] = TextureHandler.getInstance().getImageByName(
				"cure-3.png");
		abilityIcons[7] = TextureHandler.getInstance().getImageByName(
				"stealth.png");

		smallAbilityIcons = new Image[getNumberOfAbilities()];

		smallAbilityIcons[0] = TextureHandler.getInstance().getImageByName(
				"fireballIconSmall.png");
		smallAbilityIcons[1] = TextureHandler.getInstance().getImageByName(
				"massPolymorphIconSmall.png");
		smallAbilityIcons[2] = TextureHandler.getInstance().getImageByName(
				"bolaIconSmall.png");
		smallAbilityIcons[3] = TextureHandler.getInstance().getImageByName(
				"teleportIconSmall.png");
		smallAbilityIcons[4] = TextureHandler.getInstance().getImageByName(
				"icelanceIconSmall.png");
		smallAbilityIcons[5] = TextureHandler.getInstance().getImageByName(
				"elementalDischargeIconSmall.png");
		smallAbilityIcons[6] = TextureHandler.getInstance().getImageByName(
				"cure-3.png");
		smallAbilityIcons[7] = TextureHandler.getInstance().getImageByName(
				"stealthIconSmall.png");

	}

	public Ability getNewAbility(int abilityID, int usingPlayerId) {

		if (abilityID == 0) {
			return new Fireball("fireball", 1000, usingPlayerId);
		} else if (abilityID == 1) {
			return new MassPolymorph("massPolymorph", 5000, usingPlayerId);
		} else if (abilityID == 2) {
			return new Bola("bola", 1000, usingPlayerId);
		} else if (abilityID == 3) {
			return new Teleport("teleport", usingPlayerId);
		} else if (abilityID == 4) {
			return new IceLance("icelance", 1000, usingPlayerId);
		} else if (abilityID == 5) {
			return new ElementalDischarge("elemental discharge", 10000,
					usingPlayerId);
		} else if (abilityID == 6) {
			return new Healcharge("heal", usingPlayerId);
		} else if (abilityID == 7) {
			return new FakeClone("invis", usingPlayerId);
		}
		return null;
	}

	//increase this everytime you add an ability
	public int getNumberOfAbilities() {
		return 8;
	}

	public Image getSpellIconFromId(int id) {
		return abilityIcons[id];
	}

	public Image getSmallSpellIconFromId(int id) {
		return smallAbilityIcons[id];
	}
	
	public AbilityType getAbilityType(int id){
		
		AbilityType abilityType;
		
		switch(id){
		case 0:
			abilityType = AbilityType.FIRE;
			break;
		case 1:
			abilityType = AbilityType.MAGIC;
			break;
		case 2:
			abilityType = AbilityType.PHYSICAL;
			break;
		case 3:
			abilityType = AbilityType.MAGIC;
			break;
		case 4:
			abilityType = AbilityType.FROST;
			break;
		case 5:
			abilityType = AbilityType.ULTIMATE;
			break;
		case 6:
			abilityType = AbilityType.HEAL;
			break;
		case 7:
			abilityType = AbilityType.MAGIC;
			break;
		default:
			abilityType = null;
		}
			
		return abilityType;
		
	}

	public String getDescriptionFromId(int id) {

		String description;
		switch (id) {
		case 0:
			description = "FIREBALL\n"
					+ "Shoots Fireball towards mouse location\n\n" 
					+ "Ignites for 10 damage over 10 sec\n"
					+ "Damage: 10 damage or 20 damage if ignited\n"
					+ "Travels 800 pixels over 1 sec\n"
					+ "Cost: 10 energy, Casttime: 0.5 sec\n"
					+ "Cooldown: 0.5 sec\n\n"
					+ "Is castable while moving: true";
			break;

		case 1:
			description = "MASS POLYMORPH\n"
					+ "Spawns area at mouse location that polymorphs enemies inside\n\n"
					+ "Duration: 5 sec\n"
					+ "Cost: 40 energy, Casttime: 1 sec\n"
					+ "Cooldown: 0 sec\n\n"
					+ "Polymorph: 3 second silence with 80% slow\n\n"
					+ "Is castable while moving: false";
			break;
		case 2:
			description = "RENGAR E++\n"
					+ "Throws a bola towards mouse location\n"
					+ "Spawns another ability copy\nbehind target on hit\n\n"
					+ "damage: "
					+ BolaEffect.getDamage() + ", Slow: 30% - 2 sec\n"
					+ "Travels 1000 pixels over 1 sec\n"
					+ "Cost: 20 energy, Casttime: 0 sec\n"
					+ "Cooldown: 5 sec\n\n"
					+ "Is castable while moving: true\n\n";
			break;
		case 3:
			description = "TELEPORT\n"
					+ "Teleports yourself to target location\n\n"
					+ "Condition: Mouse within 500 pixels of player\n"
					+ "Cost: 20 energy, Casttime: 0 sec\n"
					+ "Cooldown: 6 sec\n\n"
					+ "Is castable while moving: true";
			break;
		case 4:
			description = "ICELANCE\n"
					+ "Shoots Icelance towards mouse location\n"
					+ "Applies chill, or freeze if target already chilled\n\n"
					+ "Travels 1000 pixels over 1 sec\n"
					+ "Damage: 10, Cooldown: 0.5 sec\n"
					+ "Cost: 10 energy, Casttime: 0 sec\n"
					+ "Is castable while moving: true\n\n"
					+ "Chill: 70% slow - 3 sec\n"
					+ "Freeze: 100% slow - 3 sec\n";
			break;
		case 5:
			description = "PLACEBO HALLUCINATIONS\n"
					+ "Shoots elemental ball towards mouse location\n"
					+ "Activate again to detonate the ball\n"
					+ "sending out 4 Fireballs and 4 Icelance in 8 directions\n\n"
					+ "Travels 5000 pixels over 10 sec\n"
					+ "Cost: 50 energy, Casttime: 1 sec\n"
					+ "Slow: 30% - 2 sec, Cooldown: 15 sec\n\n"
					+ "Is castable while moving: false";
			break;
		case 6:
			description = "BONG SMOKE\n"
					+ "Teleports to mouse location and begins channeling (~3.1 sec)\n"
					+ "Healing players withing 500 pixels\n"
					+ "Activate again to interrupt spell and return to previous location\n"
					+ "Heals 4 times every 1 sec (5, 7, 11, 17)\n"
					+ "On 4th tick(~3.1 sec) spell will be \n"
					+ "   interrupted and cooldown reduced by 2 sec\n\n"
					+ "Condition: Mouse within 500 pixels of player\n"
					+ "Cost: 30 energy, Casttime: 0 sec\n"
					+ "Cooldown: 10 sec, Is castable while moving: true\n";
			break;
		case 7:
			description = "TROLL PASS\n"
					+ "Makes you invisible and summons smoke clone of yourself \n"
					+ "that will travel in your current direction\n\n"
					+ "Cost: 30 energy, Casttime: 0 sec\n"
					+ "Cooldown: 15 seconds, Duration: 4 sec\n\n"
					+ "Is castable while moving: true\n";
			break;
		default:
			description = "";

			
		}
		
		return description;
	}
}
