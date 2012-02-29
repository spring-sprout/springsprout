package springsprout.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/29/12
 * Time: 2:19 AM
 */
public class ReadyScriptLoadTag extends JavaScriptLoadTag {
	private boolean isOutTag = false;

	public void setIsOutTag(boolean isOutTag){
		this.isOutTag = isOutTag;
	}

	@Override
	public int doEndTag() throws JspException {
		if(isOutTag){
			writeScript(getClientScript().getReadyScript());
		}else{
			getClientScript().setReadyScript(getBodyContentString());
		}
		return EVAL_PAGE;
	}
}
