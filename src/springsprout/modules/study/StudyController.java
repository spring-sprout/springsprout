package springsprout.modules.study;

import static springsprout.common.SpringSprout2System.JSON_VIEW;
import static springsprout.modules.study.support.StudyURLRedirectionUtils.redirectStudyView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springsprout.domain.Study;
import springsprout.modules.study.exception.StudyMaximumOverException;
import springsprout.modules.study.meeting.support.CountInfoDTO;
import springsprout.modules.study.support.StudyAttrList;
import springsprout.service.security.SecurityService;


@Controller
@RequestMapping("/study/")
@SessionAttributes("study")
public class StudyController {
	Logger log = LoggerFactory.getLogger(StudyController.class);

	private static final String STUDY_FORM = "study/form";
	private static final String STUDY_INDEX = "study/index";
	private static final String STUDY_VIEW = "study/view";
	private static final String REDIRECT_STUDY_INDEX = "redirect:/study/index";
	
	private static final String URL_STUDY_VIEW = "/study/view/";
	private static final String URL_STUDY_INDEX = "/study/index";
	
	@Autowired StudyService service;
	@Autowired SecurityService securityService;

	@RequestMapping("index")
	public String index(@RequestParam(required = false) String type, Model model) {
		model.addAttribute(StudyAttrList.LIST.key(), this.service.findActiveStudies());
        model.addAttribute(StudyAttrList.MINITAB_ACTIVE.key(), "active");
		return STUDY_INDEX;
    }
	
	@RequestMapping("index2")
	public String index2(@RequestParam(required = false) String type, Model model) {
		model.addAttribute(StudyAttrList.LIST.key(), this.service.findActiveStudies());
        model.addAttribute(StudyAttrList.MINITAB_ACTIVE.key(), "active");
        model.addAttribute(service.getStudyById(5));
		return "study/index2";
    }
	
	@RequestMapping("index3")
	public String index3(@RequestParam(required = false) String type, Model model) {
		model.addAttribute( "list", this.service.findActiveStudies());
        model.addAttribute( "minitab_active", "active");
        model.addAttribute( service.findActiveStudies().get(0));
        model.addAttribute( "activeStudies", service.findActiveStudies());
        model.addAttribute( "studyIndexInfo", service.makeStudyIndexInfo());
		return "study/index3";
    }

	@RequestMapping("index/past")
	public String index(Model model) {
        model.addAttribute(StudyAttrList.LIST.key(), this.service.findPastStudies());
		model.addAttribute(StudyAttrList.MINITAB_PAST.key(), "active");
		return STUDY_INDEX;
	}
    
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
        model.addAttribute(new Study());
        model.addAttribute(StudyAttrList.TITLE_ADD.key(), StudyAttrList.TITLE_ADD.value());
        model.addAttribute(StudyAttrList.BACKURL.key(), URL_STUDY_INDEX);
        model.addAttribute(StudyAttrList.ISUPDATE.key(), false);
        return STUDY_FORM;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addForm( @Valid Study study, BindingResult result, Model model, HttpSession session, SessionStatus status) {
		model.addAttribute(StudyAttrList.TITLE_ADD.key(), StudyAttrList.TITLE_ADD.value());
		if (result.hasErrors()) return STUDY_FORM;
		service.addStudy(study);
        status.setComplete();
		setSession(session, study.getStudyName(), " 스터디가 개설되었습니다.");
		return REDIRECT_STUDY_INDEX;
	}

    @RequestMapping("/{id}")
    public String newView(@PathVariable int id, Model model) {
        model.addAttribute("study", service.getStudyById(id));
        return "/study/newView";
    }

	@RequestMapping("view/{id}")
	public String view(@PathVariable int id, Model model) {
		Study study = service.getStudyById(id);
        model.addAttribute(study);
        model.addAttribute(StudyAttrList.MEMBER_COUNT.key(), study.getMemberCount());
        model.addAttribute(StudyAttrList.IS_ALREADY_JOIN_MEMBER.key(), service.isCurrentUserAlreadyJoinedIn(id));
        model.addAttribute(StudyAttrList.IS_MANAGER_OR_ADMIN.key(), service.isCurrentUserTheStudyManagerOrAdmin(id));
		return STUDY_VIEW;
	}

    @RequestMapping(value = "notify/{id}", method=RequestMethod.GET)
	public ModelAndView notify(@PathVariable int id, Model model, HttpSession session) {
    	service.notify(id);
        return new ModelAndView(JSON_VIEW).addObject("studyName", service.getStudyById(id).getStudyName());
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable int id, HttpSession session) {
		Study study = service.getStudyById(id);
		service.deleteStudy(study);
		setSession(session, study.getStudyName(), " 스터디가 폐쇄되었습니다.");
		return REDIRECT_STUDY_INDEX;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model) {
		Study study = service.getStudyById(id);
		model.addAttribute(study);
		model.addAttribute(StudyAttrList.TITLE_UPDATE.key(), StudyAttrList.TITLE_UPDATE.value());
        model.addAttribute(StudyAttrList.BACKURL.key(), URL_STUDY_VIEW + study.getId());
        model.addAttribute(StudyAttrList.ISUPDATE.key(), true);
        return STUDY_FORM;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String updateForm(boolean isGoingToBeNotified, @Valid Study study, BindingResult result, HttpSession session, SessionStatus status)
			throws ServletRequestBindingException {
		if (result.hasErrors()) return STUDY_FORM;
		service.updateStudy(study, isGoingToBeNotified);
		status.setComplete();
        setSession(session, study.getStudyName(), " 스터디가 수정되었습니다.");
		return redirectStudyView(study.getId());
	}

	@RequestMapping("end/{id}")
	public String endStudy(HttpSession session, @PathVariable int id) {
		Study study = service.getStudyById(id);
		service.endStudy(study);
		setSession(session, study.getStudyName(), "스터디가 종료되었습니다.");
		return REDIRECT_STUDY_INDEX;
	}

	@RequestMapping("start/{id}")
	public String startStudy(HttpSession session, @PathVariable int id) {
		Study study = service.getStudyById(id);
		service.startStudy(study);
		setSession(session, study.getStudyName(), "스터디가 시작되었습니다.");
		return redirectStudyView(id);
	}

	@RequestMapping("join/{id}")
	public String addCurrentMember(HttpSession session, @PathVariable int id) {
		Study study = service.getStudyById(id);
		try {
            service.addCurrentMember(study);
            setSession(session, study.getStudyName(), " 스터디에 참석하셨습니다.");
        } catch (StudyMaximumOverException e){
        	setSession(session, study.getStudyName(), " 스터디 제한 인원을 확인하세요.");
            log.debug("Check study's maximum member count");
        }

		return redirectStudyView(id);
	}

	@RequestMapping("out/{id}")
	public String removeCurrentMember(HttpSession session, @PathVariable int id) {
		Study study = service.getStudyById(id);
		service.removeCurrentMember(study);
		setSession(session, study.getStudyName(), "스터디에 참석을 취소 하셨습니다.");
		return redirectStudyView(id);
	}
	
	@RequestMapping("view/{id}/meetings")
    public String viewMeeting( @PathVariable int id, Model model) {
		model.addAttribute(service.getStudyById(id));
		model.addAttribute(StudyAttrList.IS_ALREADY_JOIN_MEMBER.key(), service.isCurrentUserAlreadyJoinedIn(id));
		model.addAttribute(StudyAttrList.IS_MANAGER_OR_ADMIN.key(), service.isCurrentUserTheStudyManagerOrAdmin(id));
    	return "/study/_meetings";
    }
	    
    @RequestMapping("view/{id}/meetingMembers")
    public String viewMeetingMembers( @PathVariable int id, Model model) {
    	model.addAttribute(service.getStudyById(id));
		model.addAttribute(StudyAttrList.IS_ALREADY_JOIN_MEMBER.key(), service.isCurrentUserAlreadyJoinedIn(id));
		model.addAttribute(StudyAttrList.IS_MANAGER_OR_ADMIN.key(), service.isCurrentUserTheStudyManagerOrAdmin(id));
    	return "/study/_members";
    }
    
    @RequestMapping("view/{id}/updateTabDataCounts")
    @ResponseBody
    public CountInfoDTO updateTabDataCounts( @PathVariable int id) {
    	return new CountInfoDTO(service.getStudyById(id));
    }
    
    @RequestMapping("view/{id}/comments")
    public String viewComments( @PathVariable int id, Model model) {
        Study study = service.getStudyById(id);
    	model.addAttribute(study);
    	return "/study/_comments";
    }

	private void setSession(HttpSession session, String studyName, String msg) {
		session.setAttribute(StudyAttrList.SESSION_FLASH_MSG.key(), studyName + msg);
	}

}
