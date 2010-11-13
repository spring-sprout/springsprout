package springsprout.common.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import springsprout.common.enumeration.PersistentEnum;
import springsprout.common.enumeration.PersistentEnumUtil;
import springsprout.common.util.StringUtils;

public class GenericEnumPropertyEditor<E extends PersistentEnum> extends
		PropertyEditorSupport {

	private Class<E> enumClass;

	private String typeName;

	@SuppressWarnings("unchecked")
	public GenericEnumPropertyEditor() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
		if (type instanceof ParameterizedType) {
			this.enumClass = (Class) ((ParameterizedType) type).getRawType();
		} else {
			this.enumClass = (Class) type;
		}

		Field value = null;
		try {
			value = enumClass.getDeclaredField("value");
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		if (value != null)
			typeName = value.getType().getSimpleName();
		else
			throw new IllegalArgumentException(
					"persistent enum needs 'value' field.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsText() {
		E e = (E) this.getValue();
		if (e == null)
			return "";
		try {
			Field value = e.getClass().getDeclaredField("value");
			value.setAccessible(true);
			return value.get(e).toString();
		} catch (SecurityException e1) {
			throw new GenericEnumPropertyException(e1);
		} catch (NoSuchFieldException e1) {
			throw new GenericEnumPropertyException(e1);
		} catch (IllegalArgumentException e1) {
			throw new GenericEnumPropertyException(e1);
		} catch (IllegalAccessException e1) {
			throw new GenericEnumPropertyException(e1);
		}
	}

	@Override
	public void setAsText(String value) throws IllegalArgumentException {
		if (StringUtils.isEmpty(value))
			setValue(null);
		else {
			if (typeName.equals("String"))
				setValue(PersistentEnumUtil.valueOf(enumClass, value));
			if (typeName.equals("Integer") || typeName.equals("int"))
				setValue(PersistentEnumUtil.valueOf(enumClass, new Integer(
						Integer.parseInt(value))));
			if (typeName.equals("Character") || typeName.equals("char"))
				setValue(PersistentEnumUtil.valueOf(enumClass, new Character(
						value.charAt(0))));
		}
	}
	
}
