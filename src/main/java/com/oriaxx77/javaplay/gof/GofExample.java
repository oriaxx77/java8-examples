package com.oriaxx77.javaplay.gof;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for Gang Of Four design pattern
 * examples. The {@link #pattern()} contains the name of  the pattern,
 * the {@link #stereotype()} contains the stereotype of the type fulfills
 * in the pattern.
 * @author BagyuraI
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GofExample
{
	/** Gof pattern */
	String pattern();
	/** Stereotype of the annotated type it fulfills in the pattern */
	String stereotype();
}
