package springsprout.common.enumeration.usertype;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import springsprout.common.enumeration.PersistentEnum;
import springsprout.common.enumeration.PersistentEnumUtil;

public class GenericEnumUserType<E extends PersistentEnum> implements UserType {

	@SuppressWarnings("unused")
	private static final String ENUMERTATION_PACKAGE = "springsprout.domain.enumeration";

	protected Class<E> enumClass;
	private String typeName;

	@SuppressWarnings("unchecked")
	public GenericEnumUserType() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
		if (type instanceof ParameterizedType) {
			this.enumClass = (Class) ((ParameterizedType) type).getRawType();
		} else {
			this.enumClass = (Class) type;
		}
	}

	public GenericEnumUserType(Class<E> enumClass) {
		this.enumClass = enumClass;
	}

	public int[] sqlTypes() {
		try {
			Field valueField = enumClass.getDeclaredField("value");
			typeName = valueField.getType().getSimpleName();
			if (typeName.equals("String"))
				return new int[] { Hibernate.STRING.sqlType() };
			if (typeName.equals("Integer") || typeName.equals("int"))
				return new int[] { Hibernate.INTEGER.sqlType() };
			if (typeName.equals("Character") || typeName.equals("char"))
				return new int[] { Hibernate.CHARACTER.sqlType() };
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		return new int[] { Hibernate.INTEGER.sqlType() };
	}

	public Class<?> returnedClass() {
		return enumClass;
	}

	public boolean isMutable() {
		return true;
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		Object value = rs.getObject(names[0]);
		return rs.wasNull() ? null : PersistentEnumUtil.valueOf(enumClass,
				value);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null)
			st.setNull(index, Hibernate.INTEGER.sqlType());
		else {
			try {
				Field valueField = value.getClass().getDeclaredField("value");
				valueField.setAccessible(true);
				Object fieldValue = valueField.get(value);
				if (fieldValue instanceof Integer)
					st.setInt(index, (Integer) fieldValue);
				if (fieldValue instanceof String)
					st.setString(index, (String) fieldValue);
				if (fieldValue instanceof Character)
					st.setString(index, "'" + (Character) fieldValue + "'");
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchFieldException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}

		}
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return null;
	}

}
