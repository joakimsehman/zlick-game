package networking;

import entities.Player;
import game.Model;
import networking.Packet.Packet0LoginRequest;
import networking.Packet.Packet1LoginAnswer;
import networking.Packet.Packet2Message;
import networking.Packet.Packet3PlayerSender;
import networking.Packet.Packet4AddPlayer;
import networking.Packet.Packet5StartThread;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ClientListener extends Listener{

	private Client client;
	private BlazeClient blazeC;
	
	public ClientListener(BlazeClient bc){
		super();
		blazeC = bc;
	}
	
	public void init(Client client) {
		this.client = client;
	}
	
	
	@Override
	public void connected(Connection connection) {
		Log.info("[CLIENT] You have connected");
		System.out.println("connected");
		Packet0LoginRequest loginRequest = new Packet0LoginRequest();
		loginRequest.name = blazeC.getName();
		client.sendTCP(loginRequest);
		System.out.println("sent request");
	}
	
	@Override
	public void disconnected(Connection arg0) {
		Log.info("[CLIENT] You have disconnect");
		System.out.println("disconnected");
	}
	
	@Override
	public void received(Connection connection, Object obj) {
		
		if(obj instanceof Packet0LoginRequest){
			//server gets this
		}else if(obj instanceof Packet1LoginAnswer){
			Packet1LoginAnswer loginAnswer = ((Packet1LoginAnswer) obj);
			if(loginAnswer.accepted == true){
				Model.model.setID(loginAnswer.ID);
			}else{
				Model.model.setID(-1);
			}
		}
		else if(obj instanceof Packet2Message){
			String message = ((Packet2Message) obj).message;
			Log.info(message);
		}else if(obj instanceof Packet3PlayerSender){
			
			Packet3PlayerSender playerSender = (Packet3PlayerSender)obj;
			Model.model.updatePlayer(playerSender.xPos, playerSender.yPos, playerSender.vectorX, playerSender.vectorY, playerSender.ID);
			
		}else if(obj instanceof Packet4AddPlayer){
			Packet4AddPlayer addPlayer = (Packet4AddPlayer)obj;
			Model.model.addPlayer(new Player(addPlayer.xPos, addPlayer.yPos, addPlayer.name, addPlayer.ID));
		}else if(obj instanceof Packet5StartThread){
			Model.model.startGame();
		}
	}
	
}
