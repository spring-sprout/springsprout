package sandbox.propertyPlaceHolder;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 24
 */
public class BeanSP {

    @Value("#{systemProperties['os.name']}")
    String name;

    @Value("${db.username}")
    String username;

}
