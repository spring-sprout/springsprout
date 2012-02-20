package springsprout.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Role;
import springsprout.domain.Study;
import springsprout.modules.member.MemberRepositoryImpl;
import springsprout.modules.role.RoleRepository;

@Service
@Transactional
public class DefaultSecurityService implements SecurityService {

	@Autowired MemberRepositoryImpl memberRepository;
	@Autowired RoleRepository roleRepository;

	public Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null)
			return null;
		Object princial = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (princial instanceof UserDetails) {
			return ((SpringSproutUserDetail) princial).getMember();
		}
		else {
			return new NullMember();
		}
	}

	public Member getPersistentMember(){
		return memberRepository.getById(getCurrentMember().getId());
	}

	public int getCurrentMemberId() {
		if(getCurrentMember() != null)
			return getCurrentMember().getId();
		return -1;
	}

	public boolean isAdmin() {
		Role adminRole = roleRepository.getAdminRole();
		return getCurrentMember().hasRole(adminRole);
	}

	public boolean isCurrentUserOrAdmin(int id) {
		if(!isCurrentMembersInfo(id) && !isAdmin())
			throw new AccessDeniedException("다른 회원의 정보에 접근을 시도할 경우 계정이 차단 됩니다.");
		return true;
	}
	
	private boolean isCurrentMembersInfo(int id) {
		return getCurrentMemberId() == id;
	}

	public boolean isStudyManagerOrAdmin(Study study) {
		if(isCurrentMemberStudyManager(study) || isAdmin())
			return true;
		return false;
	}

	private boolean isCurrentMemberStudyManager(Study study) {
		return getCurrentMemberId() == study.getManager().getId();
	}
	
	@SuppressWarnings("serial")
	static public class NullMember extends Member {
		public NullMember() {
			setId(-1);
			setName("anonymous");
			setAvatar("http://www.gravatar.com/avatar/who?r=X");
			setEmail("anony@mous.mail");
		}
		
		public boolean isAnonymous() {
			return true;
		}
	}

	public boolean isMeetingManagerOrAdmin(Meeting meeting) {
		if(isCurrentMemberMeetingManager(meeting) || isAdmin())
			return true;
		return false;
	}

    public boolean hasLoggedInUser() {
        return !(getCurrentMember() instanceof NullMember);
    }

    private boolean isCurrentMemberMeetingManager(Meeting meeting) {
		return getCurrentMemberId() == meeting.getOwner().getId() || 
			getCurrentMemberId() == meeting.getStudy().getManager().getId();
	}

}
