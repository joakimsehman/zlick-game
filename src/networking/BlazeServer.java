package networking;

import entities.Player.Clothes;
import entities.Player.Gender;
import entities.Player.Hair;
import entities.Player.Weapon;
import game.Model;
import game.Model.Team;

import java.io.IOException;
import java.util.ArrayList;









import org.newdawn.slick.geom.Vector2f;

import networking.Packet.*;
import abilities.Ability;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class BlazeServer extends Network implements Runnable{

	private int TCPPORT, UDPPORT;
	private Server server;
	private int idCounter;
	private boolean isRunning;
	private Thread netThread;
	private ArrayList<Connection> clients;
	
	public BlazeServer(String name) throws IOException{
		super(name);
		//SET VALID VALUEE BEFORE TESTING
		TCPPORT = 54555;
		UDPPORT = 54777;
		idCounter = 1;
		clients = new ArrayList<Connection>();
		
		Log.set(Log.LEVEL_DEBUG);
		server = new Server();
		registerPackets();
		server.addListener(new ServerListener(this));
		server.bind(TCPPORT, UDPPORT);
		server.start();
		
	}
	
	private void registerPackets(){
		
		Kryo kryo = server.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet3PlayerSender.class);
		kryo.register(Packet4AddPlayer.class);
		kryo.register(Packet5StartThread.class);
		kryo.register(Packet6UseAbility.class);
		kryo.register(Packet7AddAbility.class);
		kryo.register(Packet8SetTeam.class);
		kryo.register(Packet9SpellHit.class);
		kryo.register(Packet10CustomSpellEffect.class);
		kryo.register(Packet11MouseAttack.class);
		kryo.register(Packet12PlayerCustomizer.class);
		kryo.register(int[].class);
		
	}

	public int getNextID() {
		idCounter++;
		return idCounter;
	}

	@Override
	public void startNetworkThread() {
		isRunning = true;
		netThread = new Thread(this, "ServerThread");
		netThread.start();
		
	}

	@Override
	public void run() {
		while(isRunning){
			sendPlayerPositionToAll();
			sendOtherPlayersToAll();
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				System.out.println("Client got interuppted");
			}
		}
		
	}

	private void sendOtherPlayersToAll() {
		
		for(int i = 0; i < Model.model.getOtherPlayers().size(); i++){
			Packet3PlayerSender playerSender = new Packet3PlayerSender();
			playerSender.xPos = Model.model.getOtherPlayers().get(i).getXPos();
			playerSender.yPos = Model.model.getOtherPlayers().get(i).getYPos();
			playerSender.ID = Model.model.getOtherPlayers().get(i).getID();
			playerSender.vectorX = Model.model.getOtherPlayers().get(i).getVectorX();
			playerSender.vectorY = Model.model.getOtherPlayers().get(i).getVectorY();
			playerSender.isCasting = Model.model.getOtherPlayers().get(i).isCasting();
			sendUDPToAll(playerSender);
		}
		
	}

	private void sendPlayerPositionToAll() {
		Packet3PlayerSender playerSender = new Packet3PlayerSender();
		playerSender.xPos = Model.model.getMyself().getXPos();
		playerSender.yPos = Model.model.getMyself().getYPos();
		playerSender.ID = Model.model.getID();
		playerSender.vectorX = Model.model.getMyself().getVectorX();
		playerSender.vectorY = Model.model.getMyself().getVectorY();
		playerSender.isCasting = Model.model.getMyself().isCasting();
		sendUDPToAll(playerSender);
		
	}
	
	public void sendUDPToAll(Object obj){
		for(int i = 0; i < clients.size(); i++){
			clients.get(i).sendUDP(obj);
		}
	}
	
	public void sendTCPToAll(Object obj){
		for(int i = 0; i < clients.size(); i++){
			clients.get(i).sendTCP(obj);
		}
	}
	
	public void forwardTCPToAll(Object obj, Connection connection){
		for(int i = 0; i < clients.size(); i++){
			if(connection != clients.get(i)){
				clients.get(i).sendTCP(obj);
			}
		}
	}
	
	public void forwardUDPToAll(Object obj, Connection connection){
		for(int i = 0; i < clients.size(); i++){
			if(connection != clients.get(i)){
				clients.get(i).sendUDP(obj);
			}
		}
	}

	public void addClient(Connection connection) {
		clients.add(connection);
	}
	
	public void removeClient(Connection connection){
		clients.remove(connection);
	}

	@Override
	public void sendAbility(int id, int abilityNumber, float mouseGameX,
			float mouseGameY, int spellEffectId[]) {
		Packet6UseAbility abilitySender = new Packet6UseAbility();
		abilitySender.abilityNumber = abilityNumber;
		abilitySender.playerID = id;
		abilitySender.mouseGameX = mouseGameX;
		abilitySender.mouseGameY = mouseGameY;
		abilitySender.spellEffectId = spellEffectId;
		sendTCPToAll(abilitySender);
		
	}

	@Override
	public void sendAddAbility(int playerID, int abilityID, int abilityNumber) {
		Packet7AddAbility abilitySender = new Packet7AddAbility();
		abilitySender.playerID = playerID;
		abilitySender.abilityID = abilityID;
		abilitySender.abilityNumber = abilityNumber;
		sendTCPToAll(abilitySender);
		
	}

	@Override
	public void sendSetTeam(int id, Team team) {
		Packet8SetTeam teamSetter = new Packet8SetTeam();
		teamSetter.playerID = id;
		if(Model.Team.GREEN == team){
			teamSetter.teamNumber = 1;
		}else if(Model.Team.BROWN == team){
			teamSetter.teamNumber = 2;
		}
		sendTCPToAll(teamSetter);
	}

	@Override
	public void sendSpellHitReport(int spellCombinedId, int playerHitId) {
		Packet9SpellHit spellHit = new Packet9SpellHit();
		spellHit.combinedId = spellCombinedId;
		spellHit.playerHitId = playerHitId;
		sendTCPToAll(spellHit);
	}

	@Override
	public void sendCustomSpellAreaOfEffect(int effectId, float xPos,
			float yPos, float vectorX, float vectorY, int duration, int playerUsedId,
			int spellEffectId) {
		Packet10CustomSpellEffect customSpellEffect = new Packet10CustomSpellEffect();
		customSpellEffect.effectId = effectId;
		customSpellEffect.xPos = xPos;
		customSpellEffect.yPos = yPos;
		customSpellEffect.vectorX = vectorX;
		customSpellEffect.vectorY = vectorY;
		customSpellEffect.duration = duration;
		customSpellEffect.playerUsedId = playerUsedId;
		customSpellEffect.spellEffectId = spellEffectId;
		sendTCPToAll(customSpellEffect);
		
	}

	@Override
	public void sendMouseAttack(int id, int mouseButton, float mouseGameX,
			float mouseGameY) {
		Packet11MouseAttack mouseAttack = new Packet11MouseAttack();
		mouseAttack.id = id;
		mouseAttack.mouseButton = mouseButton;
		mouseAttack.mouseGameX = mouseGameX;
		mouseAttack.mouseGameY = mouseGameY;
		sendTCPToAll(mouseAttack);
		
	}

	@Override
	public void sendPlayerCustomization(int playerId, int gender,
			int clothes, int hair, int weapon) {
		Packet12PlayerCustomizer playerCustomizer = new Packet12PlayerCustomizer();
		playerCustomizer.playerId = playerId;
		playerCustomizer.gender = gender;
		playerCustomizer.clothes = clothes;
		playerCustomizer.hair = hair;
		playerCustomizer.weapon = weapon;
		sendTCPToAll(playerCustomizer);
		
	}

	
	
	
}
