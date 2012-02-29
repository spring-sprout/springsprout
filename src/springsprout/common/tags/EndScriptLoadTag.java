package springsprout.common.tags;

import springsprout.common.usebean.ClientScript;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/28/12
 * Time: 11:03 PM
 */
public class EndScriptLoadTag extends JavaScriptLoadTag {

	private boolean isOutTag = false;

	public void setIsOutTag(boolean isOutTag){
		this.isOutTag = isOutTag;
	}

	@Override
	public int doEndTag() throws JspException{
		if(isOutTag){
			writeScript(getClientScript().getEndScript());
		}else{
			getClientScript().setEndScript(getBodyContentString());
		}
		return EVAL_PAGE;
	}
}
