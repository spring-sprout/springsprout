package sandbox.asm;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
