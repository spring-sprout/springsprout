package springsprout.modules.study.board.imagePost;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import springsprout.common.web.support.Paging;
import springsprout.domain.Comment;
import springsprout.domain.Resource;
import springsprout.domain.Study;
import springsprout.domain.UploadFile;
import springsprout.domain.study.board.ImagePost;
import springsprout.domain.study.board.Post;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.file.FileService;
import springsprout.modules.study.board.PostService;
import springsprout.service.security.SecurityService;

@Controller
@RequestMapping("/study/view/{id}/board/imagePost/")
@SessionAttributes({"study", "updatePost"})
public class ImagePostController {

	@Autowired @Qualifier("imagePostService") PostService<ImagePost> service;
	@Autowired SecurityService securityService;
	
	@RequestMapping("list/{page}")
	public String getList(Model model, @ModelAttribute Study study, @PathVariable int page, SessionStatus status) {
		List<ImagePost> posts = service.getList( page, Paging.DEFAULT_SIZE);
		model.addAttribute(new ImagePost(study));
		model.addAttribute("posts", posts);
		model.addAttribute("page", page);
		model.addAttribute("comment", new Comment());
		return "study/board/imagePost/list";
	}
	
	@RequestMapping(value="write", method = RequestMethod.POST)
	public void write(Model model, @ModelAttribute ImagePost post, HttpServletRequest request) {
		service.addPost(post);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public ImagePost update(ModelMap model, @ModelAttribute ImagePost updatePost, @PathVariable int id, SessionStatus status) {
		ImagePost post = (ImagePost) model.get("updatePost");
		post.setTitle(updatePost.getTitle());
		post.setContent(updatePost.getContent());
		service.updatePost(post);
		return post;
	}
	
	@RequestMapping("remove/{id}")
	public String remove(@PathVariable int id, ImagePost post) {
		post.setId(id);
		service.removePost(post);
		return "study/board/imagePost/list";
	}
	
	@RequestMapping("view/{id}/page/{page}")
	public String read(Model model, @PathVariable int id, @PathVariable int page, Study study) {
		ImagePost post = service.getPost(id);
		model.addAttribute( "updatePost", new ImagePost());
		model.addAttribute( "comment", new Comment());
		model.addAttribute( "page", page - 1);
		model.addAttribute( post);
		return "study/board/imagePost/view";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	@ResponseBody
	public ImagePost read(Model model, @PathVariable int id) {
		ImagePost post = service.getPost(id);
		model.addAttribute( "updatePost", service.getPost(id));
		return post;
	}
	
	
	@RequestMapping(value="{id}/comment/write", method = RequestMethod.POST)
	public String writeComment(Model model, @PathVariable int id, Comment comment) {
		ImagePost post = service.getPost(id);
		service.addComment(post, comment);
		model.addAttribute("postId", id);
		return "study/board/_commentSpot";
	}
	
	@RequestMapping(value="{postId}/comment/{commentId}/remove", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeComment( @PathVariable int postId, @PathVariable int commentId) {
		service.removeComment( postId, commentId);
		return true;
	}
}
