package springsprout.modules.study;

import org.junit.Before;
import org.junit.Test;
import springsprout.common.enumeration.DayOfWeek;
import springsprout.common.util.DateUtils;
import springsprout.domain.Attendance;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.modules.study.support.MeetingDayOfWeekData;
import springsprout.modules.study.support.MeetingMemberData;
import springsprout.modules.study.support.MemberMeetingData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:50:58
 */
public class StudyStatisticsServiceImplTest {

    StudyStatisticsService service;
    Set<Meeting> meetings = new HashSet<Meeting>();
    Meeting meeting;
    Member member;

    @Before
    public void setUp(){
        service = new StudyStatisticsServiceImpl();

        member = new Member();
        member.setName("백기선");

        Attendance attendance = new Attendance();
        attendance.setAttended(true);
        attendance.setMember(member);

        meeting = new Meeting();
        meeting.setOpenDate(DateUtils.dateOf(2010, 11, 1, 0, 0)); // MONDAY
        meeting.addAttendance(attendance);
        meetings.add(meeting);
    }

    @Test
    public void getMeetingDayStatisticsOf() throws Exception {
        List<MeetingDayOfWeekData> list = service.getMeetingDayStatisticsOf(meetings);
        assertThat(list.size(), is(1));

        MeetingDayOfWeekData data = list.get(0);
        assertThat(data.getDayOfWeek(), is(DayOfWeek.MONDAY));
        assertThat(data.getMeetingCount(), is(1));
        assertThat(data.getPercentage(), is(100));
    }

    @Test
    public void getMemberMeetingStatisticsOf() {
        List<MemberMeetingData> list = service.getMemberMeetingStatisticsOf(meetings);
        assertThat(list.size(), is(1));

        MemberMeetingData data = list.get(0);
        assertThat(data.getMember(), is(member));
        assertThat(data.getMeetingCount(), is(1));
        assertThat(data.getPercentage(), is(100));
    }

    @Test
    public void getMeetingMemberStatisticsOf(){
        List<MeetingMemberData> list = service.getMeetingMemberStatisticsOf(meetings);
        assertThat(list.size(), is(1));

        MeetingMemberData data = list.get(0);
        assertThat(data.getMeeting(), is(meeting));
        assertThat(data.getAttendedCount(), is(1));
        assertThat(data.getRealCount(), is(1));
        assertThat(data.getPercentage(), is(100));
    }
}
