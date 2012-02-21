package springsprout.modules.realtime.chat;

import java.util.List;

/**
 * @author Keesun Baik
 */
public interface ChatService {

	List<ChatSession> getAll();

	void add(ChatSession chatSession);

	void remove(ChatSession chatSession);

	void clear();
}
