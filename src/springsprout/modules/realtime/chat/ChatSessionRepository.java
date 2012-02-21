package springsprout.modules.realtime.chat;

import java.util.List;

/**
 * @author Keesun Baik
 */
public interface ChatSessionRepository {
	List<ChatSession> getAll();

	void remove(ChatSession chatSession);

	void add(ChatSession chatSession);

	void clear();

}
