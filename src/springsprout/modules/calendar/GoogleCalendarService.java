package springsprout.modules.calendar;

import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;

import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Study;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 8. 10
 * Time: 오후 2:54:58
 */
public interface GoogleCalendarService {

    List<CalendarEntry> getOwnCalendarList();

    CalendarEntry createNewStudyCalendar(Study study);

    void updateStudyCalendar(Study study);
    
    void deleteStudyCalendar(Study study);
    
    CalendarEventEntry createNewMeetingEvent(Meeting meeting);
    
    CalendarEventEntry updateMeetingEvent(Meeting meeting, String oldMeetingTitle);
    
    void deleteMeetingEvent(Meeting meeting);
    
    void addToAccessControlList(Study study, Member member);
    
    void removeToAccessControlList(Study study, Member member);
    
    boolean isRegisteredAccessControlList(String email, CalendarEntry calendar);
    
    public CalendarEventEntry findEventByMeeting(Meeting meeting);

	void updateStudyCalendar(Study study, Member currentMember);

	public void synchronizeForLegacy(Study study);

	public void synchronizeForLegacy(Set<Meeting> oldMeetings);

	void synchronizeForLegacy(Set<Meeting> meetings, String updatedMeetingTitle);
}
