package springsprout.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import springsprout.common.SpringSprout2System;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 1. 18
 * Time: 오후 11:49:42
 */
public class ModelAndViewUtils {
	
	private ModelAndViewUtils() {}
	
	public static final String ATTR_NAME_ERRMESSAGE = "errMessge";
	public static final String ATTR_NAME_RESULT = "result";
	
	public static final String ATTR_VALUE_SUCCESS = "success";
	
	
    public static ModelAndView jsonWithError(BindingResult result) {
        return new ModelAndView(SpringSprout2System.JSON_VIEW).addObject(ATTR_NAME_ERRMESSAGE, result.getAllErrors().get(0).getDefaultMessage());
    }

    public static ModelAndView jsonWithSuccessResult() {
        return new ModelAndView(SpringSprout2System.JSON_VIEW).addObject(ATTR_NAME_RESULT, ATTR_VALUE_SUCCESS);
    }
}
