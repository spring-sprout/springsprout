package springsprout.modules.acl;


import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.security.AclBbs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;


/**
 * 스프링 시큐리티 도메인 ACL을 테스트 하기 위한 클래스
 * 도메인 ACL이 적용될 테스트 클래스(AclBbs)에 대한 테스트를 진행한다.
 * @author June
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"testContext.xml", "testContext-securityAcl.xml" })
@Transactional
public class AclTest {

	@Autowired AclBbsRepository aclBbsRepository;
	@Autowired SessionFactory sessionFactory;
	Logger logger = LoggerFactory.getLogger( this.getClass());
	
	AclBbs bbs;

	@Before
	public void addDefaultData() {
		initUser( "test1", "ROLE_USER");
		bbs = initBbs();
		aclBbsRepository.add(bbs);
	}
	
	@Test
	public void insert() {
		logger.info( aclBbsRepository.get( bbs).toString());
	}

	
	@Test
	public void getByManager() {
		initUser( "manager", "ROLE_USER", "ROLE_MANAGER");
		aclBbsRepository.get(bbs);
	}
	
	@Test
	public void getByAdmin() {
		initUser( "admin", "ROLE_USER", "ROLE_ADMIN");
		aclBbsRepository.get(bbs);
	}
	
	@Test(expected=AccessDeniedException.class)
	public void deleteByManager() {
		initUser( "manager", "ROLE_USER", "ROLE_MANAGER");
		aclBbsRepository.delete(bbs);
	}
	
	@Test
	public void deleteByAdmin() {
		initUser( "admin", "ROLE_USER", "ROLE_ADMIN");
		aclBbsRepository.delete(bbs);
	}
	
	@Test(expected=AccessDeniedException.class)
	public void deleteFailByUser() {
		initUser( "test2", "ROLE_USER");
		aclBbsRepository.delete( bbs);
	}
	
	/**
	 * ROLE_TESTER don't have permission
	 * @throws SQLException
	 */
	@Test(expected=AccessDeniedException.class)
	public void getFailByRole() throws SQLException {
		initUser( "test1", "ROLE_TESTER");
		aclBbsRepository.get(bbs);
	}
	
	private void initUser( String id, String... roles) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for ( int i = 0; i < roles.length; i++)
			authorities.add( new GrantedAuthorityImpl( roles[i]));
		UserDetails user = new User( id, id, true, true, true, true, authorities);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, id, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private AclBbs initBbs() {
		AclBbs bbs = new AclBbs();
		bbs.setId( UUID.randomUUID().getLeastSignificantBits());
		bbs.setTitle("Tested By Test1 ");
		bbs.setUsername("test1");
		bbs.setContents("contents");
		bbs.setIsRocked(false);
		return bbs;
	}
}
