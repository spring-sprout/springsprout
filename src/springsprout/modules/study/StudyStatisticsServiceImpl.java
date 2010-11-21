package springsprout.modules.study;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.enumeration.DayOfWeek;
import springsprout.common.util.DateUtils;
import springsprout.domain.Meeting;
import springsprout.modules.study.support.MeetingDayOfWeekData;

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
            Integer pecentage = (int)(dayCount/total*100);

            MeetingDayOfWeekData data = new MeetingDayOfWeekData();
            data.setDayOfWeek(dayOfWeek);
            data.setMeetingCount(dayCount);
            data.setPercentage(pecentage);

            dataList.add(data);
        }

        Collections.sort(dataList, new Comparator<MeetingDayOfWeekData>(){
            public int compare(MeetingDayOfWeekData data, MeetingDayOfWeekData otherData) {
                return data.getDayOfWeek().getOrder() - otherData.getDayOfWeek().getOrder();
            }
        });
        return dataList ;
    }

}
