package springsprout.modules.study;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.test.DBUnitSupport;
import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.modules.member.MemberRepositoryImpl;
import springsprout.modules.study.support.StudyCriteria;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
@Transactional
public class StudyRepositoryImplTest extends DBUnitSupport{
	
	@Autowired StudyRepositoryImpl sr;
	@Autowired MemberRepositoryImpl mr;
	
	@Test
	public void di() throws Exception {
		assertNotNull(sr);
	}
	
	@Test
	public void addMember() throws Exception {
		Study study = new Study();
		sr.add(study);
		
		Member member = new Member();
		mr.add(member);
		
		member.join(study);
		assertTrue(member.getStudies().contains(study));
		assertTrue(study.getMembers().contains(member));
	}

	@Test
	public void getAll() throws Exception {
		insertXmlData("testData.xml");
		assertThat(sr.getStudyList().size(), is(2));
	}

    @Test
    public void isUserAlreayJoinedIn_IsUserTheStudyManager() throws Exception {
        insertXmlData("testData.xml");
        assertThat(sr.getById(1).getMembers().size(), is(2));
        assertThat(sr.getById(2).getMembers().size(), is(1));

        Study s1 = new Study();
        s1.setId(1);
        Study s2 = new Study();
        s2.setId(2);
        Member m1 = new Member();
        m1.setId(1);
        Member m2 = new Member();
        m2.setId(2);

        //isJoined
        assertThat(sr.isUserAlreayJoinedIn(m1, 1), is(true));
        assertThat(sr.isUserAlreayJoinedIn(m2, 1), is(true));
        assertThat(sr.isUserAlreayJoinedIn(m1, 2), is(true));
        assertThat(sr.isUserAlreayJoinedIn(m2, 2), is(false));

        //isManager
        assertThat(sr.isUserTheStudyManager(m1, 1), is(true));
        assertThat(sr.isUserTheStudyManager(m2, 1), is(false));
    }

    @Test
    public void getStudyList() throws Exception {
        insertXmlData("testData.xml");
        StudyCriteria sc = new StudyCriteria();
        sc.setLimit(1);
        List<Study> sl = sr.getStudyList(sc);
        assertThat(sl.size(), is(1));

        sc.setLimit(2);
        sl = sr.getStudyList(sc);
        assertThat(sl.size(), is(2));
    }

    @Test
    public void getTotalRowsCount() throws Exception {
        insertXmlData("testData.xml");
        StudyCriteria sc = new StudyCriteria();
        int count = sr.getTotalRowsCount(sc);
        assertThat(count, is(2));
    }

    @Test
    public void getManagerByStudyId() throws Exception {
        insertXmlData("testData.xml");
        Member manager = sr.getManagerByStudyId(1);
        assertThat(manager.getId(), is(1));
    }

    
}
