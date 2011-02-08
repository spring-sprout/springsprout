package springsprout.domain;

import org.junit.Test;
import springsprout.domain.enumeration.SeminarComerStatus;
import springsprout.domain.enumeration.SeminarStatus;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 22
 * Time: 오전 12:24:26
 */
public class SeminarTest {

    /**
     * 세미나 생성.
     * - 기본 상태는 READY, 생성한 날짜는 자동 생성.
     * - 입장료가 있으면 isCharged가 true, 없거나 0원이면 false
     * - 기본적으로 Title, Location, Maximum, OpenDate, CloseDate, EntryStartDate, EntryEndDate, Description 설정해야 함.
     */
    @Test
    public void makeNewSeminar() {
        Seminar seminar = new Seminar();
        assertThat(seminar.getCreated(), is(notNullValue()));
        assertThat(seminar.getStatus(), is(SeminarStatus.READY));
        assertThat(seminar.isCharged(), is(false));

        seminar.setEntryFee(5000);
        assertThat(seminar.getEntryFee(), is(5000));
        assertThat(seminar.isCharged(), is(true));
        seminar.setEntryFee(0);
        assertThat(seminar.isCharged(), is(false));

        seminar.setTitle("1st SpringSprout Seminar");
        seminar.setLocation(LocationFactory.newLocation("LG CNS", "Myong-Doing Sations"));
        seminar.setMaximum(150);

        // 이하 생략.
    }

    /**
     * 세미나 상태 변화 확인
     * - READY -> ENROLLMENT_START -> ENROLLMENT_END -> SEMINAR_OPEN -> SEMINAR_CLOSE
     * - SeminarStatus
     */
    @Test
    public void changeStatus() {
        Seminar seminar = new Seminar();
        assertThat(seminar.isReady(), is(true));

        seminar.startEnrollment();
        assertThat(seminar.isEnrollmentStarted(), is(true));

        seminar.endEnrollment();
        assertThat(seminar.isEnrollmentEnded(), is(true));

        seminar.startSeminar();
        assertThat(seminar.isSeminarStated(), is(true));

        seminar.endSeminar();
        assertThat(seminar.isSeminarEnded(), is(true));
    }

    /**
     * 세미나 참석자 추가/삭제
     * - 참석자 상태를 변경 확인
     */
    @Test
    public void addAndRemoveSeminarCommner() {
        Seminar seminar = new Seminar();
        SeminarComer seminarComer = new SeminarComer();

        seminar.addSeminarCommer(seminarComer);
        assertThat(seminar.getSeminarComers().size(), is(1));
        assertThat(seminarComer.getSeminar(), is(seminar));
        assertThat(seminarComer.getStatus(), is(SeminarComerStatus.ENROLLED));
        
        seminar.removeSeminarCommer(seminarComer);
        assertThat(seminar.getSeminarComers().size(), is(0));
        assertThat(seminarComer.getSeminar(), is(nullValue()));
    }

    /**
     * 세미나 스케줄 추가/삭제
     */
    @Test
    public void addAndRemoveSeminarDetailSchedule() {
        Seminar seminar = new Seminar();
        SeminarDetailSchedule schedule = new SeminarDetailSchedule();

        seminar.addSeminarDetailSchedule(schedule);
        assertThat(seminar.getDetailSchedules().size(), is(1));
        assertThat(schedule.getSeminar(), is(seminar));

        seminar.removeSeminarDetailSchedule(schedule);
        assertThat(seminar.getDetailSchedules().size(), is(0));
        assertThat(schedule.getSeminar(), is(nullValue()));
    }

    /**
     * 댓글 추가/삭제
     */
    @Test
    public void addAndRemoveComment() {
        Seminar seminar = new Seminar();
        Comment comment = new Comment();

        seminar.addComment(comment);
        assertThat(seminar.getComments().size(), is(1));

        seminar.removeComment(comment);
        assertThat(seminar.getComments().size(), is(0));
    }

    private static class LocationFactory {
        static Location newLocation(String name, String descr) {
            Location location = new Location();
            location.setName(name);
            location.setDescr(descr);
            return location;
        }
    }

}
