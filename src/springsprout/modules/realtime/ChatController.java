package springsprout.modules.realtime;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/chat")
	public String chatView(Model model){
		model.addAttribute("user", securityService.getCurrentMember());
		return "chat";
	}
}
