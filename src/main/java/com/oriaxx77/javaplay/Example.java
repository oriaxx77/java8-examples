package com.oriaxx77.javaplay;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface Example {
	
	String value() default "";
	
	String[] values() default {};
}
