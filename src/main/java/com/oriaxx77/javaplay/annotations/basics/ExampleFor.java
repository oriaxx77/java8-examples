/**
 * 
 */
package com.oriaxx77.javaplay.annotations.basics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for adding desctiption to java code examples.
 * It will also enable us for listing java code examples
 * @author BagyuraI
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExampleFor
{
	String value() default "";
	String[] values() default {};
	Class<?>[] classes() default {};
}
