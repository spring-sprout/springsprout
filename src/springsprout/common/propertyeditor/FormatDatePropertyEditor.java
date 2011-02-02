package springsprout.common.propertyeditor;

import springsprout.common.util.DateUtils;
import springsprout.common.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FormatDatePropertyEditor extends PropertyEditorSupport {
	DateFormat format;

	public FormatDatePropertyEditor() {
		format = new SimpleDateFormat(DateUtils.yyyyMMdd_SLASH);
	}

	public FormatDatePropertyEditor(String format) {
		this.format = new SimpleDateFormat(format);
	}

	@Override
	public String getAsText() {
		Object val = getValue();
		if (val == null) return "";
		return format.format( val);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
		} else {
			try {
				setValue(format.parse(text));
			} catch (ParseException e) {
				throw new IllegalArgumentException("Invalid date string " + text, e);
			}
		}
	}

	public void setPattern(String pattern) {
		this.format = new SimpleDateFormat(pattern);
	}

	public DateFormat getFormat() {
		return format;
	}

	public void setFormat(DateFormat format) {
		this.format = format;
	}

}
