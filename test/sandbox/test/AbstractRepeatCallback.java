package sandbox.test;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * User: Whiteship
 * Date: 11. 1. 6
 * Time: 오전 11:06
 */
public class AbstractRepeatCallback implements RepeatCallback{
    public void beforeIterate(Statement statement, FrameworkMethod method, Object target) {
    }

    public void afterIterate(Statement statement, FrameworkMethod method, Object target) {
    }

    public void beforeTest(int cnt, Statement statement, FrameworkMethod method, Object target) {
    }

    public void afterTest(int cnt, Statement statement, FrameworkMethod method, Object target) {
    }
}
