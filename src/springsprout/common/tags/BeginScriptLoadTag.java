package springsprout.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/28/12
 * Time: 02:17 PM
 */
public class BeginScriptLoadTag extends JavaScriptLoadTag implements BodyTag{

	private boolean isOutTag = false;

	public void setIsOutTag(boolean isOutTag){
		this.isOutTag = isOutTag;
	}

	@Override
	public int doEndTag() throws JspException{
		if(isOutTag){
			writeScript(getClientScript().getBeginScript());
		}else{
			getClientScript().setBeginScript(getBodyContentString());
		}
		return EVAL_PAGE;
	}
}
