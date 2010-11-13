package sandbox.convert;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오전 11:50:01
 */
public class WhiteshipConverter implements Converter<String, Whiteship> {

    public Whiteship convert(String source) {
        System.out.println("WhiteshipConverter!!!!!!!!!!!!!!!!!!!!");
        return new Whiteship(source);
    }
}
