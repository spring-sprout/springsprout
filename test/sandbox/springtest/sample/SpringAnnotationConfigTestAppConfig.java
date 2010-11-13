package sandbox.springtest.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 15
 * Time: 오후 12:32:58
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class SpringAnnotationConfigTestAppConfig {

    @Bean
    public String name(){
        return "keesun";
    }

}
