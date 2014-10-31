package networking;

import utilities.AbilityCreator;
import entities.Player;
import game.Model;
import networking.Packet.Packet0LoginRequest;
import networking.Packet.Packet1LoginAnswer;
import networking.Packet.Packet2Message;
import networking.Packet.Packet3PlayerSender;
import networking.Packet.Packet4AddPlayer;
import networking.Packet.Packet5StartThread;
import networking.Packet.Packet6UseAbility;
import networking.Packet.Packet7AddAbility;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ServerListener extends Listener{

	private BlazeServer server;
	
	public ServerListener(BlazeServer server){
		super();
		this.server = server;
	}
	
	@Override
	public void connected(Connection arg0) {
		Log.info("[SERVER] Someone has to connect");
		System.out.println("someone is connecting");
	}
	
	@Override
	public void disconnected(Connection connection) {
		Log.info("[SERVER] Someone has to disconnect");
		server.removeClient(connection);
	}
	
	@Override
	public void received(Connection connection, Object obj) {
		
		if(obj instanceof Packet0LoginRequest){
			System.out.println("Login request recieved");
			Packet0LoginRequest loginRequest = ((Packet0LoginRequest)obj);
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			server.addClient(connection);
			loginAnswer.accepted = true;
			int id = server.getNextID();
			
			
			
			Model.model.addPlayer(new Player(50f, 50f, loginRequest.name, id));
			
			loginAnswer.ID = id;
			connection.sendTCP(loginAnswer);
			
			Packet4AddPlayer addPlayer = new Packet4AddPlayer();
			addPlayer.xPos = 50f;
			addPlayer.yPos = 50f;
			addPlayer.ID = id;
			addPlayer.name = loginRequest.name;
			server.sendTCPToAll(addPlayer);
			
			for(int i = 0; i < Model.model.getOtherPlayers().size(); i++){
				addPlayer.xPos = Model.model.getOtherPlayers().get(i).getXPos();
				addPlayer.yPos = Model.model.getOtherPlayers().get(i).getYPos();
				addPlayer.ID = Model.model.getOtherPlayers().get(i).getID();
				addPlayer.name = Model.model.getOtherPlayers().get(i).getName();
				connection.sendTCP(addPlayer);
			}
			
			addPlayer.xPos = Model.model.getMyself().getXPos();
			addPlayer.yPos = Model.model.getMyself().getYPos();
			addPlayer.ID = Model.model.getID();
			addPlayer.name = Model.model.getMyself().getName();
			connection.sendTCP(addPlayer);
			
		}else if(obj instanceof Packet2Message){
			
			String message = ((Packet2Message) obj).message;
			Log.info(message);
			
		}else if(obj instanceof Packet3PlayerSender){
			
			Packet3PlayerSender playerSender = (Packet3PlayerSender)obj;
			Model.model.updatePlayer(playerSender.xPos, playerSender.yPos, playerSender.vectorX, playerSender.vectorY, playerSender.ID);
			
		}else if(obj instanceof Packet4AddPlayer){
			
		}else if(obj instanceof Packet5StartThread){
			
		}else if(obj instanceof Packet6UseAbility){
			Packet6UseAbility useAbility = (Packet6UseAbility)obj;
			server.forwardTCPToAll(useAbility, connection);
			Model.model.executeAbility(useAbility.playerID, useAbility.abilityNumber, useAbility.mouseGameX, useAbility.mouseGameY);
		}else if(obj instanceof Packet7AddAbility){
			Packet7AddAbility addAbility = (Packet7AddAbility)obj;
			server.forwardTCPToAll(addAbility, connection);
			Model.model.setPlayerAbility(addAbility.playerID, addAbility.abilityID, addAbility.abilityNumber);
		}
	}
}
	
