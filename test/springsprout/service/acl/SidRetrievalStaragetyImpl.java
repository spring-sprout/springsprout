package springsprout.service.acl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.acls.model.SidRetrievalStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SidRetrievalStaragetyImpl implements SidRetrievalStrategy {

	public List<Sid> getSids( Authentication authentication) {
		Collection<GrantedAuthority> authorities = authentication.getAuthorities();
		List<Sid> sids = new ArrayList<Sid>( authorities.size() + 1);

		sids.add( new PrincipalSid( ((UserDetails) authentication.getPrincipal()).getUsername()));

		for ( GrantedAuthority authority : authorities) {
			sids.add( new GrantedAuthoritySid( authority));
		}

		return sids;
	}

}
