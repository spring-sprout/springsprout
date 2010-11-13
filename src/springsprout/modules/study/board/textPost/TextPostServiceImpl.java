package springsprout.modules.study.board.textPost;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.web.support.Paging;
import springsprout.common.web.support.PostPaging;
import springsprout.domain.Comment;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.comment.CommentRepository;
import springsprout.modules.study.board.PostService;
import springsprout.service.security.SecurityService;

@Service("textPostService")
@Transactional
public class TextPostServiceImpl implements PostService<TextPost> {

	@Autowired TextPostRepository repository;
	@Autowired SecurityService securityService;
	@Autowired CommentRepository commentRepository;
	
	public void addPost(TextPost post) {
		post.setWriter( securityService.getCurrentMember());
		post.setCreatedAt( new Date());
		repository.add( post);
	}

	public void removePost(TextPost post) {
	}

	public void updatePost(TextPost post) {
		post.setModifidedAt( new Date());
		repository.update(post);
	}

	public void replyPost(TextPost post) {
	}

	public void addComment(TextPost post, Comment comment) {
		comment.setWriter(securityService.getCurrentMember());
		comment.applyAtHtml();
		post.addComment(comment);
	}

	public void removeComment(int postId, int commentId) {
		TextPost post = repository.getById(postId);
		post.removeCommentById(commentId);
	}

	public void updateComment(Comment comment) {
	}

	public List<TextPost> getList() {
		return repository.getRootPostList();
	}

	public TextPost getPost(int id) {
		return repository.getById( id);
	}

	public PostPaging initPaing(int start) {
		return new PostPaging( Paging.DEFAULT_SIZE, start, repository.getRootPostCount());
	}

	public List<TextPost> getList( int start, int limit) {
		return repository.getRootPostList(start * limit, limit);
	}
}
