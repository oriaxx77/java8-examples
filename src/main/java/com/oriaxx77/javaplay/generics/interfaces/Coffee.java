/**
 * 
 */
package com.oriaxx77.javaplay.generics.interfaces;

/**
 * Coffe for the CoffeGenerator to use.
 * @author BagyuraI
 */
public class Coffee
{
	/**
	 * Counter for generating ids.
	 */
	private static long counter = 0;
	/**
	 * Coffee identifier.
	 */
	private final long id = counter++;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " " + id;
	}
	
	
}
