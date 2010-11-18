package springsprout.common.exception;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship2000
 * Date: 2010. 11. 18
 * Time: 오후 11:22:34
 */
public class ExceptionTemplateTest {

    @Test
    public void doSomethingInTryCatchFinally() {
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
        try {
            exceptionTemplate.catchAll(new ExceptionalWork() {
                public void run() throws Exception {
                    throw new RuntimeException("걍 에러..");
                }
            });
        } catch (Exception e) {
            fail("에러 나지 않아야 돼");
        }
    }

}
