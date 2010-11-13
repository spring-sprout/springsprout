package sandbox.md5;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.Random;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 10
 * Time: 오후 1:28:42
 * To change this template use File | Settings | File Templates.
 */
public class Md5HashUtilsTest {

    @Test
    public void md5HashUtils() throws UnsupportedEncodingException {
//        System.out.println(Md5HashUtils.getHashString(String.valueOf(new Random().nextInt(1000000)).getBytes("UTF-8")));
        System.out.println(DigestUtils.md5DigestAsHex(String.valueOf(new Random().nextInt(1000000)).getBytes("UTF-8")));
    }
}
