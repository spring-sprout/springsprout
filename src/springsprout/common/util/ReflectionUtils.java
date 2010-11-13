package springsprout.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectionUtils {

	private ReflectionUtils() {}
	
	protected static Log logger = LogFactory
			.getLog(ReflectionUtils.class);

	public static Field getField(Class<?> clazz, String fieldName) {
		return getField(clazz, fieldName, false);
	}

	public static Field getField(Class<?> clazz, String fieldName, Boolean findInSuperClass) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			throw new SecurityException(e);
		} catch (NoSuchFieldException e) {
			if (findInSuperClass){
				field = ReflectionUtils.getField(clazz.getSuperclass(), fieldName, findInSuperClass);
			}
			else{
				throw new IllegalStateException(e);
			}
		}
		return field;
	}

	public static Object getValue(Class<?> clazz, Object object, String fieldName) {
		return getValue(clazz, object, fieldName, false);
	}

	public static Object getValue(Class<?> clazz, Object object, String fieldName, Boolean findInSuperClass) {
		Field field = getField(clazz, fieldName, findInSuperClass);
		field.setAccessible(true);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		return result;
	}


	public static Class<?> getType(Class<?> clazz, String fieldName) {
		Field field = getField(clazz, fieldName);
		return field.getType();
	}

	public static Class<?> getSubType(Class<?> clazz, Object object, String fieldName) {
		Field field = getField(clazz, fieldName);
		field.setAccessible(true);

		Object realObject = null;
		try {
			realObject = field.get(object);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}

		return realObject.getClass();
	}

	public static Object callMethod(Class<?> clazz, Object object,
			String methodName) {
		List<Method> methods = Arrays.asList(clazz.getMethods());
		for (Method method : methods) {
			if (method.getName().equals(methodName))
				try {
					return method.invoke(object, null);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException(e);
				} catch (IllegalAccessException e) {
					throw new IllegalStateException(e);
				} catch (InvocationTargetException e) {
					throw new IllegalStateException(e);
				}
		}
		return null;
	}


}
