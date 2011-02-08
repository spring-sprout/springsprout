package springsprout.modules.study.post.surbeyPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import springsprout.domain.Study;
import springsprout.domain.study.board.SurbeyPost;

@Controller
@RequestMapping("/study/{id}/post/surbey")
@SessionAttributes({"study"})
public class SurbeyPostController {

	@Autowired SurbeyPostService service;
	
	
	@RequestMapping("/list/{page}")
	public String getList(Model model, @ModelAttribute Study study, @PathVariable int page, SessionStatus status) {
		model.addAttribute(new SurbeyPost(study));
		return "study/post/surbey/list";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model, @ModelAttribute Study study, @PathVariable int id) {
		model.addAttribute("surbeyPost", new SurbeyPost(study));
		model.addAttribute("title", "설문글 추가");
		model.addAttribute("action", "/study/" + id + "/post/surbey");
		model.addAttribute("cancelUrl", "/study/" + id + "/post/surbey/list/0");
		return "study/post/surbey/form";
	}
}
