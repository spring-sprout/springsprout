/**
 * Created by IntelliJ IDEA.
 * User: isyoon
 * Date: 2010. 7. 31
 * Time: 오전 8:55:51
 * enjoy springsprout ! development!
 */
package springsprout.common.conversion;

import org.junit.*;
import springsprout.domain.Role;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JsonStringToBeanTest {

    @Test
    public void JsonStringToBeanTest(){
        assertThat(new JsonStringToBean().convert("{\"name\":\"MEMBER\",\"descr\":\"사용자 권한1\",\"id\":2}",Role.class).getName(), is("MEMBER"));
    }
}