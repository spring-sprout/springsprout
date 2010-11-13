package springsprout.common.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainInfo {
	
	String value() default "";

	String descr() default "";

}
