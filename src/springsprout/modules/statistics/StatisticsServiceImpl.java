package springsprout.modules.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.*;
import springsprout.domain.enumeration.StatisticsChartType;
import springsprout.domain.enumeration.StatisticsColumnType;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.StudyRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 6
 * Time: 오후 11:16:48
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired StudyRepository studyRepository;
    @Autowired MemberRepository memberRepository;

    public Statistics getMemberNumberPerMonthStatics() {
        return null;    
    }

    public Statistics getStudyAttendanceNumPerMeeting() {
        Statistics statistics = new Statistics(StatisticsChartType.MOTION, "스터디 참석자 통계 그래프")
            .withColumn(StatisticsColumnType.STRING, "스터디 이름")
            .withColumn(StatisticsColumnType.DATE, "모임 날짜")
            .withColumn(StatisticsColumnType.NUMBER, "참석자 수")
            .withColumn(StatisticsColumnType.NUMBER, "모임 횟수")
            .withColumn(StatisticsColumnType.NUMBER, "참석율")
            .withColumn(StatisticsColumnType.STRING, "스터디 상태")
            .withColumn(StatisticsColumnType.STRING, "모임명 이름");

        List<Study> studyList = studyRepository.getStudyList();
        for(Study study : studyList){
            for(Meeting meeting : study.getMeetings()){
                statistics.addRow(
                        study.getStudyName(),
                        meeting.getOpenDate(),
                        meeting.getAttendanceCount(),
                        meeting.getCnt(),
                        meeting.getAttendanceRate(),
                        study.getStatus().getDescr(),
                        meeting.getTitle()
                );
            }
        }
        return statistics;
    }

    public Statistics getMemberNumPerDay() {
        Statistics statistics = new Statistics(StatisticsChartType.MOTION, "회원수 통계 그래프")
            .withColumn(StatisticsColumnType.STRING, "구분")
            .withColumn(StatisticsColumnType.DATE, "날짜")
            .withColumn(StatisticsColumnType.NUMBER, "회원수");

        List<Member> memberList = memberRepository.getJoinedMemberList();
        int memberCount = 1;
        int adminCount = 1;
        for(int i = 0 ; i < memberList.size() ; i++){
            Member member = memberList.get(i);
            for(Role role : member.getRoles()){
                if(role.getName().toLowerCase().equals("member")){
                    statistics.addRow("회원", member.getJoined(), memberCount++);
                    statistics.addRow("관리자", member.getJoined(), adminCount);
                }
                else if(role.getName().toLowerCase().equals("admin")){
                    statistics.addRow("관리자", member.getJoined(), adminCount++);
                    statistics.addRow("회원", member.getJoined(), memberCount);
                }
            }
        }
        
        return statistics;
    }
}
