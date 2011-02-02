package springsprout.service.notification.message;

import org.apache.commons.lang.time.DateFormatUtils;
import springsprout.domain.Comment;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Presentation;

import java.util.Collection;

/**
 * 모임 및 발표에 Comment가 등록되었을 경우 알림 메시지를 발송 한다.
 * @author JUNE
 *
 */
public class CommentMailMessage extends SpringSproutMailMessage {
	
	private Comment comment;
	private Meeting meeting;
	private Presentation presentation;
	
	
	public CommentMailMessage( Comment comment, Meeting meeting, Presentation presentation, Collection<Member> members) {
		super(members);
		this.comment = comment;
		this.meeting = meeting;
		this.presentation = presentation;
	}

	@Override
	public String getMessage() {
		// TODO 이건 머하는 거지
		return "message";
	}

	@Override
	public String getTitle() {
		if ( presentation == null) return SpringSproutMail.SUBJECT_PREFIX + " [" + meeting.getTitle() + "] 모임에 의견이 추가 되었습니다.";
		return SpringSproutMail.SUBJECT_PREFIX + " [" + meeting.getTitle() + "] 모임의 [" + presentation.getTitle() + "] 발표에 의견이 추가 되었습니다.";
	}

	@Override
	public String getContents() {
		return comment.getWriter().getName() + "님이 '<strong>" + comment.getComment() + "</strong>'란 의견을 등록 하였습니다.<br />" + 
			"등록일 : " + DateFormatUtils.formatUTC(comment.getCreated(), "yyyy-MM-dd, HH:mm:ss");
	}

}
