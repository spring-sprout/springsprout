package sandbox.provider;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 1. 12
 * Time: 오후 9:56:25
 */
@Configuration
public class ProviderTestAppConfig {

    @Bean White getWhite(){
        return new White();
    }

    @Bean @Scope("prototype") Ship getShip(){
        return new Ship();
    }
    
}
