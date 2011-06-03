package springsprout.modules.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springsprout.modules.notice.NoticeService;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.meeting.MeetingService;

/**
 * Created by IntelliJ IDEA.
 * User: keesunbaik
 * Date: 11. 5. 17.
 * Time: 오전 8:50
 */
@Controller
@RequestMapping("/m")
public class MobileWebController {

    @Autowired NoticeService noticeService;
    @Autowired StudyService studyService;
    @Autowired MeetingService meetingService;

    @RequestMapping
    public String index(Model model){
        model.addAttribute("studyList", studyService.findActiveStudies(4));
        model.addAttribute("meetingList", meetingService.findActiveMeetings(2));
        model.addAttribute("noticeList", noticeService.findRecentNotice(3));
        return "mobile/index";
    }

}
