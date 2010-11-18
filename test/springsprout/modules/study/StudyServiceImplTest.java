package springsprout.modules.study;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.enumeration.StudyStatus;
import springsprout.modules.calendar.GoogleCalendarService;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.exception.StudyMaximumOverException;
import springsprout.modules.study.support.StudyAttrList;
import springsprout.service.notification.UnifiedNotificationService;
import springsprout.service.security.SecurityService;

@RunWith(MockitoJUnitRunner.class)
public class StudyServiceImplTest {

	StudyServiceImpl service;
	@Mock SecurityService securityService;
	@Mock StudyRepositoryImpl repository;
    @Mock UnifiedNotificationService notiService;
    @Mock MemberRepository memberRepository;
    @Mock GoogleCalendarService googleCalendarService;
	
	@Before
	public void setUp() throws Exception {
		service = new StudyServiceImpl();
		service.securityService = securityService;
		service.repository = repository;
        service.unifiedNotificationService = notiService;
        service.memberRepository = memberRepository;
	}

	@Test
	public void addStudy() throws Exception {
		Study study = new Study();
		Member member = new Member();
		
		when(securityService.getPersistentMember()).thenReturn(member);

		service.addStudy(study);

		assertEquals(member, study.getManager());
		assertTrue(study.getMembers().contains(member));
	}
	
	@Test
	public void addCurrentMember() throws Exception {
		Study study = new Study();
		study.setMaximum(2);
		Member currentMember = new Member();

		// check add 1
		when(securityService.getPersistentMember()).thenReturn(currentMember);
		service.addCurrentMember(study);
		checkStudyAndMemberSize(study, 1, currentMember, 1);
		
		// check duplicated member add
		service.addCurrentMember(study);
		checkStudyAndMemberSize(study, 1, currentMember, 1);
		
		// check add 2
		currentMember = new Member("keesun@email.com");
		when(securityService.getPersistentMember()).thenReturn(currentMember);
		service.addCurrentMember(study);
		checkStudyAndMemberSize(study, 2, currentMember, 1);

		// check add over limit error!!
        try {
            service.addCurrentMember(study);
            fail();
        } catch(StudyMaximumOverException e) {

        }
		checkStudyAndMemberSize(study, 2, currentMember, 1);

        //TODO NO_LIMIT_MEMBER_COUNT test
	}

	private void checkStudyAndMemberSize(Study study, int memberSetSize,
			Member currentMember, int studySetSize) {
		assertThat(study.getMembers().size(), is(memberSetSize));
		assertTrue(study.getMembers().contains(currentMember));
		assertThat(currentMember.getStudies().size(), is(studySetSize));
		assertTrue(currentMember.getStudies().contains(study));		
	}

    @Test
    public void removeCurrentMember() {
        Member member = new Member();
        Study study = new Study();
        study.addMember(member);
        assertThat(study.getMemberCount(), is(1));

        when(securityService.getPersistentMember()).thenReturn(member);

        service.removeCurrentMember(study);
        assertThat(study.getMemberCount(), is(0));
    }

    @Test
    public void endStudy(){
        Study study = new Study();
        assertThat(study.getStatus(), is(StudyStatus.OPEN));
        service.endStudy(study);
        assertThat(study.getStatus(), is(StudyStatus.ENDED));
    }

    @Test
    public void startStudy(){
        Study study = new Study();
        assertThat(study.getStatus(), is(StudyStatus.OPEN));
        service.startStudy(study);
        assertThat(study.getStatus(), is(StudyStatus.STARTED));
    }
	
    @Test
	public void printData() {
		Logger.getRootLogger().info( StudyAttrList.TITLE_ADD);
	}
	
}
