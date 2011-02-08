package springsprout.modules.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsprout.common.util.BeanUtils;
import springsprout.domain.Notice;
import springsprout.modules.main.support.NoticeDTO;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.notice.support.NoticeContainer;
import springsprout.modules.notice.support.NoticeCriteria;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.NoticeMailMessage;
import springsprout.service.security.SecurityService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoticeService {

	@Autowired NoticeRepository noticeRepository;
    @Autowired MemberRepository memberRepository;
	@Autowired SecurityService securityService;
    @Autowired @Qualifier("unifiedNotificationService") NotificationService notiService;

	public void add(Notice notice) {
		notice.setMember(securityService.getCurrentMember());
		notice.setCreated(new Date());
		noticeRepository.add(notice);

        notiService.sendMessage(new NoticeMailMessage(notice, memberRepository.getJoinedMemberList()));
	}
	
	public void add(Notice notice, String notification) {
		notice.setMember(securityService.getCurrentMember());
		notice.setCreated(new Date());
		noticeRepository.add(notice);
		
		if (notification != null) {
			notiService.sendMessage(new NoticeMailMessage(notice, memberRepository.getJoinedMemberList()));
		}
	}

    public List<Notice> getAll() {
		return noticeRepository.getAll();
	}

    public List<NoticeDTO> getNoticeDTOAll() {
		List<NoticeDTO> noticeDTOList = new ArrayList<NoticeDTO>();
        for(Notice notice :noticeRepository.getAllByCreatedOrderDesc()){
            NoticeDTO nDTO = new NoticeDTO();
            BeanUtils.beanCopy(nDTO,notice);
            noticeDTOList.add(nDTO);
        }
        return noticeDTOList;
	}
    
    @Transactional(readOnly = true)
    public NoticeContainer  getNoticeByPaging(NoticeCriteria cri) {
        NoticeContainer container = new NoticeContainer();
        container.setList(noticeRepository.getListSorted(cri));
        container.setTotal(noticeRepository.getTotalRowsCount(cri));
        return container;
    }

	public Notice get(int id) {
		return noticeRepository.getById(id);
	}

	public void update(Notice notice) {
		noticeRepository.update(notice);
	}

	public void delete(int id) {
		noticeRepository.deleteById(id);
	}

    public Notice getTheLastetOne() {
        return noticeRepository.getTheLastedOne();
    }
}
