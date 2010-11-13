package sandbox.propertyPlaceHolder;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 24
 */
@Configuration
public class AppConfig {

    @Bean BeanSP beanSP(){
        return new BeanSP();
    }

//    @Bean PropertyPlaceholderConfigurer databasePropertyPlaceHolder(){
//        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//        configurer.setLocation(new ClassPathResource("./test.database.properties", getClass()));
//        return configurer;
//    }

}
