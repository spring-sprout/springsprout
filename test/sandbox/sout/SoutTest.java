package sandbox.sout;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 1. 25
 */
public class SoutTest {
    
    @Test
    @Ignore
    public void sout() throws IOException {
        SoutInterceptor soutInterceptor = new SoutInterceptor();

        soutInterceptor.active();

        Sout sout = new Sout();
        sout.hi();
        assertThat(soutInterceptor.getMessages(), is("hi"));

        sout.hello();
        sout.so();
        assertThat(soutInterceptor.getMessages(), is("hello11"));
    }

}
