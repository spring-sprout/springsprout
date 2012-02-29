package springsprout.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/29/12
 * Time: 2:18 AM
 */
public class HeadScriptLoadTag extends JavaScriptLoadTag {

	private boolean isOutTag = false;

	public void setIsOutTag(boolean isOutTag){
		this.isOutTag = isOutTag;
	}

	@Override
	public int doEndTag() throws JspException {
		if(isOutTag){
			writeScript(getClientScript().getHeadScript());
		}else{
			getClientScript().setHeadScript(getBodyContentString());
		}
		return EVAL_PAGE;
	}
}
