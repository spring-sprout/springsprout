package springsprout.common.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 19
 * Time: 오전 12:29:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class AsyncExceptionTemplateTest {

    @Autowired AsyncExceptionTemplate template;

    @Test
    public void testCatchAll() {
        System.out.println(Thread.currentThread());
        try {
            template.catchAll(new ExceptionalWork() {
                public void run() throws Exception {
                    System.out.println(Thread.currentThread());
                    throw new RuntimeException("걍 에러..");
                }
            });
        } catch (Exception e) {
            fail("에러 나지 않아야 돼");
        }

    }

}
