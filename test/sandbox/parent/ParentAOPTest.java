package sandbox.parent;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 3. 16
 * Time: 오전 9:40:43
 */
public class ParentAOPTest {

    @Test
    public void appParentAop(){
        ApplicationContext parentAC = new ClassPathXmlApplicationContext("/sandbox/parent/parent.xml");
        Whiteship pWhiteship = parentAC.getBean(Whiteship.class);

        ApplicationContext childAC = new ClassPathXmlApplicationContext(new String[]{"/sandbox/parent/child.xml"}, parentAC);
        Whiteship whiteship = childAC.getBean(Whiteship.class);
        SpringSprout springSprout = childAC.getBean(SpringSprout.class);

        whiteship.getName();
        pWhiteship.getName();
        springSprout.getWhiteship();

    }

}
