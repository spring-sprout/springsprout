package sandbox.test;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * User: Whiteship
 * Date: 11. 1. 6
 * Time: 오전 11:07
 */
public class TimeCheckingRepeatCallback implements RepeatCallback{

    private long startTime;
    private long testStartTime;
    private long totalTestDurations = 0l;
    private int count = 0;

    public void beforeIterate(Statement statement, FrameworkMethod method, Object target) {
        startTime = System.currentTimeMillis();
    }

    public void afterIterate(Statement statement, FrameworkMethod method, Object target) {
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Total " + method.getName() + " execution time is [" + duration + "]");
        System.out.println("Average " + method.getName() + " execution time is [" + (totalTestDurations/count) + "]");
    }

    public void beforeTest(int cnt, Statement statement, FrameworkMethod method, Object target) {
        testStartTime = System.currentTimeMillis();
    }

    public void afterTest(int cnt, Statement statement, FrameworkMethod method, Object target) {
        long testDuration = System.currentTimeMillis() - testStartTime;
        totalTestDurations += testDuration;
        count++;
        System.out.println(++cnt +  " time " + method.getName() + " execution time is [" + testDuration + "]");
    }
}
