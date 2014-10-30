package networking;

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
	}
	public static class Packet4AddPlayer{
		float xPos;
		float yPos;
		int ID;
		String name;
	}
	public static class Packet5StartThread{}
	public static class Packet6AddAbility{
		int playerID;
		int abilityID;
		float mouseGameX;
		float mouseGameY;
	}
	
	public static void registerPackets(){
		
	}
	
}
