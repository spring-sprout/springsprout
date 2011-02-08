/*
 * Created by IntelliJ IDEA.
 * User: helols
 * Date: 2009. 11. 01
 * Time: 오전 08:01:55
 * enjoy springsprout ! development!
 */
package springsprout.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD})
@Retention(RUNTIME)

public @interface CustomTransfer {}