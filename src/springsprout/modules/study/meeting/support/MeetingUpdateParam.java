package springsprout.modules.study.meeting.support;

import springsprout.domain.Meeting;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 25
 * Time: 오후 4:32:31
 * To change this template use File | Settings | File Templates.
 */
public class MeetingUpdateParam {

    int studyId;
    int meetingId;
    Meeting newMeeting;
    boolean isGoingToBeNotified;

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public Meeting getNewMeeting() {
        return newMeeting;
    }

    public void setNewMeeting(Meeting newMeeting) {
        this.newMeeting = newMeeting;
    }

    public boolean isGoingToBeNotified() {
        return isGoingToBeNotified;
    }

    public void setGoingToBeNotified(boolean goingToBeNotified) {
        isGoingToBeNotified = goingToBeNotified;
    }
}
