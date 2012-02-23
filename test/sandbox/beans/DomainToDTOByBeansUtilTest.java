package sandbox.beans;

import com.mchange.v2.beans.BeansUtils;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 10. 31
 * Time: 오후 12:20:46
 */
public class DomainToDTOByBeansUtilTest {

    @Test
    public void domainToDTO() throws IntrospectionException {
        Domain domain = new Domain();
        domain.setAge(10);
        domain.setName("keesun");

        Domain dto = new Domain();

        BeansUtils.overwriteAccessibleProperties(domain, dto, Arrays.asList(new String[]{"age"}));

        assertThat(dto.getName(), is(domain.getName()));
        assertThat(dto.getAge(), is(0));
    }

}
