package sandbox.validate;

import org.hibernate.validator.constraints.impl.EmailValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 30
 * Time: 오전 12:50:24
 */
public class EmailValidatorTest {

    EmailValidator validator = new EmailValidator();

    @Test
    public void isValid(){
        String email = "whiteship2000@gmail.com";
        assertTrue(validator.isValid(email, null));
        email = "sdfkljsdlkfjlf";
        assertFalse(validator.isValid(email, null));
    }

}
