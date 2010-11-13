package springsprout.modules.seminar.schedule;

import springsprout.domain.SeminarDetailSchedule;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 28
 * Time: 오후 3:42:39
 */
public interface SeminarScheduleService {

    List<SeminarDetailSchedule> scheduleListOfStudy(int sid);

    void addSchedult(int sid, SeminarDetailSchedule schedule);

    SeminarDetailSchedule getById(int id);

    void update(SeminarDetailSchedule schedule);

    void removeSchedulrOfSeminar(int sid, int id);
}
