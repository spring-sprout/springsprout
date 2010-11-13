package sandbox.basic;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 5. 8
 * Time: 오후 1:52:36
 */
public class ArrayCastingTest {

    @Test(expected = ArrayStoreException.class)
    public void string2Object() {
        String[] s = {"a", "b"};
        Object[] o = s;
        o[1] = 1;
    }
}
