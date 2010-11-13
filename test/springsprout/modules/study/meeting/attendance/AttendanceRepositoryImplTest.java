package springsprout.modules.study.meeting.attendance;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.is;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.common.test.DBUnitSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 11. 12
 * Time: 오후 7:32:05
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@Transactional
public class AttendanceRepositoryImplTest extends DBUnitSupport {

    @Autowired
    AttendanceRepositoryImpl attendanceRepository;

    @Test
    public void getConfirmedAttendanceCountOf() throws Exception {
        insertXmlData("testData.xml");
        Member member = new Member();
        member.setId(1);
        assertThat(attendanceRepository.getConfirmedAttendanceCountOf(member), is(2));
    }

    @Test
    public void getTotalAttandanceCountOf() throws Exception {
        insertXmlData("testData.xml");
        Member member = new Member();
        member.setId(1);
        assertThat(attendanceRepository.getTotalAttandanceCountOf(member), is(4));
    }

    @Test
    public void getConrimedAttendanceCountOf() throws Exception {
        insertXmlData("testData.xml");
        checkConrimedAttendanceCountOf(1, 1, 1);
        checkConrimedAttendanceCountOf(1, 2, 1);
        checkConrimedAttendanceCountOf(2, 2, 1);
    }

    @Test
    public void getAttendanceCountOf() throws Exception {
        insertXmlData("testData.xml");
        checkAttendanceCountOf(1, 1, 2);
        checkAttendanceCountOf(1, 2, 2);
        checkAttendanceCountOf(2, 2, 2);
    }

    private void checkConrimedAttendanceCountOf(int memberId, int studyId, int count) {
        Member member = new Member();
        Study study = new Study();
        member.setId(memberId);
        study.setId(studyId);
        assertThat(attendanceRepository.getConrimedAttendanceCountOf(member, study), is(count));
    }


    private void checkAttendanceCountOf(int memberId, int studyId, int count) {
        Member member = new Member();
        Study study = new Study();
        member.setId(memberId);
        study.setId(studyId);
        assertThat(attendanceRepository.getAttendanceCountOf(member, study), is(count));
    }

}
