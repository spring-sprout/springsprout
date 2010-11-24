package springsprout.modules.study.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import springsprout.domain.Study;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.study.post.imagePost.ImagePostService;

@Controller
@RequestMapping("/study/{id}")
@SessionAttributes("study")
public class PostController {
	
	@Autowired ImagePostService imageService;
	
	@RequestMapping("/post")
	public String view(Model model, @ModelAttribute Study study) {
		model.addAttribute(new TextPost(study));
		model.addAttribute("images", imageService.getList(0, 7));
		return "study/post/view";
	}
	
	@RequestMapping("/allPost")
	public String viewRecentPost(Model model, @ModelAttribute Study study) {
		model.addAttribute(new TextPost(study));
		return "study/post/viewRecentPosts";
	}
	
}
