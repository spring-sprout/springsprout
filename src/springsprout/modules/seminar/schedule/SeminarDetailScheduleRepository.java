package springsprout.modules.seminar.schedule;

import springsprout.common.dao.GenericDao;
import springsprout.domain.SeminarDetailSchedule;

import java.util.List;

public interface SeminarDetailScheduleRepository extends GenericDao<SeminarDetailSchedule> {

    List<SeminarDetailSchedule> scheduleListOfStudy(int sid);
}
