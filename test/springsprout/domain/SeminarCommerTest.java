package springsprout.domain;

import org.junit.Test;
import springsprout.domain.enumeration.SeminarComerStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 22
 * Time: 오후 3:47:07
 */
public class SeminarCommerTest {

    /**
     * 등록된 회원이 참가 신청할 경우
     * - 회원 정보로 부터 이름, 이메일, 비번을 가져온다.
     */
    @Test
    public void makeFromMember(){
        Member member = new Member();
        member.setEmail("whiteship@email.com");
        member.setPassword("123");
        member.setName("whiteship");

        SeminarComer seminarComer = new SeminarComer();
            assertThat(seminarComer.getCreated(), is(notNullValue()));

        seminarComer.makeFromMember(member);
            assertThat(seminarComer.getMember(), is(member));
            assertThat(seminarComer.getName(), is("whiteship"));
            assertThat(seminarComer.getEmail(), is("whiteship@email.com"));
            assertThat(seminarComer.getPassword(), is("123"));
            assertThat(seminarComer.isThisMember(), is(true));
    }

    /**
     * 참가 신청 회원의 상태 변화
     * - ENROLLED -> CONFIRMED -> JOINED
     */
    @Test
    public void chageStatus(){
        SeminarComer seminarComer = new SeminarComer();

        seminarComer.enroll();
        assertThat(seminarComer.isEnrolled(), is(true));

        seminarComer.confirm();
        assertThat(seminarComer.isConfirmed(), is(true));

        seminarComer.join();
        assertThat(seminarComer.isJoined(), is(true));
    }

}
