package springsprout.common.convention;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Keesun Baik
 */
public class ConventionTest {

	@Test
	public void homeurl(){
		assertThat(Convention.HOME_URL, is(notNullValue()));
		System.out.println(Convention.HOME_URL);
	}
}
