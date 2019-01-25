/**
 * 
 */
package com.oriaxx77.javaplay.generics.methods;

/**
 * Illustrates how to define a generic method.
 * @author BagyuraI
 */
public class GenericMethod
{
	/**
	 * Example generic method.
	 * @param <T> Type of the o parameter
	 * @param o An object of which we print the class name to the default output.
	 */
	public <T> void f( T o )
	{
		System.out.println( o.getClass().getName() );
	}
	
	/**
	 * Static entry point to demonstrate the 
	 * definition and usage of generic methods
	 * @param args Program arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		GenericMethod gm = new GenericMethod();
		gm.f( 11 );
		gm.f( "abcd" );
	}
}
