package springsprout.service.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import springsprout.domain.Member;
import springsprout.domain.Role;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.role.RoleRepository;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 21
 * Time: 오후 11:19:43
 */
@Service
@Transactional
public class DefaultRoleSetupService {

	@Autowired PlatformTransactionManager transactionManager;
    @Autowired MemberRepository memberRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public static final String DEFAULT_MEMBER_EMAIL = "springsprout@springsprout.org";
    public static final String DEFAULT_ADMIN_EMAIL = "s2cadmin@springsprout.org";
    
	public void setup() {
    	TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    	transactionTemplate.afterPropertiesSet();
    	
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	addDefaultRoles();
		    	addDefaultUsers();
			}
		});
    }    

    /**
     * Add Admin, Member Role
     */
    public void addDefaultRoles(){
        if(roleRepository.getAdminRole() == null){
            Role admin = new Role();
            admin.setName("admin");
            admin.setDescr("administration");
            roleRepository.add(admin);
        }

        if(roleRepository.getMemberRole() == null){
            Role member = new Role();
            member.setName("member");
            member.setDescr("default role");
            roleRepository.add(member);
        }
    }

    /**
     *  springsprout@springsprout.org  / springsprout
     *  s2cadmin@springsprout.org  / s2cadmin)
     */
    public void addDefaultUsers(){
        if(memberRepository.findByEmail(DEFAULT_MEMBER_EMAIL) == null){
            Member member = new Member(DEFAULT_MEMBER_EMAIL);
            member.setPassword(passwordEncoder.encodePassword("springsprout", null));
            member.setName("default user");
            member.setStatus( MemberStatus.JOIN);
            member.addRole(roleRepository.getMemberRole());
            memberRepository.add(member);
        }
        if(memberRepository.findByEmail(DEFAULT_ADMIN_EMAIL) == null){
            Member admin = new Member(DEFAULT_ADMIN_EMAIL);
            admin.setPassword(passwordEncoder.encodePassword("s2cadmin", null));
            admin.setName("admin");
            admin.setStatus( MemberStatus.JOIN);
            admin.addRole(roleRepository.getMemberRole());
            admin.addRole(roleRepository.getAdminRole());
            memberRepository.add(admin);
        }
    }

}
