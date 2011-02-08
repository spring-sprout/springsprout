/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 10. 28
 * Time: 오전 12:25:55
 * enjoy springsprout ! development!
 */
package springsprout.common.web.support;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.is;
import static org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver.UNRESOLVED;
import static springsprout.common.SpringSprout2System.JSON_VIEW;

public class ModelAndJsonViewResolverTest {

    ModelAndJsonViewResolver amvr;
    ModelAndView mav;
    ExtendedModelMap model;
    MockHttpServletRequest ajaxRequest;

    @Before public void setUp() {
        amvr = new ModelAndJsonViewResolver();
        mav = new ModelAndView(JSON_VIEW).addObject("test", "testData");
        model = new ExtendedModelMap().addAttribute("modelMap", "test");
        ajaxRequest = new MockHttpServletRequest();
        ajaxRequest.addHeader("AJAX", "true");
    }

    @Test public void 아작스요청이면서_jsonview으로_리턴시_모델_테스트_암묵적모델제거편() {
        Assert.assertThat(amvr.resolveModelAndView(null, null, mav, model, new ServletWebRequest(ajaxRequest)).getModel().containsKey("test"), is(true));
        Assert.assertThat(amvr.resolveModelAndView(null, null, mav, model, new ServletWebRequest(ajaxRequest)).getModel().containsKey("modelMap"), is(false));
    }

    @Test public void 아작스요청이면서_jsonview으로_리턴시_모델_테스트_암묵적모델추가편(){
        model.addAttribute(ModelAndJsonViewResolver.DEFAULT_CLEAR_KEY, "");
        Assert.assertThat(amvr.resolveModelAndView(null, null, mav, model, new ServletWebRequest(ajaxRequest)).getModel().containsKey("modelMap"), is(true));
        Assert.assertThat(amvr.resolveModelAndView(null, null, mav, model, new ServletWebRequest(ajaxRequest)).getModel().containsKey(ModelAndJsonViewResolver.DEFAULT_CLEAR_KEY), is(false));
    }

    @Test public void 아작스요청이면서_jsonview으로_viewName만_리턴시_테스트(){
        model.addAttribute(ModelAndJsonViewResolver.DEFAULT_CLEAR_KEY, "");           
        Assert.assertThat(amvr.resolveModelAndView(null,null,JSON_VIEW,model, new ServletWebRequest(ajaxRequest)).getModel().containsKey("modelMap"), is(true));
        Assert.assertThat(amvr.resolveModelAndView(null,null,JSON_VIEW,model, new ServletWebRequest(ajaxRequest)).getModel().containsKey(ModelAndJsonViewResolver.DEFAULT_CLEAR_KEY), is(false));
    }

    @Test public void 아작스요청이면서_일반적인_view_리턴시_테스트(){
        Assert.assertThat(amvr.resolveModelAndView(null, null, "main/index", model, new ServletWebRequest(ajaxRequest)), is(UNRESOLVED));
    }
}
