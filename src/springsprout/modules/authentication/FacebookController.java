package springsprout.modules.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import springsprout.modules.authentication.support.FaceBookReturn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: keesun
 * Date: 11. 6. 22
 * Time: 오후 12:08
 * http://developers.facebook.com/docs/authentication/
 */
@Controller
public class FacebookController {

    @RequestMapping("/fblogin")
    public String success(FaceBookReturn faceBookReturn, BindingResult result, Model model) {
        model.addAttribute("return", faceBookReturn);
        return "fblogin";
    }
}
