package springsprout.modules.study.board.textPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springsprout.common.web.support.Paging;
import springsprout.domain.Comment;
import springsprout.domain.Study;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.study.board.PostService;

@Controller
@RequestMapping("/study/view/{id}/board/textPost/")
@SessionAttributes({"study", "updatePost"})
public class TextPostController {

	@Autowired @Qualifier("textPostService") PostService<TextPost> service;
	
	@RequestMapping("list/{page}")
	public String getList(Model model, @ModelAttribute Study study, @PathVariable int page) {
		model.addAttribute(new TextPost(study));
		model.addAttribute(service.getList( page, Paging.DEFAULT_SIZE));
		model.addAttribute("pagingInfo", service.initPaing( page));
		return "study/board/textPost/list";
	}
	
	@RequestMapping(value="write", method = RequestMethod.POST)
	@ResponseBody
	public TextPost write(Model model, @ModelAttribute TextPost post) {
		service.addPost(post);
		return post;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public TextPost update(ModelMap model, @ModelAttribute TextPost updatePost, @PathVariable int id, SessionStatus status) {
		TextPost post = (TextPost) model.get("updatePost");
		post.setTitle(updatePost.getTitle());
		post.setContent(updatePost.getContent());
		service.updatePost(post);
		return post;
	}
	
	@RequestMapping("remove")
	public void remove(TextPost post) {
		service.removePost(post);
	}
	
	@RequestMapping("view/{id}/page/{page}")
	public String read(Model model, @PathVariable int id, @PathVariable int page, Study study) {
		TextPost post = service.getPost(id);
		model.addAttribute( "branchPost", new TextPost( post, study));
		model.addAttribute( "updatePost", new TextPost());
		model.addAttribute( "comment", new Comment());
		model.addAttribute( "page", page - 1);
		model.addAttribute( post);
		return "study/board/textPost/view";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	@ResponseBody
	public TextPost read(Model model, @PathVariable int id) {
		TextPost post = service.getPost(id);
		model.addAttribute( "updatePost", service.getPost(id));
		return post;
	}
	
	
	@RequestMapping(value="{id}/comment/write", method = RequestMethod.POST)
	@ResponseBody
	public Comment writeComment(@PathVariable int id, Comment comment) {
		TextPost post = service.getPost(id);
		service.addComment(post, comment);
		return comment;
	}
	
	@RequestMapping(value="{postId}/comment/{commentId}/remove", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeComment( @PathVariable int postId, @PathVariable int commentId) {
		service.removeComment( postId, commentId);
		return true;
	}
	
	
}
