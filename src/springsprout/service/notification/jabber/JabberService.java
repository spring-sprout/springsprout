package springsprout.service.notification.jabber;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.exception.NotificationException;
import springsprout.service.notification.message.SpringSproutMessage;

import javax.annotation.PostConstruct;

public class JabberService implements NotificationService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ConnectionConfiguration configuration;
	@Autowired(required=false) PacketListenerRepository listenerRepository;
	@Autowired XMPPConnection connection;

	String username;
	String password;
	boolean runOnStart;

	@PostConstruct
	public void startService(){
		if(runOnStart){
			try {
				login();
			} catch (XMPPException e) {
				throw new NotificationException(configuration.getServiceName() + " 로그인 중 에러 발생.", e);
			}
			addListener();
		}
	}
	
	public JabberService(String username, String password, boolean runOnStart) {
		this.username = username;
		this.password = password;
		this.runOnStart = runOnStart;
	}

	public void sendMessage(SpringSproutMessage ssm) {
		try {
			login();
			makeAvailable();
			for(String address : ssm.getMessageReceivers()){
				Message msg = new Message(address, Message.Type.chat);
				String message = ssm.getMessage();
				msg.setBody(message);
				connection.sendPacket(msg);
				logger.debug("구글 토크 메시지 전송 완료 address: [{}], message: [{}]", address, message);
			}
			makeAvailable();
		} catch (XMPPException e) {
			logger.debug("구글 토크 메시지 전송 에러 error: [{}]", e);
			throw new NotificationException("구글 토크 메시지 전송 중 에러 발생.", e);
		}
	}

	private void makeAvailable() {
		Presence presence = new Presence(Presence.Type.available);
		connection.sendPacket(presence);
		logger.debug("구글 토크 활성화 presence: [{}]", presence);
	}

	private void login() throws XMPPException {
		if(!connection.isConnected())
			connection.connect();
		if(!connection.isAuthenticated())
			connection.login(username, password);
		logger.debug("구글 토크 로그인  usename: [{}], password: [{}]", username, password);
	}
	
	private void addListener(){
		for(PacketListener listener : listenerRepository.getAll())
			connection.addPacketListener(listener, null);
	}


}
