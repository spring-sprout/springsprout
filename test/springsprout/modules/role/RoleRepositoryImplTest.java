package springsprout.modules.role;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import springsprout.common.test.DBUnitSupport;
import springsprout.domain.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
@Transactional
public class RoleRepositoryImplTest extends DBUnitSupport{

	@Autowired
	RoleRepository roleRepository;
	
	@Test
	public void di() throws Exception {
		assertNotNull(roleRepository);
	}
	
	@Test
	public void getMemberRole() throws Exception {
        insertXmlData("testData.xml");
		Role role = roleRepository.getMemberRole();
		assertNotNull(role);
		assertEquals(RoleRepository.DEFAULT_ROLE_NAME, role.getName().toLowerCase());
	}

    @Test
    public void getAdminRole() throws Exception {
        insertXmlData("testData.xml");
        Role role = roleRepository.getAdminRole();
        assertNotNull(role);
        assertEquals(RoleRepository.ADMIN_ROLE_NAME, role.getName().toLowerCase());

    }
	
}
