package sandbox.decimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: keesun
 * Date: 11. 6. 3
 * Time: 오전 9:50
 */
public class DecimalTest {

    @Test
    public void eq() {
        BigDecimal one = new BigDecimal("1");
        BigDecimal onePointZero = new BigDecimal("1.0");
        BigDecimal onePointDoubleZero = new BigDecimal("1.00");

        assertThat(one.equals(onePointZero), is(false));
        assertThat(onePointZero.equals(onePointDoubleZero), is(false));
        assertThat(onePointZero.compareTo(onePointDoubleZero), is(0));
    }

}
