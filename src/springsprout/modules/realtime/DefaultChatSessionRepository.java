package springsprout.modules.realtime;

import org.springframework.stereotype.Repository;
import springsprout.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Keesun Baik
 */
@Repository
public class DefaultChatSessionRepository implements ChatSessionRepository{

	ConcurrentMap<String, ChatSession> sessions = new ConcurrentHashMap<String, ChatSession>();

	@Override
	public List<ChatSession> getAll() {
		return new ArrayList<ChatSession>(sessions.values());
	}

	@Override
	public void remove(ChatSession chatSession) {
		System.out.println("======================");
		System.out.println(chatSession.getSock() + " delete");
		sessions.remove(chatSession.getSock());
		System.out.println(sessions.size());
	}

	@Override
	public void add(ChatSession chatSession) {
		System.out.println("=======================");
		System.out.println(chatSession.getSock() + " inserted");
		sessions.put(chatSession.getSock(), chatSession);
		System.out.println(sessions.size());
	}

	@Override
	public void clear() {
		sessions = new ConcurrentHashMap<String, ChatSession>();
	}
}
