package springsprout.modules.realtime.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsprout.modules.member.MemberService;
import springsprout.service.security.DefaultSecurityService;

import java.util.List;

/**
 * @author Keesun Baik
 */
@Service
public class DefaultChatService implements ChatService {

	@Autowired MemberService memberService;
	@Autowired ChatSessionRepository chatSessionRepository;

	@Override
	public List<ChatSession> getAll() {
		return chatSessionRepository.getAll();
	}

	@Override
	public void add(ChatSession chatSession) {
		if(chatSession.getEmail().equals("anony@mous.mail")) {
			chatSession.setMember(new DefaultSecurityService.NullMember());
		} else {
			chatSession.setMember(memberService.getMemberByEmail(chatSession.getEmail()));
		}
		chatSessionRepository.add(chatSession);

	}

	@Override
	public void remove(ChatSession chatSession) {
		chatSessionRepository.remove(chatSession);
	}

	@Override
	public void clear() {
		chatSessionRepository.clear();
	}
}
