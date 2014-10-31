package networking;

import game.Model;
import game.Model.Team;

import java.io.IOException;

import networking.Packet.Packet0LoginRequest;
import networking.Packet.Packet1LoginAnswer;
import networking.Packet.Packet2Message;
import networking.Packet.Packet3PlayerSender;
import networking.Packet.Packet4AddPlayer;
import networking.Packet.Packet5StartThread;
import networking.Packet.Packet6UseAbility;
import networking.Packet.Packet7AddAbility;
import networking.Packet.Packet8SetTeam;
import abilities.Ability;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class BlazeClient extends Network implements Runnable{

	private Client client;
	private boolean isRunning;
	private Thread netThread;
	
	public BlazeClient(String name, String IP) throws IOException{
		super(name);
		client = new Client();
		registerPackets();
		
		ClientListener  listener = new ClientListener(this);
		listener.init(client);
		client.addListener(listener);
		
		client.start();
		
		client.connect(60000, IP, 54555, 54777);
		
		
		
	}
	
	private void registerPackets(){
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet3PlayerSender.class);
		kryo.register(Packet4AddPlayer.class);
		kryo.register(Packet5StartThread.class);
		kryo.register(Packet6UseAbility.class);
		kryo.register(Packet7AddAbility.class);
		kryo.register(Packet8SetTeam.class);
	}

	public void startNetworkThread() {
		isRunning = true;
		netThread = new Thread(this, "ClientThread");
		netThread.start();
		
	}

	@Override
	public void run() {
		
		while(isRunning){
			sendPlayerPosition();
			
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				System.out.println("Client got interuppted");
			}
		}
	}

	private void sendPlayerPosition() {
		
		Packet3PlayerSender playerSender = new Packet3PlayerSender();
		playerSender.xPos = Model.model.getMyself().getXPos();
		playerSender.yPos = Model.model.getMyself().getYPos();
		playerSender.vectorX = Model.model.getMyself().getVectorX();
		playerSender.vectorY = Model.model.getMyself().getVectorY();
		playerSender.ID = Model.model.getID();
		
		client.sendUDP(playerSender);
	}

	@Override
	public void sendUDPToAll(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTCPToAll(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAbility(int id, int abilityNumber, float mouseGameX,
			float mouseGameY) {
		
		Packet6UseAbility abilitySender = new Packet6UseAbility();
		abilitySender.abilityNumber = abilityNumber;
		abilitySender.playerID = id;
		abilitySender.mouseGameX = mouseGameX;
		abilitySender.mouseGameY = mouseGameY;
		client.sendTCP(abilitySender);
	}

	@Override
	public void sendAddAbility(int playerID, int abilityID, int abilityNumber) {
		Packet7AddAbility abilitySender = new Packet7AddAbility();
		abilitySender.playerID = playerID;
		abilitySender.abilityID = abilityID;
		abilitySender.abilityNumber = abilityNumber;
		client.sendTCP(abilitySender);
		
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
		client.sendTCP(teamSetter);
		
	}
	
}
