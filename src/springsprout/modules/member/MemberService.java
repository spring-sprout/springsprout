package springsprout.modules.member;

import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.domain.Member;
import springsprout.domain.Role;
import springsprout.domain.Study;
import springsprout.modules.ajax.support.AutoCompleteParams;
import springsprout.modules.member.support.MemberContext;
import springsprout.modules.member.support.MemberSearchParam;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 2. 26
 * Time: 오후 12:52:13
 */
public interface MemberService {

    public void add(Member member);

    public List<Member> getMemberList();

    public List<Member> getMemberList(MemberSearchParam searchParam);

    public List<Map<String, Object>> getTransformerMemberToMapList(MemberSearchParam searchParam, OrderParam orderParam, Paging paging);

    public int getTotalMemberCount(MemberSearchParam searchParam);

    public Member getMemberById(int id);

    public void delete(int id);

    public void update(Member member);

    public List<Member> getMemberListByContext(MemberContext context);

    public boolean confimMember(String email, String authCode);

    public Member getMemberByEmail(String email);

    public boolean isDuplicated(String email);

    public void out(Member member);

    public void compulsoryOut(Member member, String outReason);

    public List<Member> getJoinedMemberList();

    public String getMemberEmailById(Integer id);

    public void makeNewPassword(Member member);

    public List<Member> getMemberListByAjaxParams(AutoCompleteParams autoCompleteParam);

    void updateByAdmin(Member updatedMember, boolean statusEdit);

    void addRoleTo(Role role, Member member);

    int getAttendenceRateOf(Member member, Study study);
}
