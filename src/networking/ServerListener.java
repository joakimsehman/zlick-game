package networking;

import utilities.AbilityCreator;
import entities.Player;
import game.Model;
import networking.Packet.Packet0LoginRequest;
import networking.Packet.Packet10CustomSpellEffect;
import networking.Packet.Packet11MouseAttack;
import networking.Packet.Packet12PlayerCustomizer;
import networking.Packet.Packet1LoginAnswer;
import networking.Packet.Packet2Message;
import networking.Packet.Packet3PlayerSender;
import networking.Packet.Packet4AddPlayer;
import networking.Packet.Packet5StartThread;
import networking.Packet.Packet6UseAbility;
import networking.Packet.Packet7AddAbility;
import networking.Packet.Packet8SetTeam;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ServerListener extends Listener {

	private BlazeServer server;

	public ServerListener(BlazeServer server) {
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
			Model.model.updatePlayer(playerSender.xPos, playerSender.yPos, playerSender.vectorX, playerSender.vectorY, playerSender.ID, playerSender.playerHealth, playerSender.isCasting);
			
		}else if(obj instanceof Packet4AddPlayer){
			
		}else if(obj instanceof Packet5StartThread){
			
		}else if(obj instanceof Packet6UseAbility){
			Packet6UseAbility useAbility = (Packet6UseAbility)obj;
			server.forwardTCPToAll(useAbility, connection);
			Model.model.executeAbility(useAbility.playerID, useAbility.abilityNumber, useAbility.mouseGameX, useAbility.mouseGameY, useAbility.spellEffectId);
		}else if(obj instanceof Packet7AddAbility){
			Packet7AddAbility addAbility = (Packet7AddAbility)obj;
			server.forwardTCPToAll(addAbility, connection);
			Model.model.setPlayerAbility(addAbility.playerID, addAbility.abilityID, addAbility.abilityNumber);
		}else if(obj instanceof Packet8SetTeam){
			Packet8SetTeam teamSetter = (Packet8SetTeam)obj;
			server.forwardTCPToAll(teamSetter, connection);
			if(teamSetter.teamNumber == 1){
				Model.model.setPlayerTeam(teamSetter.playerID, Model.Team.GREEN);
			}else if(teamSetter.teamNumber == 2){
				Model.model.setPlayerTeam(teamSetter.playerID, Model.Team.BROWN);
			}
		}else if(obj instanceof Packet10CustomSpellEffect){
			Packet10CustomSpellEffect customSpellEffect = (Packet10CustomSpellEffect)obj;
			server.forwardTCPToAll(customSpellEffect, connection);
			Model.model.recieveCustomSpellAreaOfEffect(customSpellEffect.effectId, customSpellEffect.xPos, customSpellEffect.yPos, customSpellEffect.vectorX, customSpellEffect.vectorY, customSpellEffect.duration, customSpellEffect.playerUsedId, customSpellEffect.spellEffectId);
		}else if(obj instanceof Packet11MouseAttack){
			Packet11MouseAttack mouseAttack = (Packet11MouseAttack)obj;
			server.forwardTCPToAll(mouseAttack, connection);
			Model.model.executeMouseAttack(mouseAttack.id, mouseAttack.mouseButton, mouseAttack.mouseGameX, mouseAttack.mouseGameY);
		}else if(obj instanceof Packet12PlayerCustomizer){
			Packet12PlayerCustomizer playerCustomizer = (Packet12PlayerCustomizer)obj;
			server.forwardTCPToAll(playerCustomizer, connection);
			Model.model.recievePlayerCustomizer(playerCustomizer.playerId, playerCustomizer.gender, playerCustomizer.clothes, playerCustomizer.hair,playerCustomizer.weapon);
		}
	}
}
