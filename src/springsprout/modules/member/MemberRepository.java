package springsprout.modules.member;

import java.util.List;
import java.util.Map;

import springsprout.common.dao.GenericDao;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.modules.member.support.MemberContext;
import springsprout.modules.member.support.MemberSearchParam;
import springsprout.modules.ajax.support.AutoCompleteParams;

public interface MemberRepository extends GenericDao<Member> {

	List<Member> getMemberList();

    List<Member> getMemberList(MemberSearchParam searchParam, OrderParam orderParam, Paging paging);

	int getTotalRowsCount(MemberSearchParam searchParam);

	List<Member> getMemberListByContext(MemberContext context);

	void deleteAll();

	Member findByEmail(String email);

	List<Member> getJoinedMemberList();

	String getMemberEmailById(Integer id);

    List<Member> getMemberListByAjaxParams(AutoCompleteParams autoCompleteParam);

    Member getOriginalMemberBy(Member updatedMember);

    int getAttdRateBy(Integer memberId, Integer studyId);
}