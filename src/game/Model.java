package game;

import java.io.IOException;
import java.util.ArrayList;

import map.Level;
import networking.BlazeClient;
import networking.BlazeServer;
import networking.Network;
import networking.Packet.Packet5StartThread;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import abilities.Ability;
import utilities.AbilityCreator;
import utilities.SpellAreaOfEffectCreator;
import utilities.TextureHandler;
import entities.AnimatedDecoration;
import entities.Entity;
import entities.Player;
import entities.Player.Clothes;
import entities.Player.Gender;
import entities.Player.Hair;
import entities.Player.Weapon;
import entities.SpellAreaOfEffect;
import entities.Terrain;
import gui.GuiEntity;

public class Model {

	private Player myself;
	private String name;
	private float playerSpeed;

	private int spellEffectIdCounter;

	private boolean isGaming;

	private Network network;

	public enum NetState {
		SERVER, CLIENT
	};

	public enum Team {
		GREEN, BROWN
	};

	private NetState modelNetState;

	private ArrayList<Entity> terrain;
	private ArrayList<SpellAreaOfEffect> activeSpells;
	private ArrayList<GuiEntity> activeGui;
	private ArrayList<AnimatedDecoration> temporaryDecorations;
	private ArrayList<Integer> temporaryDecorationTimers;

	private ArrayList<Player> otherPlayers;

	private int id;
	private float cameraX;
	private float cameraY;
	
	private int screenWidth;
	private int screenHeight;

	private Level level;
	private float mouseGameX;
	private float mouseGameY;

	public static Model model;

	public Model() {
		playerSpeed = 30;
		id = -1;
		otherPlayers = new ArrayList<Player>();
		isGaming = false;
		activeSpells = new ArrayList<SpellAreaOfEffect>();
		activeGui = new ArrayList<GuiEntity>();
		temporaryDecorations = new ArrayList<AnimatedDecoration>();
		temporaryDecorationTimers = new ArrayList<Integer>();
		spellEffectIdCounter = 0;
	}
	
	public void setScreenSize(int width, int height){
		screenWidth = width;
		screenHeight = height;
	}

	public static Model getModel() {
		return model;
	}

	public void initModel() {
		terrain = new ArrayList<Entity>();
		terrain.add(new Terrain(300, 300, null,
				new Rectangle(300, 300, 50, 50), TextureHandler.getInstance()
						.getImageByName("red.png")));
	}

	public Player getMyself() {
		return myself;
	}

	public ArrayList<Entity> getTerrain() {
		return this.terrain;
	}

	public void addActiveSpell(SpellAreaOfEffect spell) {
		activeSpells.add(spell);
	}

	public void removeActiveSpell(SpellAreaOfEffect spell) {
		activeSpells.remove(spell);
	}

	public void handlePlayerMovement(boolean upKeyPressed,
			boolean downKeyPressed, boolean leftKeyPressed,
			boolean rightKeyPressed) {
		if (upKeyPressed && !downKeyPressed) {
			if (leftKeyPressed && !rightKeyPressed) {
				myself.setVectorByDegree(playerSpeed, 225);
			} else if (rightKeyPressed && !leftKeyPressed) {
				myself.setVectorByDegree(playerSpeed, 315);
			} else {
				myself.setVectorByDegree(playerSpeed, 270);
			}
		} else if (downKeyPressed && !upKeyPressed) {
			if (leftKeyPressed && !rightKeyPressed) {
				myself.setVectorByDegree(playerSpeed, 135);
			} else if (rightKeyPressed && !leftKeyPressed) {
				myself.setVectorByDegree(playerSpeed, 45);
			} else {
				myself.setVectorByDegree(playerSpeed, 90);
			}
		} else {
			if (leftKeyPressed && !rightKeyPressed) {
				myself.setVectorByDegree(playerSpeed, 180);
			} else if (rightKeyPressed && !leftKeyPressed) {
				myself.setVectorByDegree(playerSpeed, 0);
			} else {
				myself.setVectorByDegree(0, 0);
			}
		}
	}

	public void setName(String nameStr) {
		name = nameStr;
	}

	public void createHost() {
		try {
			network = new BlazeServer(name);
			id = 1;
			modelNetState = NetState.SERVER;
			myself = new Player(50f, 50f, new Vector2f(0, 0), new Rectangle(
					50f, 50f, 50, 50), 100, name, id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createClient(String ip) {
		try {
			network = new BlazeClient(name, ip);
			modelNetState = NetState.CLIENT;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setID(int id) {
		this.id = id;
		myself = new Player(50f, 50f, new Vector2f(0, 0), new Rectangle(50f,
				50f, 50, 50), 100, name, id);
	}

	public int getID() {
		return id;
	}

	public void addPlayer(Player player) {
		boolean playerAlreadyExists = false;
		if (player.getID() == id) {
			playerAlreadyExists = true;
		}
		for (int i = 0; i < otherPlayers.size(); i++) {
			if (otherPlayers.get(i).getID() == player.getID()) {
				playerAlreadyExists = true;
			}
		}

		if (!playerAlreadyExists) {
			otherPlayers.add(player);
		}
	}

	public void removePlayer(Player player) {
		otherPlayers.remove(player);
	}

	public void removePlayer(int id) {
		for (int i = 0; i < otherPlayers.size(); i++) {
			if (otherPlayers.get(i).getID() == id) {
				otherPlayers.remove(i);
			}
		}
	}

	public Player getPlayer(int id) {
		if (id == getMyself().getID()) {
			return getMyself();
		}
		for (int i = 0; i < otherPlayers.size(); i++) {
			if (id == otherPlayers.get(i).getID()) {
				return otherPlayers.get(i);
			}
		}
		return null;
	}

	public void updatePlayer(float xPos, float yPos, float vectorX,
			float vectorY, int id, boolean isCasting) {
		if (id != this.id) {
			for (int i = 0; i < otherPlayers.size(); i++) {
				if (id == otherPlayers.get(i).getID()) {
					otherPlayers.get(i).setPos(xPos, yPos);
					otherPlayers.get(i).setVectorWithoutSpeedModifier(vectorX,
							vectorY);
					otherPlayers.get(i).setIsCasting(isCasting);
				}
			}
		}
	}

	public boolean isServer() {
		if (modelNetState == NetState.SERVER) {
			return true;
		} else {
			return false;
		}
	}

	public void startGame() {
		if (getMyself().getTeam() == Team.GREEN) {
			getMyself().setPos(-2650, 1550);
		} else if (getMyself().getTeam() == Team.BROWN) {
			getMyself().setPos(2650, 1550);
		}
		network.startNetworkThread();
		isGaming = true;
		sendAbilities();
	}

	private void sendAbilities() {
		for (int i = 0; i < 4; i++) {
			if (getMyself().getAbility(i + 1) != null) {
				network.sendAddAbility(getMyself().getID(), getMyself()
						.getAbility(i + 1).getID(), i + 1);
			}

		}

	}

	public ArrayList<Player> getOtherPlayers() {
		return otherPlayers;

	}

	public void startClients() {
		network.sendTCPToAll(new Packet5StartThread());

	}

	public boolean isGaming() {
		return isGaming;
	}

	public void useAbility(int abilityNumber, int mouseXPos, int mouseYPos) {
		System.out.println("ability: " + abilityNumber + " used");
//		we keep this in model now so not needed, might be this should be refactored some..
//		float mouseGameX = cameraX + mouseXPos;
//		float mouseGameY = cameraY + mouseYPos;

		if (abilityNumber > 0 && abilityNumber < 5) {

			if (getMyself().isAbleToCast()) {
				if (getMyself().getAbility(abilityNumber) != null && getMyself().getAbility(abilityNumber).getMsSinceLastUse() > getMyself().getAbility(abilityNumber).getCooldown() && getMyself().getAbility(abilityNumber).isCastable(-1, mouseGameX, mouseGameY)) {
					if (getMyself().getAbility(abilityNumber).getCastTime() == 0) {
						if (getMyself()
								.reduceEnergy(
										getMyself().getAbility(abilityNumber)
												.getCost())) {
							Ability ability = getMyself().getAbility(
									abilityNumber);
							int spellEffectId[] = new int[ability
									.getSpellEffectAmount()];
							for (int i = 0; i < ability.getSpellEffectAmount(); i++) {
								spellEffectId[i] = getNextSpellEffectId();
							}

							network.sendAbility(getMyself().getID(),
									abilityNumber, mouseGameX, mouseGameY,
									spellEffectId);
							executeAbility(getMyself().getID(), abilityNumber,
									mouseGameX, mouseGameY, spellEffectId);
						}
					} else {
						getMyself().startCastedAbility(abilityNumber,
								mouseGameX, mouseGameY);
					}
				}
			}
		} else if (abilityNumber == 5) {

			if (getMyself().getSpeedDurationLeft() <= 0) {
				if (getMyself().reduceEnergy(20)) {
					getMyself().applyMovementModifyer(1.5f, 4000);
				}
			} else {
				if (getMyself().reduceEnergy(60)) {
					getMyself().applyMovementModifyer(2.5f, 1000);
				}
			}
		}

	}

	public void finishCastingAbility(int abilityNumber, float mouseGameX,
			float mouseGameY) {
		if (getMyself().reduceEnergy(
				getMyself().getAbility(abilityNumber).getCost())) {
			Ability ability = getMyself().getAbility(abilityNumber);
			int spellEffectId[] = new int[ability.getSpellEffectAmount()];
			for (int i = 0; i < ability.getSpellEffectAmount(); i++) {
				spellEffectId[i] = getNextSpellEffectId();
			}

			network.sendAbility(getMyself().getID(), abilityNumber, mouseGameX,
					mouseGameY, spellEffectId);
			executeAbility(getMyself().getID(), abilityNumber, mouseGameX,
					mouseGameY, spellEffectId);
		}
	}
	
	public void finishCastingAbility(int abilityNumber){
		finishCastingAbility(abilityNumber, mouseGameX, mouseGameY);
	}
	
	//if you let more than one client or host call this your fucked
	public void launchCustomSpellAreaOfEffect(int effectId, float xPos, float yPos, float vectorX, float vectorY, int duration, int playerUsedId, int spellEffectId){
		network.sendCustomSpellAreaOfEffect(effectId, xPos, yPos, vectorX, vectorY, duration, playerUsedId, spellEffectId);
		recieveCustomSpellAreaOfEffect(effectId, xPos, yPos, vectorX, vectorY, duration, playerUsedId, spellEffectId);
		
	}
	
	public void recieveCustomSpellAreaOfEffect(int effectId, float xPos, float yPos, float vectorX, float vectorY, int duration, int playerUsedId, int spellEffectId){
		SpellAreaOfEffect spell = SpellAreaOfEffectCreator.getNewEffect(effectId, xPos, yPos, new Vector2f(vectorX, vectorY), duration, playerUsedId, spellEffectId);
		addActiveSpell(spell);
	}
	

	public void executeAbility(int id, int abilityNumber, float mouseGameX,
			float mouseGameY, int spellEffectId[]) {
		getPlayer(id).getAbility(abilityNumber).useAbility(id, mouseGameX,
				mouseGameY, spellEffectId);

	}
	
	public void useMouseAttack(int mouseButton, int x, int y){
		if(getMyself().isAbleToCast() && getMyself().isMouseAttackReady()){
			network.sendMouseAttack(getMyself().getID(), mouseButton, mouseGameX, mouseGameY);
			executeMouseAttack(getMyself().getID(), mouseButton, mouseGameX, mouseGameY);
		}
	}
	
	public void executeMouseAttack(int playerId, int mouseButton, float mouseGameX, float mouseGameY){
		getPlayer(id).useMouseAttack(mouseButton, mouseGameX, mouseGameY);
	}
	

	public void setPlayerSpeed(float playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	public float getPlayerSpeed() {
		return playerSpeed;
	}

	public void setCamera(float cameraX, float cameraY) {
		this.cameraX = cameraX;
		this.cameraY = cameraY;
	}
	
	public float getCameraX(){
		return cameraX;
	}
	
	public float getCameraY(){
		return cameraY;
	}
	
	public void updateMouseGameXY(int mouseXPos, int mouseYPos){
		mouseGameX = cameraX + mouseXPos;
		mouseGameY = cameraY + mouseYPos;
	}

	public void updateSpells(int delta) {
		for (int i = 0; i < activeSpells.size(); i++) {
			activeSpells.get(i).update(delta, getOtherPlayers(), getMyself());
		}

	}

	public ArrayList<SpellAreaOfEffect> getActiveSpells() {
		return activeSpells;
	}

	public void checkForExpiredSpells() {
		for (int i = 0; i < activeSpells.size(); i++) {
			if (activeSpells.get(i).getDurationLeft() < 0) {
				activeSpells.get(i).onExpire();
				activeSpells.remove(i);
			}
		}

	}

	public void setPlayerAbility(int playerID, int abilityID, int abilityNumber) {
		getPlayer(playerID).setAbility(AbilityCreator.getInstance().getNewAbility(abilityID, playerID),
				abilityNumber);
	}

	public void setAndSendTeam(Team team) {
		network.sendSetTeam(getMyself().getID(), team);
		setPlayerTeam(getMyself().getID(), team);
	}

	public void setPlayerTeam(int playerId, Team team) {
		this.getPlayer(playerId).setTeam(team);
	}

	public int getNextSpellEffectId() {
		return spellEffectIdCounter++;
	}

	public void sendSpellHitReport(int spellCombinedId, int playerHitId) {
		network.sendSpellHitReport(spellCombinedId, playerHitId);

	}

	public void recieveSpellHitReport(int combinedId, int playerHitId) {
		if (getSpellByCombinedId(combinedId) != null) {
			getSpellByCombinedId(combinedId).applyEffect(
					Model.model.getPlayer(playerHitId));
		}
	}

	public SpellAreaOfEffect getSpellByCombinedId(int combinedId) {
		for (int i = 0; i < activeSpells.size(); i++) {
			if (activeSpells.get(i).getCombinedId() == combinedId) {
				return activeSpells.get(i);
			}
		}
		return null;
	}

	public Level getLevel() {
		return level;
	}

	public ArrayList<GuiEntity> getActiveGui() {
		return activeGui;
	}

	public void addActiveGui(GuiEntity guiEntity) {
		activeGui.add(guiEntity);
	}

	public void clearGui() {
		activeGui.clear();
	}

	public void updateGui(int delta) {
		for (int i = 0; i < activeGui.size(); i++) {
			activeGui.get(i).update(delta);
		}

	}

	public void initLevel(Level level) {
		this.level = level;
	}
	
	public boolean isOnScreen(float xPos, float yPos){
		if(xPos > cameraX && xPos < cameraX + screenWidth && yPos > cameraY && yPos < cameraY + screenHeight){
			return true;
		}
		return false;
	}
	
	public int getScreenWidth(){
		return screenWidth;
	}
	
	public int getScreenHeight(){
		return screenHeight;
	}

	public void setAndSendPlayerCustomization(Gender gender,
			Clothes clothes, Hair hair, Weapon weapon) {
		getMyself().setCustomization(gender, clothes, hair, weapon);
		network.sendPlayerCustomization(getMyself().getID(), gender.ordinal(), clothes.ordinal(), hair.ordinal(), weapon.ordinal());
	}

	public void recievePlayerCustomizer(int playerId, int gender, int clothes,
			int hair, int weapon) {
		this.getPlayer(playerId).setCustomization(Gender.values()[gender], Clothes.values()[clothes], Hair.values()[hair], Weapon.values()[weapon]);
		
	}
	
	public void addTemporaryDecoration(AnimatedDecoration decoration, int duration){
		temporaryDecorations.add(decoration);
		temporaryDecorationTimers.add(duration);
	}
	
	public ArrayList<AnimatedDecoration> getTemporaryDecorations(){
		return temporaryDecorations;
	}
	
	public void updateTemporaryDecorations(int delta){
		for(int i = 0; i < temporaryDecorations.size(); i++){
			temporaryDecorations.get(i).update(delta);
			temporaryDecorationTimers.set(i, temporaryDecorationTimers.get(i) - delta);
		}
		
		for(int i = 0; i < temporaryDecorations.size(); i++){
			if(temporaryDecorationTimers.get(i) < 0){
				temporaryDecorations.remove(i);
				temporaryDecorationTimers.remove(i);
			}
		}
	}
	
	public boolean isNonSolidNonNullPosition(float xPos, float yPos){
		if(level.getTileAtPos(xPos, yPos) == null){
			return false;
		}
		if(level.getTileAtPos(xPos, yPos).isSolid()){
			return false;
		}else{
			return true;
		}
	}
}
