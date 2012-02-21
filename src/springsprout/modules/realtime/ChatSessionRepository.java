package springsprout.modules.realtime;

import java.util.Collection;
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
