package springsprout.modules.realtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springsprout.modules.member.MemberService;
import springsprout.service.security.DefaultSecurityService;
import springsprout.service.security.SecurityService;

import java.util.List;

/**
 * @author Keesun Baik
 */
@Controller
public class ChatController {

	@Autowired SecurityService securityService;
	@Value("${vertx.chat}") String chatUrl;
	@Autowired ChatSessionRepository chatSessionRepository;
	@Autowired MemberService memberService;

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatView(Model model){
		model.addAttribute("user", securityService.getCurrentMember());
		model.addAttribute("chatUrl", chatUrl);
		model.addAttribute("chatSessions", chatSessionRepository.getAll());
		return "chat";
	}
	
	@RequestMapping(value = "/chat/in", method = RequestMethod.POST)
	public @ResponseBody String newChatter(ChatSession chatSession) {
		if(chatSession.getEmail().equals("anony@mous.mail")) {
			chatSession.setMember(new DefaultSecurityService.NullMember());
		} else {
			chatSession.setMember(memberService.getMemberByEmail(chatSession.getEmail()));
		}
		chatSessionRepository.add(chatSession);
		return chatSession + " inserted!";
	}

	@RequestMapping(value = "/chat/out", method = RequestMethod.POST)
	public @ResponseBody String deleteChatter(ChatSession chatSession) {
		chatSessionRepository.remove(chatSession);
		return chatSession + " deleted!";
	}

	@RequestMapping(value = "/chat/clear", method = RequestMethod.GET)
	public @ResponseBody String clearChatSessions() {
		chatSessionRepository.clear();
		return "seesion cleared!";
	}

	@RequestMapping(value = "/chat/sessions", method = RequestMethod.GET)
	public @ResponseBody List<ChatSession> chatSessions() {
		return chatSessionRepository.getAll();
	}
	

}
