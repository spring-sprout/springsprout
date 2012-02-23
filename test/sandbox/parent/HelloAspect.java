package sandbox.parent;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 16
 * Time: 오전 9:51:16
 */
@Aspect
public class HelloAspect {

    @Pointcut("execution(* sandbox.parent.Whiteship.getName(..))") void whiteshipGetName(){};
    @Pointcut("execution(* sandbox.parent.SpringSprout.getWhiteship(..))") void springSproutGetWhiteship(){};

    @Before("whiteshipGetName()")
    public void sayHiWhiteship(){
        System.out.println("hi");
    }

    @Before("springSproutGetWhiteship()")
    public void sayHiSpringSprout(){
        System.out.println("hello");
    }

}
