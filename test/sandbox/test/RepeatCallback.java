package sandbox.test;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * User: Whiteship
 * Date: 11. 1. 6
 * Time: 오전 11:02
 */
public interface RepeatCallback {
    void beforeIterate(Statement statement, FrameworkMethod method, Object target);

    void afterIterate(Statement statement, FrameworkMethod method, Object target);

    void beforeTest(int cnt, Statement statement, FrameworkMethod method, Object target);

    void afterTest(int cnt, Statement statement, FrameworkMethod method, Object target);
}
