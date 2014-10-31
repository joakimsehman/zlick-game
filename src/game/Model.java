package game;

import java.io.IOException;
import java.util.ArrayList;

import networking.BlazeClient;
import networking.BlazeServer;
import networking.Network;
import networking.Packet.Packet5StartThread;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import abilities.Ability;
import abilities.SimpleDamageAbility;
import utilities.AbilityCreator;
import utilities.TextureHandler;
import entities.Entity;
import entities.Player;
import entities.SpellAreaOfEffect;
import entities.Terrain;

public class Model {

	private Player myself;
	private String name;
	private float playerSpeed;

	private boolean isGaming;

	private Network network;

	public enum netState {
		SERVER, CLIENT
	};

	private netState modelNetState;

	private ArrayList<Entity> terrain;
	private ArrayList<SpellAreaOfEffect> activeSpells;

	private ArrayList<Player> otherPlayers;

	private int id;
	private float cameraX;
	private float cameraY;

	public static Model model;

	public Model() {
		playerSpeed = 30;
		id = -1;
		otherPlayers = new ArrayList<Player>();
		isGaming = false;
		activeSpells = new ArrayList<SpellAreaOfEffect>();
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
		System.out.println("name set to: " + nameStr);
		name = nameStr;
	}

	public void createHost() {
		try {
			network = new BlazeServer(name);
			id = 1;
			modelNetState = netState.SERVER;
			myself = new Player(50f, 50f, new Vector2f(0, 0), new Rectangle(
					50f, 50f, 50, 50), 100, name, id);
			myself.setAbility(AbilityCreator.getNewAbility(0), 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createClient(String ip) {
		try {
			network = new BlazeClient(name, ip);
			modelNetState = netState.CLIENT;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setID(int id) {
		this.id = id;
		myself = new Player(50f, 50f, new Vector2f(0, 0), new Rectangle(50f,
				50f, 50, 50), 100, name, id);
		myself.setAbility(AbilityCreator.getNewAbility(0), 1);
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
			float vectorY, int id) {
		if (id != this.id) {
			for (int i = 0; i < otherPlayers.size(); i++) {
				if (id == otherPlayers.get(i).getID()) {
					otherPlayers.get(i).setPos(xPos, yPos);
					otherPlayers.get(i).setVectorWithoutSpeedModifyer(vectorX,
							vectorY);
				}
			}
		}
	}

	public boolean isServer() {
		if (modelNetState == netState.SERVER) {
			return true;
		} else {
			return false;
		}
	}

	public void startGame() {
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

		float mouseGameX = cameraX + mouseXPos;
		float mouseGameY = cameraY + mouseYPos;

		if (abilityNumber > 0 && abilityNumber < 5) {

			if (getMyself().getAbility(abilityNumber) != null) {
				if (getMyself().reduceEnergy(
						getMyself().getAbility(abilityNumber).getCost())) {
					Ability ability = getMyself().getAbility(abilityNumber);
					if (ability != null) {
						network.sendAbility(getMyself().getID(), abilityNumber,
								mouseGameX, mouseGameY);
						executeAbility(getMyself().getID(), abilityNumber,
								mouseGameX, mouseGameY);
					}
				}
			}
		} else if (abilityNumber == 5) {

			if (getMyself().getSpeedDurationLeft() <= 0) {
				if (getMyself().reduceEnergy(20)) {
					getMyself().applyMovementModifyer(2.0f, 4000);
				}
			} else {
				if (getMyself().reduceEnergy(40)) {
					getMyself().applyMovementModifyer(4.0f, 1000);
				}
			}
		}

	}

	public void executeAbility(int id, int abilityNumber, float mouseGameX,
			float mouseGameY) {
		getPlayer(id).getAbility(abilityNumber).useAbility(id, mouseGameX,
				mouseGameY);

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

	public void updateSpells(int delta) {
		for (int i = 0; i < activeSpells.size(); i++) {
			activeSpells.get(i).update(delta, null);
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
		getPlayer(playerID).setAbility(AbilityCreator.getNewAbility(abilityID),
				abilityNumber);
	}

}