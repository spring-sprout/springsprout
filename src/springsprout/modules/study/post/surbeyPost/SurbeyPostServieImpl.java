package springsprout.modules.study.post.surbeyPost;

import java.util.List;

import org.springframework.stereotype.Service;

import springsprout.common.web.support.PostPaging;
import springsprout.domain.Comment;
import springsprout.domain.study.board.SurbeyPost;

@Service
public class SurbeyPostServieImpl implements SurbeyPostService {

	public List<SurbeyPost> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SurbeyPost> getList(int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public SurbeyPost getPost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPost(SurbeyPost post) {
		// TODO Auto-generated method stub
		
	}

	public void removePost(SurbeyPost post) {
		// TODO Auto-generated method stub
		
	}

	public void updatePost(SurbeyPost post) {
		// TODO Auto-generated method stub
		
	}

	public void replyPost(SurbeyPost post) {
		// TODO Auto-generated method stub
		
	}

	public void addComment(SurbeyPost post, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public void removeComment(int postId, int commentId) {
		// TODO Auto-generated method stub
		
	}

	public PostPaging initPaing(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
