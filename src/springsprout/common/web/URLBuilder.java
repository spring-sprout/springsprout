package springsprout.common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springsprout.common.SpringSprout2System;
import springsprout.common.util.ValidationUtils;
import springsprout.common.web.exception.EncodingException;


public class URLBuilder {

	private StringBuilder builder;
	String encoding;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public URLBuilder() {
		builder = new StringBuilder();
		this.encoding = SpringSprout2System.ENCODING;
	}

	public String getEncoding(){
		return this.encoding;
	}
	public void setEncoding(String encoding){
		this.encoding = encoding;
	}

	public void addParameter(String parameterName, Object value, String defaultValue) {
		StringBuilder localBuilder = makeForehead(parameterName);

		if( ValidationUtils.isNotNull(value))
			localBuilder.append(encode(String.valueOf(value)));
		else if(ValidationUtils.isNotNull(defaultValue))
			localBuilder.append(encode(defaultValue));
		else
			return;
		builder.append(localBuilder.toString());
	}

	private String encode(String string)  {
		try {
			return URLEncoder.encode(string, encoding);
		} catch (UnsupportedEncodingException e) {
			if(logger.isDebugEnabled()){
				logger.debug("[SpringSprout]encoding field's value is: <" + encoding + ">");
			}
			throw new EncodingException();
		}
	}

	private StringBuilder makeForehead(String parameterName) {
		StringBuilder localBuilder = new StringBuilder();

		if(builder.length() > 0)
			localBuilder.append("&");

		localBuilder.append(parameterName);
		localBuilder.append("=");
		return localBuilder;
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}
