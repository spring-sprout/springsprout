package springsprout.modules.study.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import springsprout.domain.Study;
import springsprout.domain.study.board.ImagePost;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.study.post.imagePost.ImagePostService;

@Controller
@RequestMapping("/study/{id}")
@SessionAttributes("study")
public class PostController {
	
	@Autowired @Qualifier("textPostService") PostService<TextPost> textService;
	@Autowired ImagePostService imageService;
	
	@RequestMapping("/post")
	public String view(Model model, @ModelAttribute Study study) {
		model.addAttribute(new TextPost(study));
		List<TextPost> texts = textService.getList(0, 10, study.getId());
		List<ImagePost> images = imageService.getList(0, 7, study.getId());
		model.addAttribute("texts", texts.isEmpty() ? null : texts);
		model.addAttribute("images", images.isEmpty() ? null : images);
		return "study/post/view";
	}
	
	@RequestMapping("/allPost")
	public String viewRecentPost(Model model, @ModelAttribute Study study) {
		model.addAttribute(new TextPost(study));
		return "study/post/viewRecentPosts";
	}
	
}
