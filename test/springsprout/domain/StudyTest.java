package springsprout.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import springsprout.domain.enumeration.StudyStatus;
import springsprout.domain.Study;
import springsprout.modules.study.exception.StudyMaximumOverException;

public class StudyTest {
	
	private Study study;
	private Member manager;

	@Before
	public void setUp(){
		manager = new Member();
		study = new Study(manager);
	}

	@Test
	public void create() throws Exception {
		assertNotNull(study);
		assertEquals(manager, study.getManager());
		assertEquals(StudyStatus.OPEN, study.getStatus());
	}
	
	@Test
	public void maximumCount() throws Exception {
		Study study = new Study();
		study.setMaximum(null);
		assertThat(study.getMaximumCount(), is("무제한"));
		study.setMaximum(5);
		assertThat(study.getMaximumCount(), is("5"));
	}

    @Test
    public void addMember(){
        Study study = new Study();
        Member member = new Member();

        study.addMember(member);

        assertThat(study.getCurrentMembers().size(), is(1));
        assertThat(member.getStudies().size(), is(1));
    }


    @Test(expected = StudyMaximumOverException.class)
    public void addMemberException(){
        Study study = new Study();
        study.setMaximum(1);

        Member member = new Member();

        study.addMember(member);

        assertThat(study.getCurrentMembers().size(), is(1));
        assertThat(member.getStudies().size(), is(1));
        
        study.addMember(new Member());
    }

}
