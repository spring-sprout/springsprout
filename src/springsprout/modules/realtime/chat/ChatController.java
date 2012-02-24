package springsprout.modules.realtime.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired ChatService chatService;
	@Autowired MemberService memberService;

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatView(Model model){
		model.addAttribute("user", securityService.getCurrentMember());
		model.addAttribute("chatSessions", chatService.getAll());
		return "chat";
	}
	
	@RequestMapping(value = "/chat/in", method = RequestMethod.GET)
	public @ResponseBody String newChatter(ChatSession chatSession) {
		chatService.add(chatSession);
		return chatSession + " inserted!";
	}

	@RequestMapping(value = "/chat/out", method = RequestMethod.GET)
	public @ResponseBody String deleteChatter(ChatSession chatSession) {
		chatService.remove(chatSession);
		return chatSession + " deleted!";
	}

	@RequestMapping(value = "/chat/clear", method = RequestMethod.GET)
	public @ResponseBody String clearChatSessions() {
		chatService.clear();
		return "seesion cleared!";
	}

	@RequestMapping(value = "/chat/sessions", method = RequestMethod.GET)
	public @ResponseBody List<ChatSession> chatSessions() {
		return chatService.getAll();
	}
	

}
