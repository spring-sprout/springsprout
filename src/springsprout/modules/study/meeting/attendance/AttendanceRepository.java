package springsprout.modules.study.meeting.attendance;

import springsprout.common.dao.GenericDao;
import springsprout.domain.Attendance;
import springsprout.domain.Member;
import springsprout.domain.Study;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 12
 * Time: 오후 6:56:00
 * To change this template use File | Settings | File Templates.
 */
public interface AttendanceRepository extends GenericDao<Attendance>{

    int getConfirmedAttendanceCountOf(Member member);

	int getTotalAttandanceCountOf(Member member);

	int getConrimedAttendanceCountOf(Member member, Study study);

	int getAttendanceCountOf(Member member, Study study);

}
