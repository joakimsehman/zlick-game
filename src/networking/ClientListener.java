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
import networking.Packet.Packet9SpellHit;

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
			Model.model.updatePlayer(playerSender.xPos, playerSender.yPos, playerSender.vectorX, playerSender.vectorY, playerSender.ID, playerSender.playerHealth, playerSender.isCasting);
			
		}else if(obj instanceof Packet4AddPlayer){
			Packet4AddPlayer addPlayer = (Packet4AddPlayer)obj;
			Model.model.addPlayer(new Player(addPlayer.xPos, addPlayer.yPos, addPlayer.name, addPlayer.ID));
		}else if(obj instanceof Packet5StartThread){
			Model.model.startGame();
		}else if(obj instanceof Packet6UseAbility){
			Packet6UseAbility addAbility = (Packet6UseAbility)obj;
			
			Model.model.executeAbility(addAbility.playerID, addAbility.abilityNumber, addAbility.mouseGameX, addAbility.mouseGameY, addAbility.spellEffectId);
		}else if(obj instanceof Packet7AddAbility){
			Packet7AddAbility addAbility = (Packet7AddAbility)obj;
			Model.model.setPlayerAbility(addAbility.playerID, addAbility.abilityID, addAbility.abilityNumber);
		}else if(obj instanceof Packet8SetTeam){
			Packet8SetTeam teamSetter = (Packet8SetTeam)obj;
			
			if(teamSetter.teamNumber == 1){
				Model.model.setPlayerTeam(teamSetter.playerID, Model.Team.GREEN);
			}else if(teamSetter.teamNumber == 2){
				Model.model.setPlayerTeam(teamSetter.playerID, Model.Team.BROWN);
			}
		}else if(obj instanceof Packet9SpellHit){
			Packet9SpellHit spellHit = (Packet9SpellHit)obj;
			Model.model.recieveSpellHitReport(spellHit.combinedId, spellHit.playerHitId);
		}else if(obj instanceof Packet10CustomSpellEffect){
			Packet10CustomSpellEffect customSpellEffect = (Packet10CustomSpellEffect)obj;
			Model.model.recieveCustomSpellAreaOfEffect(customSpellEffect.effectId, customSpellEffect.xPos, customSpellEffect.yPos, customSpellEffect.vectorX, customSpellEffect.vectorY, customSpellEffect.duration, customSpellEffect.playerUsedId, customSpellEffect.spellEffectId);
		}else if(obj instanceof Packet11MouseAttack){
			Packet11MouseAttack mouseAttack = (Packet11MouseAttack)obj;
			Model.model.executeMouseAttack(mouseAttack.id, mouseAttack.mouseButton, mouseAttack.mouseGameX, mouseAttack.mouseGameY);
		}else if(obj instanceof Packet12PlayerCustomizer){
			Packet12PlayerCustomizer playerCustomizer = (Packet12PlayerCustomizer)obj;
			Model.model.recievePlayerCustomizer(playerCustomizer.playerId, playerCustomizer.gender, playerCustomizer.clothes, playerCustomizer.hair,playerCustomizer.weapon);
		}
	}
	
}
