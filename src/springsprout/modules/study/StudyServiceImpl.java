package springsprout.modules.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springsprout.domain.Member;
import springsprout.domain.Study;
import springsprout.domain.enumeration.StudyStatus;
import springsprout.modules.calendar.GoogleCalendarService;
import springsprout.modules.member.MemberRepository;
import springsprout.modules.study.support.StudyContainer;
import springsprout.modules.study.support.StudyCriteria;
import springsprout.modules.study.support.StudyIndexInfo;
import springsprout.service.notification.NotificationService;
import springsprout.service.notification.message.StudyMailMessage;
import springsprout.service.security.SecurityService;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {

	@Autowired StudyRepository repository;
	@Autowired SecurityService securityService;
    @Autowired MemberRepository memberRepository;
    @Autowired @Qualifier("unifiedNotificationService") NotificationService notiService;
    @Autowired GoogleCalendarService calendarService;
    @Autowired ThreadPoolTaskExecutor myExecutor;
    
	public void addStudy(final Study study) {
		Member currentMember = securityService.getPersistentMember();
		currentMember.addManagedStudy(study);
		repository.add(study);

        myExecutor.execute(new Runnable(){
            public void run() {
                notiService.sendMessage(new StudyMailMessage(study, StudyStatus.OPEN, memberRepository.getMemberList()));
                calendarService.createNewStudyCalendar(study);
            }
        });

	}

	@PreAuthorize("(#study.manager.email == principal.Username) or hasRole('ROLE_ADMIN')")
	public void updateStudy(final Study study, final Boolean isGoingToBeNotified) {
		final Member currentMember = securityService.getCurrentMember();
		repository.update(study);

        myExecutor.execute(new Runnable(){
            public void run() {
                calendarService.synchronizeForLegacy(study);
                calendarService.synchronizeForLegacy(study.getMeetings());

                calendarService.updateStudyCalendar(study);
                calendarService.addToAccessControlList(study, currentMember);

                if (isGoingToBeNotified && (study.getStatus() != StudyStatus.ENDED)) {
                    notiService.sendMessage(new StudyMailMessage(study, StudyStatus.UPDATED, memberRepository.getMemberList()));
                }
            }
        });
	}

	public void addCurrentMember(final Study study) {
		final Member currentMember = securityService.getPersistentMember();
		study.addMember(currentMember);

        myExecutor.execute(new Runnable(){
            public void run() {
                calendarService.addToAccessControlList(study, currentMember);
            }
        });
	}

	public void removeCurrentMember(final Study study) {
		final Member currentMember = securityService.getPersistentMember();
        study.removeMember(currentMember);

        myExecutor.execute(new Runnable(){
            public void run() {
                calendarService.removeToAccessControlList(study, currentMember);
            }
        });
	}

	public Study getStudyById(int id) {
		return repository.getById(id);
	}

	@PreAuthorize("(#study.manager.email == principal.Username) or hasRole('ROLE_ADMIN')")
	public void deleteStudy(final Study study) {
        myExecutor.execute(new Runnable(){
            public void run() {
                calendarService.deleteStudyCalendar(study);
            }
        });
		
		study.setStatus(StudyStatus.DELETED);
	}

	@PreAuthorize("(#study.manager.email == principal.Username) or hasRole('ROLE_ADMIN')")
	public void endStudy(Study study) {
		study.endStudy();
		repository.update(study);
	}

	public List<Study> findActiveStudies() {
		return repository.findActiveStudies();
	}

    public List<Study> findActiveStudies(int rows) {
        return repository.findActiveStudies(rows);
    }

	public List<Study> findPastStudies() {
		return repository.findPastStudies();
	}

    @Transactional(readOnly = true)
    public StudyContainer findStudies(StudyCriteria cri) {
        StudyContainer container = new StudyContainer();
        container.setList(repository.getStudyList(cri));
        container.setTotal(repository.getTotalRowsCount(cri));
        return container;
    }

    public void startStudy(Study study) {
		study.startStudy();
		repository.update(study);
	}

    public boolean isCurrentUserAlreadyJoinedIn(int studyId) {
        Member currentUser = securityService.getCurrentMember();
        if(currentUser.isAnonymous())
            return false;
        return repository.isUserAlreayJoinedIn(currentUser, studyId);   
    }

    public boolean isCurrentUserTheStudyManagerOrAdmin(int studyId) {
        Member currentUser = securityService.getCurrentMember();
        if(currentUser.isAnonymous())
            return false;
        boolean isManager = repository.isUserTheStudyManager(currentUser, studyId);
        boolean isAdmin = securityService.isAdmin();
        return isManager || isAdmin;
    }

    public void notify(int studyId) {
        Study study = repository.getById(studyId);
        notiService.sendMessage(new StudyMailMessage(study, StudyStatus.UPDATED, study.getCurrentMembers()));
    }
    
    @Transactional(readOnly=true)
    public StudyIndexInfo makeStudyIndexInfo() {
    	return new StudyIndexInfo( findActiveStudies(), repository.findPastStudies());
    }

}
