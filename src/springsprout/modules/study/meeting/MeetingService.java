package springsprout.modules.study.meeting;

import springsprout.domain.Attendance;
import springsprout.domain.Comment;
import springsprout.domain.Meeting;
import springsprout.domain.Presentation;
import springsprout.domain.Resource;
import springsprout.domain.UploadFile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 16
 * Time: 오후 1:27:43
 */
public interface MeetingService {

    //from MeetingController
    Meeting getById(int meetingId);

    void notify(int meetingId);

    void addComment(Meeting meeting, Comment comment);

    void deleteMeeting(Meeting meeting);

    void endMeeting(Meeting meeting);

    void openMeeting(Meeting meeting);

    void updateMeeting(Integer studyId, Integer meetingId, Meeting newMeeting, Boolean isGoingToBeNotified);

    void joinMeetingMember(Meeting meeting);

    void leaveMeetingMember(Meeting meeting);

    Attendance confirmAttendanceById(int attendanceId);

    Attendance rejectAttendanceById(int attendanceId);

    //from SWF
    void addMeeting(Integer studyId, Meeting meeting);

    //from ResourceController
    void addUrlResource(Meeting meeting, Resource resource);

    void addFileResource(Meeting meeting, Resource resource, UploadFile uploadFile);

    void deleteResourceFromMeeting(int meetingId, int resourceId);

    //from MainController
    List<Meeting> getMeetingListByStudyId(int studyId, int rows);

    //from StudyListener
    List<Meeting> findActiveMeetings();
    
    List<Presentation> getPresentationListByMeetingIdWithSort( int meetingId);

    void deleteComment(int meetingId, int commentId);

	void notifyCommentAdded(Meeting meeting, Comment comment);

    List<Meeting> findActiveMeetings(int count);
}
