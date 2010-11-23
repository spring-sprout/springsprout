package springsprout.modules.study;

import springsprout.domain.Meeting;
import springsprout.domain.Study;
import springsprout.modules.study.support.MeetingDayOfWeekData;
import springsprout.modules.study.support.MeetingMemberData;
import springsprout.modules.study.support.MemberMeetingData;
import springsprout.modules.study.support.StudyMemberData;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:46:03
 */
public interface StudyStatisticsService {
    List<MeetingDayOfWeekData> getMeetingDayStatisticsOf(Set<Meeting> meetings);

    List<MemberMeetingData> getMemberMeetingStatisticsOf(Set<Meeting> meetings);

    List<MeetingMemberData> getMeetingMemberStatisticsOf(Set<Meeting> meetings);

    List<StudyMemberData> getStudyMemberStatisticesOf(Study study);
}
