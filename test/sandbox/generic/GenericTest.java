package sandbox.generic;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 10. 6
 * Time: 오후 7:28:42
 */
public class GenericTest {

    @Test
    public void typeInfer(){
        BookGeneric bookGeneric = new BookGeneric();
        assertNotNull(bookGeneric.objectT);
    }

}
