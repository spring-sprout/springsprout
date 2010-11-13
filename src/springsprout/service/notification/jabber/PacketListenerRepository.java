package springsprout.service.notification.jabber;

import org.jivesoftware.smack.PacketListener;

import java.util.List;

public class PacketListenerRepository {

	List<PacketListener> packetListeners;
	
	public PacketListenerRepository(List<PacketListener> packetListeners) {
		this.packetListeners = packetListeners;
	}
	
	public List<PacketListener> getAll() {
		return packetListeners;
	}

}
