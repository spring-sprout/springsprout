package springsprout.service.security;

import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Study;

public interface SecurityService {

    /**
     * 현재 사용자 정보를 참조용으로만 사용할 때 사용.
     * @return 현재 사용자
     */
	public Member getCurrentMember();

    /**
     * 현재 사용자의 정보를 변경하고 싶을 때 사용.
     * @return 현재 사용자
     */
	public Member getPersistentMember();

	public int getCurrentMemberId();

	public boolean isAdmin();

	public boolean isCurrentUserOrAdmin(int id);

	public boolean isStudyManagerOrAdmin(Study study);

	public boolean isMeetingManagerOrAdmin(Meeting meeting);

    boolean hasLoggedInUser();

}