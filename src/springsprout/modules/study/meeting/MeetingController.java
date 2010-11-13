package springsprout.modules.study.meeting;

import static springsprout.common.SpringSprout2System.JSON_VIEW;
import static springsprout.modules.study.support.StudyURLRedirectionUtils.redirectMeetingView;
import static springsprout.modules.study.support.StudyURLRedirectionUtils.redirectStudyView;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import springsprout.common.enumeration.PersistentEnumUtil;
import springsprout.common.propertyeditor.FormatDatePropertyEditor;
import springsprout.common.util.BeanUtils;
import springsprout.domain.Attendance;
import springsprout.domain.Comment;
import springsprout.domain.Meeting;
import springsprout.domain.Member;
import springsprout.domain.Presentation;
import springsprout.domain.Resource;
import springsprout.domain.Study;
import springsprout.domain.enumeration.ResourceType;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.exception.JoinMeetingException;
import springsprout.modules.study.exception.MeetingMaximumOverException;
import springsprout.modules.study.meeting.support.CommentDTO;
import springsprout.modules.study.meeting.support.CountInfoDTO;
import springsprout.modules.study.meeting.support.MeetingValidator;
import springsprout.service.security.SecurityService;

@Controller
@SessionAttributes("meeting, presentation")
public class MeetingController {

    private static final String MEETING_FORM = "study/meeting/form";
    private static final String MEETING_VIEW = "study/meeting/view";


    Logger log = LoggerFactory.getLogger(MeetingController.class);

    @Autowired StudyService studyService;
    @Autowired MeetingService meetingService;
    @Autowired SecurityService securityService;

    @Autowired MeetingValidator meetingValidator;

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}", method = RequestMethod.GET)
    public String meetingView(@PathVariable int meetingId, Model model) {
        Meeting meeting = meetingService.getById(meetingId);
        model.addAttribute(meeting);
        model.addAttribute("attendances", meeting.getSortedAttendances());
        model.addAttribute(new Comment());
        model.addAttribute("isAlreadyJoinMember", isAlreadyJoinMember(meeting));
        model.addAttribute("isManagerOrAdmin", securityService
                .isMeetingManagerOrAdmin(meeting));
        return MEETING_VIEW;
    }

    @RequestMapping(value = "/study/{studyId}/meeting/notify/{meetingId}", method = RequestMethod.GET)
    public String meetingNotify(@PathVariable int studyId, @PathVariable int meetingId, Model model) {
        meetingService.notify(meetingId);
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/comment/add", method = RequestMethod.POST)
    public ModelAndView submitComment(Model model, @Valid Comment comment, @PathVariable int studyId, @PathVariable int meetingId) {
        Meeting meeting = meetingService.getById(meetingId);
        model.addAttribute("meeting", meeting);
        
        meetingService.addComment(meeting, comment);
        meetingService.notifyCommentAdded(meeting, comment);
        
        CommentDTO commentDto = new CommentDTO();
        BeanUtils.beanCopy(commentDto,comment,true);
        return new ModelAndView(JSON_VIEW).addObject( "commentDto", commentDto).addObject("title", meeting.getTitle());
    }

    @RequestMapping(value = "/study/{studyId}/meeting/{meetingId}/comment/{commentId}/delete")
    public String commentDelete(@PathVariable int studyId, @PathVariable int meetingId, @PathVariable int commentId){
        meetingService.deleteComment(meetingId, commentId);
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping("/study/{studyId}/meeting/delete/{meetingId}")
    public String deleteMeeting(@PathVariable int studyId, @PathVariable int meetingId, HttpSession session) {
    	Meeting meeting = meetingService.getById(meetingId);
    	session.setAttribute("SESSION_FLASH_MSG", meeting.getTitle() + " 모임이 삭제되었습니다.");
        meetingService.deleteMeeting( meeting);
        return redirectStudyView(studyId);
    }

    @RequestMapping(value = "/study/{studyId}/meeting/end/{meetingId}")
    public String endMeeting(@PathVariable int studyId, @PathVariable int meetingId, HttpSession session) {
        Meeting meeting = meetingService.getById(meetingId);
        meetingService.endMeeting(meeting);
        session.setAttribute("SESSION_FLASH_MSG", meeting.getTitle()
                + " 모임이 종료되었습니다.");
        return redirectMeetingView(studyId, meetingId);
    }

    private boolean isAlreadyJoinMember(Meeting meeting) {
        Member currentMember = securityService.getCurrentMember();
        return meeting.getAttendanceByMember(currentMember) != null;
    }

    @RequestMapping(value = "/study/{studyId}/meeting/open/{meetingId}")
    public String openMeeting(@PathVariable int studyId,
                              @PathVariable int meetingId, HttpSession session) {
        Meeting meeting = meetingService.getById(meetingId);
        meetingService.openMeeting(meeting);
        session.setAttribute("SESSION_FLASH_MSG", meeting.getTitle()
                + " 모임이 개설되었습니다.");
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping(value = "/study/{studyId}/meeting/update/{meetingId}", method = RequestMethod.GET)
    public String updateMeetingForm(@PathVariable int studyId, @PathVariable int meetingId, Model model) {
    	Study study = studyService.getStudyById(studyId);
        model.addAttribute(meetingService.getById(meetingId));
        model.addAttribute(study);
        model.addAttribute("meetingId", meetingId);
        model.addAttribute("studyName", study.getStudyName());
        model.addAttribute("action", "수정");
        return MEETING_FORM;
    }
    
    @RequestMapping(value = "/study/{studyId}/meeting/update/{meetingId}", method = RequestMethod.POST)
    public String updateMeetingForm(boolean isGoingToBeNotified, @Valid Meeting meeting, BindingResult result,
                                    SessionStatus status, @PathVariable int studyId,
                                    @PathVariable int meetingId, Model model) {
        if (result.hasErrors()) {
        	Study study = studyService.getStudyById(studyId);
            model.addAttribute(study);
            model.addAttribute("studyName", study.getStudyName());
            model.addAttribute("meetingId", meetingId);
            model.addAttribute("action", "수정");
            return MEETING_FORM;
        }
        meetingService.updateMeeting(studyId, meetingId, meeting, isGoingToBeNotified);
        status.setComplete();
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping("/study/{studyId}/meeting/join/{meetingId}")
    public String joinMeeting(@PathVariable int studyId,
                              @PathVariable int meetingId, HttpSession session) {
        Meeting meeting = meetingService.getById(meetingId);
        session.setAttribute("SESSION_FLASH_MSG", meeting.getTitle()
                + " 모임에 참석 신청하였습니다.");
        try {
            meetingService.joinMeetingMember(meeting);
        } catch (MeetingMaximumOverException e) {
            session.setAttribute("SESSION_FLASH_MSG", meeting.getTitle()
                    + " 모임 제한 인원을 확인하세요.");
        } catch (JoinMeetingException e) {
            session.setAttribute("SESSION_FLASH_MSG", meeting.getStudy()
                    .getStudyName()
                    + " 스터디에 참가 신청을 하세요.");
        }
        return redirectMeetingView(studyId, meetingId);
    }

    @RequestMapping("/study/{studyId}/meeting/out/{meetingId}")
    public String leaveMeeting(@PathVariable int studyId,
                               @PathVariable int meetingId, HttpSession session) {
        Meeting meeting = meetingService.getById(meetingId);
        meetingService.leaveMeetingMember(meeting);
        session.setAttribute("SESSION_FLASH_MSG", meeting.getTitle()
                + "모임에 참석 신청을 취소하였습니다.");
        return redirectMeetingView(studyId, meetingId);
    }

    /**
     * 모임 참석 확인
     * @param attendanceId
     * @return
     */
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/confirm/{attendanceId}")
    public ModelAndView confirmMember(@PathVariable int attendanceId) {
        Attendance attendance = meetingService.confirmAttendanceById(attendanceId);
        return new ModelAndView(JSON_VIEW).addObject("notifyMsg", attendance.getMember().getName()
                + "님 모임에 참석 확인하였습니다.");
    }

    @RequestMapping("/study/{studyId}/meeting/{meetingId}/reject/{attendanceId}")
    public ModelAndView rejectMember(@PathVariable int attendanceId) {
    	Attendance attendance = meetingService.rejectAttendanceById(attendanceId);
    	return new ModelAndView(JSON_VIEW).addObject("notifyMsg", attendance.getMember().getName() 
        		+ "님 모임에 참석 취소하였습니다.");
    }
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/meetingLocation")
    public String meetingLocation(@PathVariable int meetingId,Model model) {
        model.addAttribute(meetingService.getById(meetingId));
        return "/study/meeting/_meetingLocationView";
    }
    
    @RequestMapping("/study/{studyId}/meeting/meetings")
    public String viewMeeting( Model model) {
    	return "/study/meeting/_meetings";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/viewMembers")
    public String viewMembers( @PathVariable int meetingId, Model model) {
    	 Meeting meeting = meetingService.getById(meetingId);
         model.addAttribute(meeting);
         model.addAttribute("attendances", meeting.getSortedAttendances());
         model.addAttribute("isAlreadyJoinMember", isAlreadyJoinMember(meeting));
         model.addAttribute("isManagerOrAdmin", securityService.isMeetingManagerOrAdmin(meeting));
    	return "/study/meeting/_members";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/viewPresentations")
    public String viewPresentation( @PathVariable int meetingId, Model model) {
    	 Meeting meeting = meetingService.getById(meetingId);
    	 model.addAttribute(meeting);
         model.addAttribute("presentations", meetingService.getPresentationListByMeetingIdWithSort(meetingId));
         model.addAttribute("isManagerOrAdmin", securityService.isMeetingManagerOrAdmin(meeting));
    	return "/study/meeting/_presentations";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/viewResources")
    public String viewResources( @PathVariable int meetingId, Model model) {
    	model.addAttribute(new Resource());
        model.addAttribute("resourceTypes", PersistentEnumUtil.sortedListOf(ResourceType.class));
    	model.addAttribute("targetObj", meetingService.getById(meetingId));
    	return "/study/meeting/_resources";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/viewComments")
    public String viewComments( @PathVariable int meetingId, Model model) {
    	Meeting meeting = meetingService.getById(meetingId);
        model.addAttribute("commentObj", meeting);
        model.addAttribute(new Comment());
    	return "/study/meeting/_comments";
    }
    
    @RequestMapping("/study/{studyId}/meeting/{meetingId}/updateTabDataCounts")
    @ResponseBody
    public CountInfoDTO updateTabDataCounts( @PathVariable int meetingId) {
    	return new CountInfoDTO(meetingService.getById(meetingId));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(Date.class, "time", new FormatDatePropertyEditor("HH:mm"));
        binder.registerCustomEditor(Date.class, "openTime", new FormatDatePropertyEditor("HH:mm"));
        binder.registerCustomEditor(Date.class, "closeTime", new FormatDatePropertyEditor("HH:mm"));
		binder.registerCustomEditor(Date.class, "openDate", new FormatDatePropertyEditor("yyyy/MM/dd"));	
		binder.registerCustomEditor(Date.class, "closeDate", new FormatDatePropertyEditor("yyyy/MM/dd"));
    }

}
