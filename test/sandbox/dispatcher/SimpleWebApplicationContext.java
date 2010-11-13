package sandbox.dispatcher;

import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.BeansException;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import springsprout.domain.Member;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 12
 * Time: 오전 10:03:35
 */
public class SimpleWebApplicationContext extends StaticWebApplicationContext {
    public void refresh() throws BeansException {
        registerSingleton("handlerMapping", DefaultAnnotationHandlerMapping.class);
        registerSingleton("testController", TestController.class);
        super.refresh();
    }

    @Controller
    static class TestController {

        @ModelAttribute("ref")
        String ref() {
            return "data";
        }

        @RequestMapping("/add")
        public String add(Member member, Model model) {
            model.addAttribute("msg", "ok");
            return "hi";
        }

    }
}