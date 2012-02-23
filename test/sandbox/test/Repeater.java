package sandbox.test;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * User: Whiteship
 * Date: 11. 1. 6
 * Time: 오전 10:51
 */
public class Repeater implements MethodRule {

    private RepeatCallback callback;

    public Repeater(RepeatCallback callback) {
        this.callback = callback;
    }

    public Repeater() {
    }

    public Statement apply(final Statement statement, final FrameworkMethod method, final Object target) {
        return new Statement() {
            public void evaluate() throws Throwable {
                Repeat repeat = method.getAnnotation(Repeat.class);
                if(repeat == null) {
                    statement.evaluate();
                } else {
                    int count = repeat.value();
                    callback.beforeIterate(statement, method, target);
                    for(int i = 0 ; i < count ; i++){
                        callback.beforeTest(i, statement, method, target);
                        statement.evaluate();
                        callback.afterTest(i, statement, method, target);
                    }
                    callback.afterIterate(statement, method, target);
                }
            }
        };
    }
}
