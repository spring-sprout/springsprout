package springsprout.common.propertyeditor;

import org.springframework.core.NestedRuntimeException;

public class GenericEnumPropertyException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericEnumPropertyException(String msg) {
		super(msg);
	}

	public GenericEnumPropertyException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public GenericEnumPropertyException(Throwable ex) {
		this("GenericEnumPropertyException", ex);
	}
	
}