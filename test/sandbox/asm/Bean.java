package sandbox.asm;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 9
 * Time: 오후 1:12:51
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(value = "prototype")
public class Bean {

    static {
        System.out.println("I am loaded!");
    }
}
