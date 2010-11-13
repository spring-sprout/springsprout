/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 10. 27
 * Time: 오후 11:50:14
 * enjoy springsprout ! development!
 */
package springsprout.common.web.support;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import java.lang.reflect.Method;

public class ModelAndJsonViewResolver implements ModelAndViewResolver {
    public static final String DEFAULT_JSONVIEW_NAME = "mappingJacksonJsonView";
    public static final String DEFAULT_CLEAR_KEY = "__CLEAR__";
    public static final String DEFAULT_AJAX_HEADER_NAME = "AJAX";

    private String jsonViewName = DEFAULT_JSONVIEW_NAME;

    private String clearKey = DEFAULT_CLEAR_KEY;

    private String ajaxHeaderName = DEFAULT_AJAX_HEADER_NAME;

    public void setAjaxHeaderName(String ajaxHeaderName) {
        this.ajaxHeaderName = ajaxHeaderName;
    }

    public void setDefaultJsonViewName(String jsonViewName){
        this.jsonViewName = jsonViewName;
    }

    public void setClearKey(String clearKey) {
        this.clearKey = clearKey;
    }

    public String getJsonViewName() {
        return jsonViewName;
    }

    public String getClearKey() {
        return clearKey;
    }

    /**
     * 스프링 3.0 부터 제공된 ModelAndViewResolver의 기본 구현 메소드.
     * 역할 : ajax 요청이고 리턴하는 viewn가 jsonViewName 이면 implicitModel의 clearkey의 존재 여부에 따라
     * ModelAndView 의 model에 add해주는 기능을 함.
     * @param handlerMethod
     * @param handlerType
     * @param returnValue
     * @param implicitModel
     * @param webRequest
     * @return ModelAndView
     */
    @SuppressWarnings("unchecked")
    public ModelAndView resolveModelAndView(Method handlerMethod, Class handlerType, Object returnValue, ExtendedModelMap implicitModel, NativeWebRequest webRequest) {        
        if(isAjaxRequest(webRequest)){
			if (isModelAndViewObject(returnValue) && isJsonViewName(returnValue)) {
                return  mergeClearModel(implicitModel, (ModelAndView) returnValue);
			}
            else if(isStringObject(returnValue) && isJsonViewName(returnValue)){
                return  mergeClearModel(implicitModel, new ModelAndView((String) returnValue));
            }
        }
        return UNRESOLVED;
    }

    private boolean isModelAndViewObject(Object returnValue) {
        return returnValue instanceof ModelAndView;
    }

    private boolean isStringObject(Object returnValue) {
        return returnValue instanceof String;
    }

    private boolean isJsonViewName(Object returnValue) {
        String viewName = "";
        
        if(isModelAndViewObject(returnValue)){
            viewName = ((ModelAndView) returnValue).getViewName();
        }else if(isStringObject(returnValue)){
            viewName = (String)returnValue;
        }

        return jsonViewName.equals(viewName);
    }

    private boolean isAjaxRequest(NativeWebRequest webRequest) {
        return webRequest.getHeader(ajaxHeaderName) != null;
    }

    private ModelAndView mergeClearModel(ExtendedModelMap implicitModel, ModelAndView mav) {
        if(implicitModel.containsKey(clearKey)){
            mav.addAllObjects(implicitModel).getModel().remove(clearKey);
        }
        return mav;
    }
}
