package springsprout.service.notification.message;

import springsprout.common.convention.Convention;
import springsprout.domain.Member;
import springsprout.domain.Notice;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 28
 * Time: 오전 10:56:40
 */
public class NoticeMailMessage extends SpringSproutMailMessage {

    private Notice notice;

    public NoticeMailMessage(Notice notice, Collection<Member> members) {
        super(members);
        this.notice = notice;
    }

    @Override
    public String getTitle() {
        return SpringSproutMail.SUBJECT_PREFIX + " [" + notice.getTitle() + "] ";
    }

    @Override
    public String getContents() {
        return notice.getContents();
    }

    @Override
    public String getMessage() {
        StringBuilder msg = new StringBuilder();
		msg.append(notice.getTitle());
		msg.append("\n");
		msg.append(Convention.HOME_URL);
		return msg.toString();

    }
}
