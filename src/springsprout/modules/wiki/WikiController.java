package springsprout.modules.wiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 4. 3
 * Time: 오후 10:08:40
 */

@Controller
@RequestMapping("/wiki/*")
public class WikiController {

    @Autowired WikiService wikiService;

    @RequestMapping
    public void index(Model model){
        model.addAttribute("spaceList", wikiService.spaceList());
        model.addAttribute("wikiList", wikiService.wikiFeedList());
        model.addAttribute("minitab_index", "active");
    }

    @RequestMapping
    public String refresh(){
        wikiService.refresh();
        return "redirect:/wiki/index";
    }
    

}
