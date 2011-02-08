package springsprout.common.propertyeditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import springsprout.common.util.ReflectionUtils;
import springsprout.common.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;

public class GenericFakePropertyEditor<T> extends PropertyEditorSupport {

	protected Log logger = LogFactory.getLog(getClass());

	protected Class<T> entityClass;
	
	public GenericFakePropertyEditor(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsText() {
		T entity = (T) getValue();
		logger.debug("entity = " + entity);
		if (entity == null)
			return "";
		else
			return String.valueOf(ReflectionUtils.callMethod(entityClass, entity, "getId"));
	}

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		logger.debug("text = " + id);
		if (StringUtils.isEmpty( id))
			setValue(null);
		else {
			T entity;
			try {
				entity = entityClass.newInstance();
				Field idFiled = ReflectionUtils.getField(entityClass, "id");
				idFiled.setAccessible(true);
				idFiled.set(entity, Integer.parseInt(id));
				setValue(entity);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
