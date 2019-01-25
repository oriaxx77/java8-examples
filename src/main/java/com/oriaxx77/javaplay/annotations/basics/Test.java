package com.oriaxx77.javaplay.annotations.basics;

import java.lang.annotation.*;


/**
 * Annotation that can be used to mark a method as a Test method.
 * @author BagyuraI
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test
{
	
}
