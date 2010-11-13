package sandbox.springtest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import sandbox.springtest.sample.SpringAnnotationConfigTest;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 15
 * Time: 오후 5:28:46
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationContextLoaderTest {

    AnnotationContextLoader acl = new AnnotationContextLoader();

    @Test
    public void generateDefaultLocations(){
        String[] result = acl.generateDefaultLocations(SpringAnnotationConfigTest.class);
        assertThat(result[0], is("sandbox.springtest.sample.SpringAnnotationConfigTestAppConfig.java"));

    }

    @Test
    public void modifyLocations(){
        // absolute location
        String[] result = acl.modifyLocations(SpringAnnotationConfigTest.class, "/sandbox/springtest/sample/SpringAnnotationConfigTestAppConfig.java");
        assertThat(result[0], is("sandbox.springtest.sample.SpringAnnotationConfigTestAppConfig.java"));
        result = acl.modifyLocations(SpringAnnotationConfigTest.class, "/sandbox/springtest/sample/");
        assertThat(result[0], is("sandbox.springtest.sample"));
        result = acl.modifyLocations(SpringAnnotationConfigTest.class, "/sandbox/springtest/sample");
        assertThat(result[0], is("sandbox.springtest.sample"));
        // relative location
        result = acl.modifyLocations(SpringAnnotationConfigTest.class, "./SpringAnnotationConfigTestAppConfig.java");
        assertThat(result[0], is("sandbox.springtest.sample.SpringAnnotationConfigTestAppConfig.java"));
        result = acl.modifyLocations(SpringAnnotationConfigTest.class, "./");
        assertThat(result[0], is("sandbox.springtest.sample"));
        result = acl.modifyLocations(SpringAnnotationConfigTest.class, ".");
        assertThat(result[0], is("sandbox.springtest.sample"));
    }

}
