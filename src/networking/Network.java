package networking;

import org.newdawn.slick.geom.Vector2f;

import game.Model.Team;
import networking.Packet.Packet0LoginRequest;
import networking.Packet.Packet1LoginAnswer;
import networking.Packet.Packet2Message;
import abilities.Ability;

import com.esotericsoftware.kryo.Kryo;

public abstract class Network {

	public String name;
	
	
	public Network(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract void startNetworkThread();
	
	public abstract void sendUDPToAll(Object obj);
	
	public abstract void sendTCPToAll(Object obj);

	public abstract void sendAbility(int id, int abilityNumber, float mouseGameX, float mouseGameY, int spellEffectId[]) ;

	public abstract void sendAddAbility(int playerID, int abilityID, int abilityNumber) ;

	public abstract void sendSetTeam(int id, Team team);

	public abstract void sendSpellHitReport(int spellCombinedId, int playerHitId);

	public abstract void sendCustomSpellAreaOfEffect(int effectId, float xPos,
			float yPos, float vectorX, float vectorY, int duration, int playerUsedId,
			int spellEffectId);

	public abstract void sendMouseAttack(int id, int mouseButton, float mouseGameX,
			float mouseGameY);
	
}
