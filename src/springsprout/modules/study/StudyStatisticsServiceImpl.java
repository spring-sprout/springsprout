package springsprout.modules.study;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.enumeration.DayOfWeek;
import springsprout.common.util.DateUtils;
import springsprout.domain.Attendance;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.modules.study.support.MeetingDayOfWeekData;
import springsprout.modules.study.support.MeetingMemberData;
import springsprout.modules.study.support.MemberMeetingData;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 21
 * Time: 오후 9:34:28
 */
@Service
@Transactional
public class StudyStatisticsServiceImpl implements StudyStatisticsService {

    public List<MeetingDayOfWeekData> getMeetingDayStatisticsOf(Set<Meeting> meetings) {
        Double total = new Double(meetings.size());
        if(total < 1){
            return null;
        }

        Map<DayOfWeek, Integer> dayCountMap = new HashMap<DayOfWeek, Integer>();

        for(Meeting meeting : meetings) {
            DayOfWeek day = DateUtils.extractDayOfMeekFrom(meeting.getOpenDate());
            Integer count = dayCountMap.get(day);
            if(count == null) {
                dayCountMap.put(day, 1);
            } else {
                dayCountMap.put(day, count + 1);
            }
        }

        List<MeetingDayOfWeekData> dataList  = new ArrayList<MeetingDayOfWeekData>();
        for(DayOfWeek dayOfWeek : dayCountMap.keySet()){
            Integer dayCount = dayCountMap.get(dayOfWeek);
            Integer percentage = (int)(dayCount/total*100);

            MeetingDayOfWeekData data = new MeetingDayOfWeekData();
            data.setDayOfWeek(dayOfWeek);
            data.setMeetingCount(dayCount);
            data.setPercentage(percentage);

            dataList.add(data);
        }

        Collections.sort(dataList, new Comparator<MeetingDayOfWeekData>(){
            public int compare(MeetingDayOfWeekData data, MeetingDayOfWeekData otherData) {
                return data.getDayOfWeek().getOrder() - otherData.getDayOfWeek().getOrder();
            }
        });
        return dataList ;
    }

    public List<MemberMeetingData> getMemberMeetingStatisticsOf(Set<Meeting> meetings) {
        Double total = new Double(meetings.size());
        Map<Member, Integer> memberMeetingMap = new HashMap<Member, Integer>();

        for(Meeting meeting : meetings){
            for(Attendance attendance : meeting.getAttendances()){
                if(attendance.isAttended()){
                    Member member = attendance.getMember();
                    Integer meetingCount = memberMeetingMap.get(member);
                    if(meetingCount == null){
                        memberMeetingMap.put(member, 1);
                    } else {
                        memberMeetingMap.put(member, meetingCount + 1);
                    }
                }
            }
        }

        List<MemberMeetingData> dataList = new ArrayList<MemberMeetingData>();
        for(Member member : memberMeetingMap.keySet()) {
            Integer meetingCount = memberMeetingMap.get(member);
            Integer percentage = (int)(meetingCount/total*100);

            MemberMeetingData data = new MemberMeetingData();
            data.setMember(member);
            data.setMeetingCount(meetingCount);
            data.setPercentage(percentage);

            dataList.add(data);
        }

        Collections.sort(dataList, new Comparator<MemberMeetingData>(){
            public int compare(MemberMeetingData data, MemberMeetingData otherData) {
//                return data.getMember().getName().compareTo(otherData.getMember().getName());
                return otherData.getPercentage() - data.getPercentage();
            }
        });
        

        return dataList;
    }

    public List<MeetingMemberData> getMeetingMemberStatisticsOf(Set<Meeting> meetings) {
        List<MeetingMemberData> dataList = new ArrayList<MeetingMemberData>();

        for(Meeting meeting : meetings) {
            MeetingMemberData data = new MeetingMemberData();
            data.setMeeting(meeting);
            data.setAttendedCount(meeting.getAttendanceCount());
            data.setRealCount(meeting.getAttendedCount());
            dataList.add(data);
        }

        return dataList;
    }

}
