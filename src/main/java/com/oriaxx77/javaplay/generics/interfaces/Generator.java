/**
 * 
 */
package com.oriaxx77.javaplay.generics.interfaces;

/**
 * Generator interface to generate Things.
 * @param T type to generate
 * @author BagyuraI
 */
public interface Generator<T>
{
	/**
	 * Get the next item.
	 * @return The generated item.
	 */
	T next();
}
