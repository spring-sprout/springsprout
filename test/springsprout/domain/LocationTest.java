package springsprout.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: nhn
 * Date: 10. 12. 21
 * Time: 오후 6:11
 */
public class LocationTest {
    @Test
    public void testToString() throws Exception {
        Location location = new Location();
        assertThat(location.toString(), is("미정"));

        location.setName("강남");
        assertThat(location.toString(), is("강남"));
    }
}
