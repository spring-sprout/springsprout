package springsprout.modules.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springsprout.modules.main.support.SudaMessage;
import springsprout.modules.member.MemberService;

/**
 * @author Keesun Baik
 */
@Controller
public class GraffitiController {

	@Autowired GraffitiService service;
	
	@RequestMapping("/suda/add")
	public @ResponseBody String addSuda(SudaMessage sudaMessage) {
		service.add(sudaMessage);
		return "ok";
	}
}
