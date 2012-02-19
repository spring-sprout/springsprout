package springsprout.modules.realtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springsprout.service.security.SecurityService;

/**
 * @author Keesun Baik
 */
@Controller
public class ChatController {

	@Autowired SecurityService securityService;
	@Value("${vertx.chat}") String chatUrl;

	@RequestMapping("/chat")
	public String chatView(Model model){
		model.addAttribute("user", securityService.getCurrentMember());
		System.out.println("=================");
		System.out.println(chatUrl);
		model.addAttribute("chatUrl", chatUrl);
		return "chat";
	}
}
