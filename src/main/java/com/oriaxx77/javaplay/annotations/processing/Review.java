/**
 * 
 */
package com.oriaxx77.javaplay.annotations.processing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An interface that marks a class as reviewed
 * @author BagyuraI
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Review 
{
	/** Author */
	Author author();
	/** Reviewer */
	Reviewer reviewer();
	/** Date of the review */
	String date();
}


/**
 * A person's name.  This annotation type is not designed
 * to be used directly to annotate program elements, but to
 * define elements of other annotation types.
 */
@interface Name 
{
    String first();
    String last();
}
/**
 * Indicates the author of the annotated program element.
 */
@interface Author 
{
    Name value();
}
/**
 * Indicates the reviewer of the annotated program element.
 */
@interface Reviewer 
{
    Name value();
}
