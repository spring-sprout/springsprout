package sandbox.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 10. 6
 * Time: 오후 7:24:57
 */
public class Generic<T> {

    protected Class<T> classT;
    protected T objectT;

    @SuppressWarnings("unchecked")
    public Generic() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            this.classT = (Class) ((ParameterizedType) type).getRawType();
        } else {
            this.classT = (Class) type;
        }
        
        try {
            this.objectT = classT.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
