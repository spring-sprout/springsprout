package springsprout.modules.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springsprout.modules.notice.NoticeService;
import springsprout.modules.study.StudyService;
import springsprout.modules.study.meeting.MeetingService;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: keesunbaik
 * Date: 11. 5. 17.
 * Time: 오전 8:50
 */
@Controller
public class MobileWebController {

    @Autowired NoticeService noticeService;
    @Resource StudyService advancedStudyService;
    @Autowired MeetingService meetingService;

    @RequestMapping("/m")
    public String index(Model model){
        model.addAttribute("studyList", advancedStudyService.findActiveStudies(4));
        model.addAttribute("meetingList", meetingService.findActiveMeetings(2));
        model.addAttribute("noticeList", noticeService.findRecentNotice(5));
        return "mobile/index";
    }

    @RequestMapping("/mobile/notice/{id}")
    public String noticeView(@PathVariable int id, Model model) {
        model.addAttribute("notice", noticeService.get(id));
        return "mobile/notice/view";
    }

    @RequestMapping("/mobile/study")
    public String studyList(Model model) {
        model.addAttribute("activeStudyList", advancedStudyService.findActiveStudies());
        model.addAttribute("pastStudyList", advancedStudyService.findPastStudies());
        return "mobile/study/list";
    }

    @RequestMapping("/mobile/meeting/{id}")
    public String studyView(@PathVariable int id, Model model) {
        model.addAttribute("meeting", meetingService.getById(id));
        return "mobile/study/meeting/view_home";
    }


}
