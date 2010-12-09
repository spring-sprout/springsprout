package springsprout.modules.study.post.surbeyPost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/study/{id}/post/surbeyPost")
@SessionAttributes({"study"})
public class SurbeyPostController {

}
