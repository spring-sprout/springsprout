package springsprout.common.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 3. 21
 * Time: 오전 10:54:04
 */
public class StringUtilsTest {

    @Test
    public void testCutBytes() throws Exception {
        String result = StringUtils.cutBytes("The emergence of the XML-based Simple Object Access Protocol (SOAP) as a widely accepted, platform-agnostic standard for RMI, and widespread support for web services.", 150);
        assertThat(result.getBytes().length, is(150));

        result = StringUtils.cutBytes("하이버네이트 기능 중 하나로 자동으로 persistent 상태의 객체 변화를 감지하는 기능입니다.\n" +
                "\n" +
                "\"Hibernate monitors all Persistent objects (i.e. the persistent context). At the end of a unit of work, it knows which objects have been modified. It then calls update statements on all updated objects. This process of monitoring and updating only objects that have changed is called automatic dirty checking. As opposed to updating all Persistent objects at the end of each work, automatic dirty checking can offer significant performance savings.\"\n" +
                "- from: http://www.hibernate-training-guide.com/dirty-checking.html", 150);
        assertThat(result.getBytes().length, is(150));

        result = StringUtils.cutBytes("sss", 150);
        assertThat(result.getBytes().length, is(3));
    }
}
