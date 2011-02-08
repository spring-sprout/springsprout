package springsprout.modules.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.common.web.support.OrderParam;
import springsprout.domain.Member;
import springsprout.modules.member.support.MemberSearchParam;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class MemberRepositoryImplTest extends DBUnitSupport{
	
	@Autowired
	MemberRepositoryImpl memberRepository;
	
	@Test
	public void getMemberList() throws Exception {
		insertXmlData("testMemberData.xml");

        List<Member> members = memberRepository.getMemberList();
        assertThat(members.size(), is(10));

        MemberSearchParam param = new MemberSearchParam();
        param.setName("arawn");

        members = memberRepository.getMemberList(param, new OrderParam(), null);
        assertThat(members.size(), is(1));
        assertEquals(members.get(0).getName(), "arawn");

        param = new MemberSearchParam();
        param.setEmail("arawn.kr@gmail.com");

        members = memberRepository.getMemberList(param, new OrderParam(), null);
        assertThat(members.size(), is(1));
        assertEquals(members.get(0).getEmail(), "arawn.kr@gmail.com");

        param = new MemberSearchParam();
        param.setAllowedEmail("true");
        param.setAllowedGoogleTalk("false");

        members = memberRepository.getMemberList(param, new OrderParam(), null);
        assertThat(members.size(), is(5));

        param = new MemberSearchParam();
        param.setAllowedEmail("false");
        param.setAllowedGoogleTalk("true");

        members = memberRepository.getMemberList(param, new OrderParam(), null);
        assertThat(members.size(), is(5));
	}
	
	@Test
	public void memberTotalCountTest() throws Exception{
		insertXmlData("testData.xml");
		assertThat(memberRepository.getTotalRowsCount(new MemberSearchParam()), is(3));
	}
	
	@Test
	public void getJoinedMemberList() throws Exception {
		insertXmlData("testData.xml");
		assertEquals(3, memberRepository.getMemberList().size());
		assertEquals(2, memberRepository.getJoinedMemberList().size());
	}
	
	@Test
	public void getMemberEmailById() throws Exception {
		insertXmlData("testData.xml");
		assertEquals("whiteship@keesun.com", memberRepository.getMemberEmailById(1));
	}
	
	@Test
	public void findByEmail() throws Exception {
		insertXmlData("testData.xml");
		Member member = memberRepository.findByEmail("whiteship@keesun.com");
		assertNotNull(member);
		assertThat(member.getId(), is(1));
	}

    @Test
    public void getAttendenceRateOf() throws Exception {
        insertXmlData("testData.xml");
        int rate = memberRepository.getAttdRateBy(1, 1);    
    }
	
}
