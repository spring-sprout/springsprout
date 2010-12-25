package springsprout.modules.study.post;

import java.util.List;

import springsprout.common.web.support.PostPaging;
import springsprout.domain.Comment;
import springsprout.domain.study.board.Post;


public interface PostService<T extends Post> {

	public abstract List<T> getList();
	public abstract List<T> getList(int start, int limit, int studyId);
	public abstract T getPost(int postId);
	public abstract void addPost(T post);
	public abstract void removePost(T post);
	public abstract void updatePost(T post);
	public abstract void replyPost(T post);
	public abstract void addComment(T post,Comment comment);
	public abstract void removeComment(int postId, int commentId);
	public abstract PostPaging initPaing(int i);

    public abstract PostPaging initPaing(int page, int studyId);
}