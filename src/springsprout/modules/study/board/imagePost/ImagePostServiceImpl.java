package springsprout.modules.study.board.imagePost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.web.support.PostPaging;
import springsprout.domain.Comment;
import springsprout.domain.study.board.ImageFile;
import springsprout.domain.study.board.ImagePost;
import springsprout.modules.file.FileService;
import springsprout.modules.study.board.PostService;
import springsprout.service.security.SecurityService;

@Service("imagePostService")
@Transactional
public class ImagePostServiceImpl implements PostService<ImagePost> {
	
	@Autowired ImagePostRepository repository;
	@Autowired @Qualifier("imageFileService") FileService fileService;
	@Autowired SecurityService securityService;

	public List<ImagePost> getList() {
		throw new UnsupportedOperationException("미지원 기능 이지롱");
	}

	public List<ImagePost> getList( int start, int limit) {
		return repository.getRootPostList(start * limit, limit);
	}

	public ImagePost getPost( int postId) {
		return repository.getById(postId);
	}

	public void addPost( ImagePost post) {
		post.setWriter(securityService.getCurrentMember());
		ImageFile imageFile = getImageFileWithUploadFile(post);
		post.setImageFile(imageFile);
		repository.add(post);
	}

	private ImageFile getImageFileWithUploadFile( ImagePost post) {
		return (ImageFile) fileService.upload(post.getFile());
	}

	public void removePost( ImagePost post) {
		fileService.delete(post.getImageFile().getId());
		repository.deleteById(post.getId());
	}

	public void updatePost( ImagePost post) {
		// TODO Auto-generated method stub
		
	}

	public void replyPost( ImagePost post) {
		throw new UnsupportedOperationException("미지원 기능 이지롱");
	}

	public void addComment(ImagePost post, Comment comment) {
		comment.setWriter(securityService.getCurrentMember());
		comment.applyAtHtml();
		post.addComment(comment);
	}
	
	public void removeComment(int postId, int commentId) {
		ImagePost post = repository.getById(postId);
		post.removeCommentById(commentId);
	}

	public PostPaging initPaing( int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
