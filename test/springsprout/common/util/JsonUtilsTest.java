/**
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 10. 28
 * Time: 오전 11:12:47
 * enjoy springsprout ! development!
 */
package springsprout.common.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import springsprout.common.SpringSprout2System;

import static org.hamcrest.Matchers.is;

public class JsonUtilsTest {
    @Test
    public void testCleanModel() {
        ExtendedModelMap model = new ExtendedModelMap().addAttribute("modelMap","test");
        JsonUtils.cleanModel(model);
        Assert.assertThat(model.asMap().containsKey("modelMap"), is(false));
        Assert.assertThat(model.asMap().containsKey(SpringSprout2System.CLEAN_KEY), is(true));
    }
}
