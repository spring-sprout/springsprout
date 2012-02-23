package sandbox.dispatcher;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.servlet.SimpleDispatcherServlet;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 12
 * Time: 오전 9:46:42
 */
public class SimpleDispatcherServletTest {

    @Test
    public void modelMapTest() throws Exception {
        MockServletConfig servletConfig = new MockServletConfig(new MockServletContext(), "simple");
        SimpleDispatcherServlet ds = new SimpleDispatcherServlet();
        ds.setContextClass(SimpleWebApplicationContext.class);
        ds.init(servletConfig);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/add");
        MockHttpServletResponse res = new MockHttpServletResponse();
        ds.doService(req, res);
    }


}
