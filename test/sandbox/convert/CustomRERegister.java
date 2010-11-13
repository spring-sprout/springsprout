package sandbox.convert;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.ConvertingPropertyEditorAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: Whiteship
 * Date: 2010. 2. 18
 * Time: 오전 11:45:05
 */
public class CustomRERegister implements PropertyEditorRegistrar {

    ConversionService conversionService;

    public CustomRERegister(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public void registerCustomEditors(PropertyEditorRegistry registry) {
//        registry.registerCustomEditor(Whiteship.class, new WhiteshipPE());
        registry.registerCustomEditor(Whiteship.class, new ConvertingPropertyEditorAdapter(conversionService, TypeDescriptor.valueOf(Whiteship.class)));
    }
}
