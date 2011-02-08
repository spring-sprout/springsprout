package springsprout.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import springsprout.common.util.ValidationUtils;
import springsprout.domain.Member;
import springsprout.domain.Right;
import springsprout.domain.Role;
import springsprout.domain.enumeration.MemberStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class SpringSproutUserDetail implements UserDetails {
	
	private Member member;
	private List<GrantedAuthority> authorities;

	public SpringSproutUserDetail(Member member) {
		this.member = member;
		Set<Role> roles = member.getRoles();

		if(ValidationUtils.isNull(roles)) return;

		authorities = new ArrayList<GrantedAuthority>();

		for(Role role : roles){
			authorities.add(new GrantedAuthorityImpl("ROLE_" + role.getName().toUpperCase()));
			for(Right right : role.getRights())
				authorities.add(new GrantedAuthorityImpl("RIGHT_" + right.getName().toUpperCase()));
		}
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return member.getPassword();
	}

	public String getUsername() {
		return member.getEmail();
	}

	public boolean isAccountNonExpired() {
		if(member.getStatus() == MemberStatus.OUT)
			return false;
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return (member.getStatus() == MemberStatus.JOIN);
	}

	public Member getMember() {
		return member;
	}

}
