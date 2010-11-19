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

	public List<ImagePost> getList( int start, int limit) {
		return repository.getRootPostList(start * limit, limit);
	}

	public ImagePost getPost( int postId) {
		return repository.getById(postId);
	}

	public void addPost( ImagePost imagePost) {
		imagePost.setWriter(securityService.getCurrentMember());
		ImageFile imageFile = getImageFileWithUploadFile(imagePost);
		imagePost.setImageFile(imageFile);
		repository.add(imagePost);
	}

	private ImageFile getImageFileWithUploadFile( ImagePost imagePost) {
		return (ImageFile) fileService.upload(imagePost.getFile());
	}

	public void removePost( ImagePost imagePost) {
		ImagePost post = repository.getById(imagePost.getId());
		deleteImageFromStorage(post.getImageFile().getId());
		repository.deleteById(post.getId());
	}

	private void deleteImageFromStorage( int imageFileId) {
		fileService.delete(imageFileId);
	}

	/**
	 * @TODO 기존 파일 삭제한 후 새 파일 업로드
	 */
	public void updatePost( ImagePost imagePost) {
		ImagePost updatePost = repository.getById(imagePost.getId());
		deleteImageFromStorage( updatePost.getImageFile().getId());
		updatePost.setTitle(imagePost.getTitle());
		updatePost.setContent(imagePost.getContent());
		updatePost.setWriter(securityService.getCurrentMember());
		updatePost.setImageFile(getImageFileWithUploadFile(imagePost));
		repository.update(updatePost);
	}

	public void addComment(ImagePost imagePost, Comment comment) {
		comment.setWriter(securityService.getCurrentMember());
		comment.applyAtHtml();
		imagePost.addComment(comment);
	}
	
	public void removeComment(int postId, int commentId) {
		ImagePost imagePost = repository.getById(postId);
		imagePost.removeCommentById(commentId);
	}

	public List<ImagePost> getList() {
		throw new UnsupportedOperationException("미지원 기능 이에요.");
	}
	
	public void replyPost( ImagePost post) {
		throw new UnsupportedOperationException("미지원 기능 이에요.");
	}
	
	public PostPaging initPaing( int i) {
		throw new UnsupportedOperationException("이미지 게시판에서는 페이징을 쓰지 않아요.");
	}
}
