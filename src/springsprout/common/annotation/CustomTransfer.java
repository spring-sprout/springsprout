/*
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 11. 01
 * Time: 오전 08:01:55
 * enjoy springsprout ! development!
 */
package springsprout.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;


@Target({METHOD})
@Retention(RUNTIME)

public @interface CustomTransfer {}