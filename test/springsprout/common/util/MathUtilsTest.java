package springsprout.common.util;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 9
 * Time: 오후 10:51:20
 */
public class MathUtilsTest {

    @Test
    public void testRateByDivide() throws Exception {
        assertThat(MathUtils.rateByDivide(20, 100), is(20));
        assertThat(MathUtils.rateByDivide(10, 20), is(50));
        assertThat(MathUtils.rateByDivide(20, 20), is(100));
        assertThat(MathUtils.rateByDivide(20, 0), is(0));
        assertThat(MathUtils.rateByDivide(0, 20), is(0));
    }
}
