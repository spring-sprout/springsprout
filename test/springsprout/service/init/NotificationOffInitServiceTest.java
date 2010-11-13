package springsprout.service.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.domain.Member;
import springsprout.modules.member.MemberRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 4. 19
 * Time: 오후 2:24:56
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class NotificationOffInitServiceTest extends DBUnitSupport {

    @Autowired NotificationOffInitService service;
    @Autowired MemberRepository memberRepository;

    @Test
    public void testNotificationOffAllMember() throws Exception {
        insertXmlData("testData.xml");
        Member whiteship = memberRepository.getById(1);
        assertThat(whiteship.getIsAllowedEmail(), is(true));

        service.notificationOffAllMember();

        memberRepository.flush();
        whiteship = memberRepository.getById(1);
        assertThat(whiteship.getIsAllowedEmail(), is(false));
    }
}
