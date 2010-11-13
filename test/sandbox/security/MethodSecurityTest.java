package sandbox.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.modules.main.GraffitiRepository;
import springsprout.modules.main.GraffitiService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 19
 * Time: 오후 8:02:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml", "/testContext-security.xml"})
@Transactional
public class MethodSecurityTest extends DBUnitSupport{

    @Autowired GraffitiService graffitiService;
    @Autowired GraffitiRepository graffitiRepository;

    @Before
    public void login() throws Exception {
        insertXmlData("testData.xml");
        SecurityContext securityContext = new SecurityContextImpl();
        Authentication authentication = new UsernamePasswordAuthenticationToken("whiteship@email.com", "passwd");
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void methodAuth(){
        assertThat(graffitiRepository.getAll().size(), is(0));
        graffitiService.write("hi");
        assertThat(graffitiRepository.getAll().size(), is(1));
    }

    @After
    public void after(){
        System.out.println(SecurityContextHolder.getContext());
        SecurityContextHolder.clearContext();
        System.out.println(SecurityContextHolder.getContext());
    }

}
