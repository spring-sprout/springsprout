package sandbox.test;

import org.junit.Rule;
import org.junit.Test;

/**
 * User: Whiteship
 * Date: 11. 1. 6
 * Time: 오전 10:45
 */
public class RuleTest {

    @Rule public Repeater repeater = new Repeater(new TimeCheckingRepeatCallback());

    @Test
    public void normal(){
        System.out.println("hehehe");
    }

    @Test
    @Repeat(10)
    public void something() throws InterruptedException {
        System.out.println("hi");
        Thread.sleep(10l);
    }

}
