package springsprout.modules.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 6
 * Time: 오후 11:10:09
 */
@Controller
@RequestMapping("/statistics/*")
public class StatisticsController {

    @Autowired StatisticsService statisticsService;

    @RequestMapping
    public void index(ModelMap map){
        map.addAttribute("study_tab", "active");
        map.addAttribute("chartData", statisticsService.getStudyAttendanceNumPerMeeting());
    }


    @RequestMapping
    public String member(ModelMap map){
        map.addAttribute("member_tab", "active");
        map.addAttribute("chartData", statisticsService.getMemberNumPerDay());
        return "statistics/index";
    }

}
