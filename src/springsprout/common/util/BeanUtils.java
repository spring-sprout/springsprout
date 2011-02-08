/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 10. 31
 * Time: 오전 11:18:00
 * enjoy springsprout ! development!
 */
package springsprout.common.util;

import org.apache.commons.beanutils.PropertyUtils;
import springsprout.common.annotation.CustomTransfer;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import static org.springframework.util.ReflectionUtils.invokeMethod;

public class BeanUtils {
	
	private BeanUtils() {}

    public static void beanCopy(Object targetObject, Object sourceObject,boolean isCustomTransfer) {
        checkBeanDTONull(targetObject,sourceObject);
        writeBeanToBean(targetObject, sourceObject);
        if(isCustomTransfer){
            invokeCustomTransfer(targetObject,sourceObject);
        }
    }

    public static void beanCopy(Object targetObject, Object sourceObject) {
        beanCopy(targetObject,sourceObject,true);
    }

    private static void invokeCustomTransfer(Object writeObject, Object readObject) {
        Method[] mds = writeObject.getClass().getDeclaredMethods();
        for(Method md : mds){
            if(md.getAnnotation(CustomTransfer.class) != null){
                invokeMethod(md,writeObject,new Object[]{readObject});
            }
        }
    }

    private static void checkBeanDTONull(Object targetObject, Object sourceObject) {
        if(ValidationUtils.isNull( sourceObject, targetObject)){
            throw new IllegalArgumentException("sourceObject/targetObject is null!");
        }
    }

    private static void writeBeanToBean(Object writeObject, Object readObject) {
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(writeObject);
        String propName = null;
        
        for (PropertyDescriptor pd : pds) {
            propName = pd.getName();
            if (isWriteable(propName, writeObject) && isReadable(propName, readObject)) {
                try {
                    PropertyUtils.setProperty(writeObject, propName, PropertyUtils.getProperty(readObject, propName));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static boolean isReadable(String propName, Object readableObject) {
        return PropertyUtils.isReadable(readableObject, propName);
    }

    private static boolean isWriteable(String propName, Object writeableObject) {
        return PropertyUtils.isWriteable(writeableObject, propName);
    }
}