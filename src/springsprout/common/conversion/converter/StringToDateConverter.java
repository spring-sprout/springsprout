package springsprout.common.conversion.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import springsprout.common.util.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오전 9:53:48
 */
public class StringToDateConverter implements ConverterFactory<String, Date>{

    public <T extends Date> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToDate<T>();
    }

    private static final class StringToDate<T extends Date> implements Converter<String, T> {
        @SuppressWarnings("unchecked")
        public T convert(String source) {
            DateFormat dateFormat = new SimpleDateFormat(DateUtils.yyyyMMdd_SLASH);
            Date date = null;
            try {
                date = dateFormat.parse(source);
            } catch (ParseException e) {
            }
            return (T)date;
        }
    }
}
