package sandbox.spel;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 2. 10
 * Time: 오후 6:38:11
 */
public class SpELNullTest {

    @Test
    public void SafeNavigation(){
        ExpressionParser parser = new SpelExpressionParser();

        SpringSprout ss = new SpringSprout();
        StandardEvaluationContext context = new StandardEvaluationContext(ss);

        String myName = parser.parseExpression("whiteship?.name").getValue(context, String.class);
        assertThat(myName, is(nullValue()));
    }

    class Whiteship{
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class SpringSprout{
        Whiteship whiteship;

        public Whiteship getWhiteship() {
            return whiteship;
        }

        public void setWhiteship(Whiteship whiteship) {
            this.whiteship = whiteship;
        }
    }

}
