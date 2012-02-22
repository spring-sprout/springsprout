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
 * @since 2010.04.21
 * @author whiteship
 * @author arawn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/*-context.xml"})
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
