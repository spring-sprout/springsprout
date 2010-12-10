package springsprout.modules.study.support;

import springsprout.common.enumeration.DayOfWeek;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:36:52
 */
public class MeetingDayOfWeekData {

    private DayOfWeek dayOfWeek;

    private int meetingCount;

    private int percentage;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
