package networking;

import org.newdawn.slick.geom.Vector2f;

import entities.Player.Clothes;
import entities.Player.Gender;
import entities.Player.Hair;
import entities.Player.Weapon;
import game.Model.Team;

public class Packet {

	
	public static class Packet0LoginRequest{String name;}
	public static class Packet1LoginAnswer{
		boolean accepted = false;
		int ID;
	}
	public static class Packet2Message{ String message;}
	public static class Packet3PlayerSender{ 
		float xPos;
		float yPos;
		int ID;
		float vectorX;
		float vectorY;
		boolean isCasting;
	}
	public static class Packet4AddPlayer{
		float xPos;
		float yPos;
		int ID;
		String name;
	}
	public static class Packet5StartThread{}
	public static class Packet6UseAbility{
		int playerID;
		int abilityNumber;
		float mouseGameX;
		float mouseGameY;
		int[] spellEffectId;
	}
	
	public static class Packet7AddAbility{
		int playerID;
		int abilityID;
		int abilityNumber;
	}
	
	public static class Packet8SetTeam{
		int playerID;
		int teamNumber;
	}
	
	public static class Packet9SpellHit{
		int combinedId;
		int playerHitId;
	}
	
	public static class Packet10CustomSpellEffect{
		int effectId;
		float xPos;
		float yPos;
		float vectorX;
		float vectorY;
		int duration;
		int playerUsedId;
		int spellEffectId;
	}
	
	public static class Packet11MouseAttack{
		int id; 
		int mouseButton; 
		float mouseGameX;
		float mouseGameY;
	}
	
	public static class Packet12PlayerCustomizer{
		int playerId;
		int gender;
		int clothes;
		int hair;
		int weapon;
	}
	
	//hej
	
}
