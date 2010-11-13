package sandbox.convert;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오후 12:33:23
 */
public class WhiteshipFormatter implements Formatter<Whiteship>{
    public Whiteship parse(String text, Locale locale) throws ParseException {
        System.out.println("Formatter parse win!!!!!!!!!!");
        return new Whiteship(text);
    }

    public String print(Whiteship object, Locale locale) {
        System.out.println("Formatter print win!!!!!!!!!!");
        return object.getName();
    }
}
