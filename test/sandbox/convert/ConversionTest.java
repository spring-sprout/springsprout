package sandbox.convert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.ConvertingPropertyEditorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오전 11:31:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("testContext.xml")
public class ConversionTest {

    @Autowired SpringSprout springSprout;

    @Test
    public void whiteship(){
        assertThat(springSprout.getWhiteship().getName(), is("keesun"));
    }


}
