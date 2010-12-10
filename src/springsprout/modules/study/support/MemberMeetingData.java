package springsprout.modules.study.support;

import springsprout.domain.Member;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 22
 * Time: 오전 12:42:10
 */
public class MemberMeetingData {

    Member member;

    int meetingCount;

    int percentage;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(int meetingCount) {
        this.meetingCount = meetingCount;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
