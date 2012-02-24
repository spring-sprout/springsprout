package springsprout.modules.member;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import springsprout.common.enumeration.PersistentEnum;
import springsprout.common.util.MD5Util;
import springsprout.common.web.support.OrderParam;
import springsprout.common.web.support.Paging;
import springsprout.domain.Member;
import springsprout.domain.Role;
import springsprout.domain.Study;
import springsprout.domain.enumeration.MemberStatus;
import springsprout.modules.ajax.support.AutoCompleteParams;
import springsprout.modules.member.support.MemberContext;
import springsprout.modules.member.support.MemberSearchParam;
import springsprout.modules.role.RoleRepository;
import springsprout.modules.study.StudyRepository;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.ConfirmMail;
import springsprout.service.notification.message.PasswordMail;
import springsprout.service.security.SecurityService;

import java.util.*;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired MemberRepository repository;
	@Autowired RoleRepository roleRepository;
	@Autowired SecurityService securityService;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired @Qualifier("sendMailService") NotificationService sendMailService;
	@Autowired StudyRepository studyRepository;

    private BeanUtilsBean memberBeanUtilsBean = new BeanUtilsBean(new PersistentEnumAwareConvertUtilsBean(), new PropertyUtilsBean());

	public void add(Member member) {
        member.setup(passwordEncoder.encodePassword(member.getPassword(), null));
		repository.add(member);
		sendMailService.sendMessage(new ConfirmMail(member));
	}

	@Transactional(readOnly = true)
	public List<Member> getMemberList() {
		return repository.getMemberList();
	}

    @Transactional(readOnly = true)
    public List<Member> getMemberList(MemberSearchParam searchParam) {
        return repository.getMemberList(searchParam, new OrderParam(), null);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getTransformerMemberToMapList(MemberSearchParam searchParam, OrderParam orderParam, Paging paging) {
        List<Member> members = repository.getMemberList(searchParam, orderParam, paging);

        if(!CollectionUtils.isEmpty(members)){
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for(Member member : members){
                try {
                    result.add(memberBeanUtilsBean.describe(member));
                } catch (Exception e) {
                    throw new RuntimeException("Transformer Member To Map<String, Object> Exception!");
                }
            }
            return result;
        }

        return Collections.emptyList();
    }

    public int getTotalMemberCount(MemberSearchParam searchParam) {
        return repository.getTotalRowsCount(searchParam);
    }

//    @PostAuthorize("(returnObject.email == principal.Username) or hasRole('ROLE_ADMIN')")
	public Member getMemberById(int id) {
		return repository.getById(id);
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or (#member.email == principal.Username)")
	public void update(Member member) {
		if(StringUtils.hasText(member.getNewPassword())){
			member.setPassword(passwordEncoder.encodePassword(member.getNewPassword(), null));
		}
		repository.update(member);
	}

	public List<Member> getMemberListByContext(MemberContext context) {
		context.setTotalRowsCount(repository.getTotalRowsCount(context
				.getSearchParam()));
		return repository.getMemberListByContext(context);
	}

	public boolean confimMember(String email, String authCode) {
        // 인코딩이 이상하게 되서 서버에서 %40을 @로 못바꾼 경우에 직접 변화 처리.
        if(email.contains("%40")){
            email = email.replace("%40", "@");
        }

		Member storedMember = repository.findByEmail(email);
		if (storedMember == null) {
			throw new UsernameNotFoundException(email + " 에 해당하는 사용자가 없습니다.");
		}
		boolean result = storedMember.getAuthCode().equals(authCode);
		if (!result) {
			throw new InsufficientAuthenticationException(authCode
					+ " 인증 코드가 올바르지 않습니다.");
		} else {
			storedMember.join();
			storedMember.makeAvatar();
			addMemberRole(storedMember);
			update(storedMember);
		}
		return result;
	}

	private void addMemberRole(Member storedmember) {
		Role memberRole = roleRepository.getMemberRole();
		storedmember.addRole(memberRole);
	}

	public Member getMemberByEmail(String email) {
		return repository.findByEmail(email);
	}

	public boolean isDuplicated(String email) {
		Member member = repository.findByEmail(email);
		if (member != null)
			return true;
		return false;
	}

	/* Member 탈퇴 : 이메일 변경, 상태 OUT(30)으로 변경, 탈퇴일, 탈퇴사유 */
	@PreAuthorize("hasRole('ROLE_ADMIN') or (#member.email == principal.Username)")
	public void out(Member member) {
        member.selfOut();
    }

    /* Member 강제퇴출 : 이메일 변경, 상태 COMPULSORY_OUT(40)으로 변경, 탈퇴일, 탈퇴사유 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void compulsoryOut(Member member, String outReason) {
        member.compulsoryOut(outReason);
	}

	public List<Member> getJoinedMemberList() {
		return repository.getJoinedMemberList();
	}

	public String getMemberEmailById(Integer id) {
		return repository.getMemberEmailById(id);
	}

	public void makeNewPassword(Member member) {
		long uniqueValue = Calendar.getInstance().getTimeInMillis();
		member.setPassword(MD5Util.md5toHex(member.getEmail() + uniqueValue));
		sendMailService.sendMessage(new PasswordMail(member));
		String encodePass = passwordEncoder.encodePassword(
				member.getPassword(), null);
		member.setPassword(encodePass);
	}

    public List<Member> getMemberListByAjaxParams(AutoCompleteParams autoCompleteParam) {
        return repository.getMemberListByAjaxParams(autoCompleteParam);
    }

    public void updateByAdmin(Member updatedMember, boolean isStatusEditable) {
        Member originalMember = repository.getOriginalMemberBy(updatedMember);
        originalMember.updateNotificationsWith(updatedMember);
        if(updatedMember.getStatus() == MemberStatus.JOIN){
            addMemberRole(originalMember);
        }
        if(isStatusEditable) {
            originalMember.updateStatusWith(updatedMember);
            if(updatedMember.getStatus() == MemberStatus.COMPULSORY_OUT) {
                compulsoryOut(originalMember, updatedMember.getOutReason());
            }
        }
    }

    public void addRoleTo(Role role, Member member) {
        repository.getById(member.getId()).addRole(role);
    }

    public int getAttendenceRateOf(Member member, Study study) {
        return repository.getAttdRateBy(member.getId(), study.getId());

    }

    class PersistentEnumAwareConvertUtilsBean extends ConvertUtilsBean {
        public String convert(Object value) {
            if(value instanceof PersistentEnum) return String.valueOf(((PersistentEnum) value).getValue());
            return super.convert(value);
        }
    }

}
