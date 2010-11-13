package sandbox.asm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.asm.ClassReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2009. 12. 9
 * Time: 오후 1:10:45
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ClassLoadingTest {

    @Autowired
    ApplicationContext ac;

    @Test
    public void asm() throws IOException {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, true);
    }

    @Test
    public void spring() throws IOException {
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource rs = resolver.getResource("sandbox/asm/Whiteship.class");
//        System.out.println(rs.isReadable());
        MetadataReader mr = new SimpleMetadataReaderFactory().getMetadataReader("sandbox.asm.Whiteship");
        assertThat(mr.getClassMetadata().getClassName(), is("sandbox.asm.Whiteship"));
        assertThat(mr.getClassMetadata().getSuperClassName(), is("java.lang.Object"));
    }

    @Test
    public void reflection() throws ClassNotFoundException {
        Class bean = Class.forName("sandbox.asm.Bean");
        assertThat(bean, is(notNullValue()));
    }

}
