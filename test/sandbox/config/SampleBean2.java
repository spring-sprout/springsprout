package sandbox.config;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 12. 11
 * Time: 오후 8:19:11
 */
@Component
public class SampleBean2 {

    @Bean
    public SampleBean sampleBean(){
        return new SampleBean();
    }

}
