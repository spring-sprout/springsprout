package sandbox.convert;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오후 4:00:38
 */
public class WhiteshipConverter2 implements ConditionalGenericConverter {

    public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(String.class, Whiteship.class));
	}

	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		Class<?> sourceClass = sourceType.getObjectType();
        Class<?> targetClass = targetType.getObjectType();
		return String.class.isAssignableFrom(sourceClass) && Whiteship.class.isAssignableFrom(targetClass);
	}

	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		return new Whiteship((String)source);
	}
}
