package sandbox.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 16
 * Time: 오후 5:53:19
 */
@Component
public class Whiteship {

    @Value("keesun")
    private String name;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
