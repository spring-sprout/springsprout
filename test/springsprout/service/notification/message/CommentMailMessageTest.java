package springsprout.service.notification.message;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import springsprout.domain.Comment;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Presentation;

/**
 * 모임 및 발표에 Comment가 등록되었을 경우 알림 메시지를 발송 한다.
 * @author JUNE
 *
 */
public class CommentMailMessageTest extends SpringSproutMailMessage {
	@Autowired VelocityEngine velocityEngine;
	private Comment comment;
	private Meeting meeting;
	private Presentation presentation;
	
	public CommentMailMessageTest() {
		
	}
	
	public CommentMailMessageTest( Comment comment, Meeting meeting, Presentation presentation, Collection<Member> members) {
		super(members);
		this.comment = comment;
		this.meeting = meeting;
		this.presentation = presentation;
	}
	
	@Override
	public String getFrom() {
		return comment.getWriter().getEmail();
	}

	@Override
	public String getMessage() {
		return "message";
	}

	@Override
	public String getTitle() {
		if ( presentation == null) return SpringSproutMail.SUBJECT_PREFIX + " [" + meeting.getTitle() + "] 모임에 의견이 추가 되었습니다.";
		return SpringSproutMail.SUBJECT_PREFIX + " [" + meeting.getTitle() + "] 모임의 [" + presentation.getTitle() + "] 발표에 의견이 추가 되었습니다.";
	}

	@Override
	public String getContents() {
		return makeContents();
	}
	
	private String makeContents() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("user", "June.");
		model.put("author", "김제준");
		model.put("contents", "블바블라 스터디");
		model.put("message", "아아. 이메일 템플릿 이정도 비스무레 하게 하면 맘에 드십니까?");
		model.put("link", "http://www.springsprout.org/study/5");
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/web/WEB-INF/velocity/commentNotifyMail.html", "UTF-8", model);
	}

}
