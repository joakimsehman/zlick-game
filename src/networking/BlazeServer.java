package networking;

import game.Model;

import java.io.IOException;
import java.util.ArrayList;


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

	public void addClient(Connection connection) {
		clients.add(connection);
	}
	
	public void removeClient(Connection connection){
		clients.remove(connection);
	}

	@Override
	public void sendAbility(int id, Ability ability, float mouseGameX,
			float mouseGameY) {
		
		Packet6AddAbility abilitySender = new Packet6AddAbility();
		abilitySender.abilityID = ability.getID();
		abilitySender.playerID = id;
		abilitySender.mouseGameX = mouseGameX;
		abilitySender.mouseGameY = mouseGameY;
		sendTCPToAll(abilitySender);
		
	}

	
	
	
}
