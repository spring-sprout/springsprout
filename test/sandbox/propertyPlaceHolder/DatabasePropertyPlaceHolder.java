package sandbox.propertyPlaceHolder;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 24
 */

@Component
public class DatabasePropertyPlaceHolder extends PropertyPlaceholderConfigurer {

    public DatabasePropertyPlaceHolder() {
        this.setLocation(new ClassPathResource("/test.database.properties", getClass()));
    }

}
