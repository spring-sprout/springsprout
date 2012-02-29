package springsprout.common.tags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springsprout.common.usebean.ClientScript;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.Serializable;

public class JavaScriptLoadTag extends TagSupport implements Serializable,BodyTag {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private BodyContent bodyContent;
	private ClientScript clientScript;

	public static final String CLIENT_SCRIPT_ATTRIBUTE = "clientScript";

	@Override
	public int doStartTag() throws JspException {
		setClientScript();
		return EVAL_BODY_BUFFERED;
	}

	protected void setClientScript() {
		this.clientScript = (ClientScript)this.pageContext.getServletContext().getAttribute(CLIENT_SCRIPT_ATTRIBUTE);
		if (this.clientScript == null) {
			this.clientScript = new ClientScript();
			this.pageContext.getServletContext().setAttribute(CLIENT_SCRIPT_ATTRIBUTE, this.clientScript);
		}
	}

	public ClientScript getClientScript(){
		return this.clientScript;
	}

	protected void writeScript(String tag) throws JspException{
		try {
			pageContext.getOut().write(String.valueOf(tag));
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}

	@Override
	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

	@Override
	public void doInitBody() throws JspException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public String getBodyContentString(){
		return (bodyContent == null?"":bodyContent.getString());
	}
}