/**
 * Created by IntelliJ IDEA.
 * User: isyoon
 * Date: 2010. 7. 31
 * Time: 오전 8:54:06
 * enjoy springsprout ! development!
 */
package springsprout.common.conversion;

import net.sf.json.JSONObject;

public class JsonStringToBean {

    public static <T> T convert(String jsonString, Class<T> bean) {
        Object obj = null;
        try {
            obj = JSONObject.toBean(JSONObject.fromObject(jsonString), bean);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        return (T) obj;
    }
}
