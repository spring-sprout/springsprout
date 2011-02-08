package springsprout.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.util.ValidationUtils;
import springsprout.domain.Member;
import springsprout.modules.member.MemberRepository;

@Transactional
public class SpringSproutUserDetailsService implements UserDetailsService {

	@Autowired MemberRepository repository;

	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		Member member = repository.findByEmail(email);
		if( ValidationUtils.isNull(member))
			throw new UsernameNotFoundException(email + " 에 해당하는 사용자가 존재하지 않습니다.");
		return new SpringSproutUserDetail(member);
	}

}
