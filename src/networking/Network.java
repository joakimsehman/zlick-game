package networking;

import networking.Packet.Packet0LoginRequest;
import networking.Packet.Packet1LoginAnswer;
import networking.Packet.Packet2Message;

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
	
	
	
	
}
