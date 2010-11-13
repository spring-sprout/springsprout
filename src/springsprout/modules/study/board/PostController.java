package springsprout.modules.study.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import springsprout.domain.Study;
import springsprout.domain.study.board.TextPost;

@Controller
@RequestMapping("/study/view/{id}/board/")
@SessionAttributes("study")
public class PostController {
	
	@RequestMapping("view")
	public String view(Model model, @ModelAttribute Study study) {
		model.addAttribute(new TextPost(study));
		return "study/board/view";
	}
	
	@RequestMapping("allPost")
	public String viewRecentPost(Model model, @ModelAttribute Study study) {
		model.addAttribute(new TextPost(study));
		return "study/board/viewRecentPosts";
	}
	
}
