package springsprout.modules.realtime.chat;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Keesun Baik
 */
@Repository
public class DefaultChatSessionRepository implements ChatSessionRepository{

	Map<String, ChatSession> sessions = Collections.synchronizedMap(new HashMap<String, ChatSession>());

	@Override
	public List<ChatSession> getAll() {
		return new ArrayList<ChatSession>(sessions.values());
	}

	@Override
	public void remove(ChatSession chatSession) {
		sessions.remove(chatSession.getSock());
	}

	@Override
	public void add(ChatSession chatSession) {
		sessions.put(chatSession.getSock(), chatSession);
	}

	@Override
	public void clear() {
		sessions = new ConcurrentHashMap<String, ChatSession>();
	}
}
