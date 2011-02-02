package springsprout.common.util;


import org.springframework.security.core.context.SecurityContextHolder;
import springsprout.domain.Member;
import springsprout.service.security.SpringSproutUserDetail;

@Deprecated
public class SecurityUtils {
	public static Member getCurrentMember() {
		if(SecurityContextHolder.getContext().getAuthentication() == null)
			return null;
		Object princial = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (princial instanceof String && princial.equals("roleAnonymous")) {
			return null;
		}
		else {
			return ((SpringSproutUserDetail) princial).getMember();
		}
	}
}
