package sandbox.convert;

import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오후 12:42:47
 * To change this template use File | Settings | File Templates.
 */
public class WhiteshipFormattingConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    @Override
    protected void installFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(Whiteship.class, new WhiteshipFormatter());
        super.installFormatters(registry);
    }

}
