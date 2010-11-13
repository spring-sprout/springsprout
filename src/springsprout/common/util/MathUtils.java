package springsprout.common.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 9
 * Time: 오후 10:49:59
 */
public class MathUtils {

	private MathUtils() {}
	
    public static int rateByDivide(int divided, int divisor) {
		if(divisor == 0)
			return 0;
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		BigDecimal rate = new BigDecimal(divided).divide(new BigDecimal(divisor), mc);
		return rate.multiply(new BigDecimal(100)).intValue();
	}
}
