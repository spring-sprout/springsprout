package springsprout.modules.study.post.textPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import springsprout.common.web.support.Paging;
import springsprout.domain.Comment;
import springsprout.domain.Study;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.study.post.PostService;

@Controller
@RequestMapping("/study/{id}/post/textPost")
@SessionAttributes({"study", "textPost"})
public class TextPostController {

	@Autowired @Qualifier("textPostService") PostService<TextPost> service;
	
	@RequestMapping("/list/{page}")
	public String getList(Model model, @ModelAttribute Study study, @PathVariable int id, @PathVariable int page) {
		model.addAttribute(new TextPost(study));
		model.addAttribute(service.getList( page, Paging.DEFAULT_SIZE, id));
		model.addAttribute("pagingInfo", service.initPaing( page));
		return "study/post/textPost/list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String write(Model model, @ModelAttribute TextPost post) {
		service.addPost(post);
		return "study/post/textPost/list";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model, @ModelAttribute Study study, @PathVariable int id) {
		model.addAttribute(new TextPost(study));
		model.addAttribute("title", "Add New");
		model.addAttribute("method", RequestMethod.POST.toString());
		model.addAttribute("action", "/study/" + id + "/post/textPost");
		model.addAttribute("cancelUrl", "/study/" + id + "/post/textPost/list/0");
		return "study/post/textPost/form";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String update(ModelMap model, @ModelAttribute TextPost textPost, @ModelAttribute Study study, 
			@PathVariable int id, @RequestParam int page, SessionStatus status) {
		service.updatePost(textPost);
		model.addAttribute( "branchPost", new TextPost( textPost, study));
		model.addAttribute( "comment", new Comment());
		model.addAttribute( "page", page);
		model.addAttribute( textPost);
		return "study/post/textPost/view";
	}
	
	@RequestMapping(value="/{postId}/update", method = RequestMethod.GET)
	public String getUpdateForm(Model model, @PathVariable int id, @PathVariable int postId, @RequestParam int page) {
		model.addAttribute("textPost", service.getPost(postId));
		model.addAttribute("title", "Update");
		model.addAttribute("method", RequestMethod.PUT.toString());
		String actionUrl = "/study/" + id + "/post/textPost/" + postId + "?page=" + page;
		model.addAttribute("action", actionUrl);
		model.addAttribute("cancelUrl", actionUrl);
		return "study/post/textPost/form";
	}
	
	@RequestMapping(value="/{postId}/reply", method = RequestMethod.GET)
	public String getReplyForm(Model model, @ModelAttribute Study study,
			@PathVariable int id, @PathVariable int postId, @RequestParam int page) {
		TextPost parent = service.getPost(postId);
		model.addAttribute("parent", service.getPost(postId));
		model.addAttribute("textPost", new TextPost( parent, study));
		model.addAttribute("title", "Reply to");
		model.addAttribute("method", RequestMethod.POST.toString());
		model.addAttribute("action", "/study/" + id + "/post/textPost/" + postId + "/reply?page=" + page);
		model.addAttribute("cancelUrl", "/study/" + id + "/post/textPost/" + postId + "?page=" + page);
		return "study/post/textPost/replyForm";
	}
	
	@RequestMapping(value="/{postId}/replyUpdate/{replyId}", method = RequestMethod.GET)
	public String getReplyUpdateForm(Model model, @ModelAttribute Study study,
			@PathVariable int id, @PathVariable int postId, @PathVariable int replyId, @RequestParam int page) {
		model.addAttribute("parent", service.getPost(postId));
		model.addAttribute("textPost", service.getPost(replyId));
		model.addAttribute("title", "Update Reply to");
		model.addAttribute("method", RequestMethod.PUT.toString());
		model.addAttribute("action", "/study/" + id + "/post/textPost/" + postId + "/replyUpdate?page=" + page);
		model.addAttribute("cancelUrl", "/study/" + id + "/post/textPost/" + postId + "?page=" + page);
		return "study/post/textPost/replyForm";
	}
	
	@RequestMapping(value="/{postId}/replyUpdate", method=RequestMethod.PUT)
	public String updateReply(ModelMap model, @ModelAttribute TextPost textPost, @ModelAttribute Study study, 
			@PathVariable int postId, @RequestParam int page, SessionStatus status) {
		service.updatePost(textPost);
		model.addAttribute( "branchPost", new TextPost( textPost, study));
		model.addAttribute( "comment", new Comment());
		model.addAttribute( "page", page);
		model.addAttribute( service.getPost(postId));
		return "study/post/textPost/view";
	}
	
	@RequestMapping("/{postId}")
	public String read(Model model, @PathVariable int postId, @RequestParam int page, Study study) {
		TextPost post = service.getPost(postId);
		model.addAttribute( "branchPost", new TextPost( post, study));
		model.addAttribute( "comment", new Comment());
		model.addAttribute( "page", page - 1);
		model.addAttribute( post);
		return "study/post/textPost/view";
	}
	
	@RequestMapping(value="/{postId}/comment", method = RequestMethod.POST)
	public @ResponseBody Comment write(@PathVariable int postId, Comment comment) {
		TextPost post = service.getPost(postId);
		service.addComment(post, comment);
		return comment;
	}
	
	@RequestMapping(value="/{postId}/comment/{commentId}", method = RequestMethod.DELETE)
	public @ResponseBody boolean removeComment( @PathVariable int postId, @PathVariable int commentId) {
		service.removeComment( postId, commentId);
		return true;
	}
}
