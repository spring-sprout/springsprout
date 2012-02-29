package springsprout.common.tags;

import javax.servlet.jsp.JspException;

/**
 * Created by IntelliJ IDEA.
 * User: ImYoon
 * Date: 2/29/12
 * Time: 2:46 AM
 */
public class EndScriptFileLoadTag extends JavaScriptLoadTag{
	private String src;
	private boolean isOutTag = false;

	public void setIsOutTag(boolean isOutTag){
		this.isOutTag = isOutTag;
	}

	public void setSrc(String src){
		this.src = src;
	}

	@Override
	public int doEndTag() throws JspException {
		if(isOutTag){
			writeScript(getClientScript().getEndScriptFile());
		}else{
			getClientScript().setRegisterScriptFileAtEnd((this.src == null?getBodyContentString():this.src));
		}
		return EVAL_PAGE;
	}
}
