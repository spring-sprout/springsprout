package springsprout.service.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.role.RoleRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 21
 * Time: 오후 11:35:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class DefaultRoleSetupServiceTest {

    @Autowired DefaultRoleSetupService service;
    @Autowired MemberRepository memberRepository;
    @Autowired RoleRepository roleRepository;

    @Test
    public void testAddDefaultRoles() throws Exception {
        service.addDefaultRoles();
        assertThat(roleRepository.getMemberRole(), is(notNullValue()));
        assertThat(roleRepository.getAdminRole(), is(notNullValue()));
    }

    @Test
    public void testAddDefaultUsers() throws Exception {
        service.addDefaultUsers();
        assertThat(memberRepository.findByEmail(DefaultRoleSetupService.DEFAULT_ADMIN_EMAIL), is(notNullValue()));
        assertThat(memberRepository.findByEmail(DefaultRoleSetupService.DEFAULT_MEMBER_EMAIL), is(notNullValue()));
    }
}
