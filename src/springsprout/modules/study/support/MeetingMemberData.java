package springsprout.modules.study.support;

import springsprout.domain.Meeting;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 22
 * Time: 오전 8:30:00
 */
public class MeetingMemberData {

    private Meeting meeting;

    private int attendedCount;

    private int realCount;

    private int percentage;

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public int getAttendedCount() {
        return attendedCount;
    }

    public void setAttendedCount(int attendedCount) {
        this.attendedCount = attendedCount;
    }

    public int getRealCount() {
        return realCount;
    }

    public void setRealCount(int realCount) {
        this.realCount = realCount;
    }

    public int getPercentage() {
        Double total = new Double(this.attendedCount);
        return (int)(this.realCount/total*100);
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
