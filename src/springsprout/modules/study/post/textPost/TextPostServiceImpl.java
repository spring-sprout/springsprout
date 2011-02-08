package springsprout.modules.study.post.textPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.exception.ExceptionTemplate;
import springsprout.common.exception.ExceptionalWork;
import springsprout.common.web.support.Paging;
import springsprout.common.web.support.PostPaging;
import springsprout.domain.Comment;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.study.board.TextPost;
import springsprout.modules.comment.CommentRepository;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.post.PostService;
import springsprout.modules.study.post.textPost.support.TextPostMailMessage;
import springsprout.service.notification.NotificationService;
import springsprout.service.security.SecurityService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service("textPostService")
@Transactional
public class TextPostServiceImpl implements PostService<TextPost> {

	@Autowired TextPostRepository repository;
	@Autowired SecurityService securityService;
	@Autowired CommentRepository commentRepository;
    @Autowired MemberRepository memberRepository;
    @Resource StudyService studyService;

    @Autowired ExceptionTemplate exceptionTemplate;
    @Resource NotificationService unifiedNotificationService;

	public void addPost(final TextPost post) {
        post.setWriter( securityService.getCurrentMember());
		post.setCreatedAt( new Date());
		repository.add( post);


        final Study study = post.getRootStudy();
        final Collection<Member> members = studyService.getMembersOf(study);
        exceptionTemplate.catchAll(new ExceptionalWork() {
            public void run() throws Exception {
                unifiedNotificationService.sendMessage(new TextPostMailMessage(study, post, members));
            }
        });
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

    public PostPaging initPaing(int start, int studyId) {
        return new PostPaging( Paging.DEFAULT_SIZE, start, repository.getRootPostCount(studyId));
    }

    public List<TextPost> getList( int start, int limit, int studyId) {
		return repository.getRootPostList(start * limit, limit, studyId);
	}
}
