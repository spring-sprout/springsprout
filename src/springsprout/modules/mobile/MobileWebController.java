package springsprout.modules.mobile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: keesunbaik
 * Date: 11. 5. 17.
 * Time: 오전 8:50
 */
@Controller
@RequestMapping("/m")
public class MobileWebController {

    @RequestMapping
    public String index(){
        return "mobile/index";
    }

}
