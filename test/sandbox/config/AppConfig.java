package sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 11
 * Time: 오후 7:47:44
 */
@Configuration
public class AppConfig {

    @Bean
    public SampleBean sampleBean(){
        return new SampleBean();
    }
}
