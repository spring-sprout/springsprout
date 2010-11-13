package sandbox.convert;

import java.beans.PropertyEditorSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오전 11:34:27
 */
public class WhiteshipPE extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        System.out.println("PE getAsText");
        return ((Whiteship)getValue()).getName();
    }

    @Override
    public void setAsText(String name) throws IllegalArgumentException {
        System.out.println("PE setAsText");
        setValue(new Whiteship(name));
    }
}
