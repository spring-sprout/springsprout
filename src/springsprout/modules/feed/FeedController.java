package springsprout.modules.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import springsprout.modules.feed.support.NoticesAtomView;
import springsprout.modules.feed.support.NoticesRssView;
import springsprout.modules.notice.NoticeService;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 27
 * Time: 오후 10:25:47
 */
@Controller
public class FeedController {

    @Autowired NoticeService noticeService;
    @Autowired NoticesAtomView noticesAtomView;
    @Autowired NoticesRssView noticesRssView;

    @RequestMapping("/notices.atom")
    public View atom(Model model){
        model.addAttribute("notices", noticeService.getAll());
        return noticesAtomView;
    }

    @RequestMapping("/notices.rss")
    public View rss(Model model){
        model.addAttribute("notices", noticeService.getAll());
        return noticesRssView;
    }

}
