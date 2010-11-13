package springsprout.modules.member;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springsprout.domain.Member;
import springsprout.domain.Role;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.modules.role.RoleRepository;
import springsprout.modules.study.StudyRepository;
import springsprout.service.notification.mail.SendMailService;
import springsprout.service.notification.message.ConfirmMail;
import springsprout.service.notification.message.SpringSproutMessage;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceImplTest {

	MemberServiceImpl memberService;

	@Mock MemberRepositoryImpl mockMemberRepository;
	@Mock RoleRepository mockRoleRepository;
	@Mock StudyRepository mockStudyRepository;
    @Mock SendMailService mockSendMailService;
    @Mock PasswordEncoder mockPasswordEncoder;

	@Before
	public void make() throws Exception {
		memberService = new MemberServiceImpl();
		memberService.repository = mockMemberRepository;
		memberService.roleRepository = mockRoleRepository;
		memberService.studyRepository = mockStudyRepository;
        memberService.passwordEncoder = mockPasswordEncoder;
        memberService.sendMailService = mockSendMailService;
	}

	@Test
	public void confirmMember() throws Exception {
		Member member = new Member();
		String email = "whiteship@email.com";
		member.setEmail(email);
		String authCode = member.getAuthCode();

        assertThat(member.getStatus(), is(MemberStatus.JOIN_WAIT));
        assertThat(member.getJoined(), is(nullValue()));
        assertThat(member.getRoles().size(), is(0));

		Role mockMemberRole = new Role();
		mockMemberRole.setName("member");
		when(mockRoleRepository.getMemberRole()).thenReturn(mockMemberRole);
		when(mockMemberRepository.findByEmail(email)).thenReturn(member);

		memberService.confimMember(email, authCode);
        assertThat(member.getStatus(), is(MemberStatus.JOIN));
        assertThat(member.getRoles().size(), is(1));
        assertThat(member.getJoined(), is(notNullValue()));
        assertThat(member.getRoles().iterator().next(), is(mockMemberRole));
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void userNotFoundWhenConfirmMember() throws Exception {
		String email = "whiteship@mail.com";
		when(mockMemberRepository.findByEmail(email)).thenReturn(null);
		memberService.confimMember(email, "authCode");
	}

    @Test
    public void addMember(){
        Member member = new Member();
        member.setEmail("whiteship@mail.com");
        member.setPassword("pass");

        memberService.add(member);

        verify(mockPasswordEncoder).encodePassword("pass", null);
        verify(mockMemberRepository).add(member);
        verify(mockSendMailService).sendMessage(Matchers.<SpringSproutMessage>anyObject());
        assertThat(member.getAvatar(), is(CoreMatchers.<Object>notNullValue()));
    }

}
