package springsprout.modules.mypage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import springsprout.domain.Member;
import springsprout.service.security.SecurityService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	Logger log = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	SecurityService securityService;
	
	@RequestMapping("/index")
    public void index(Model model) throws ServletRequestBindingException{
		Member member = securityService.getPersistentMember();
		member.makeAvatar();
		model.addAttribute(member);
    }
	
}
