package springsprout.service.security;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 9. 17
 * Time: 오전 11:10:11
 */
public class PopupLoginSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        String s = request.getParameter("_to");
        System.out.println(s);
        if(s != null)
            return s;
        else
            return super.determineTargetUrl(request, response);
    }
}
