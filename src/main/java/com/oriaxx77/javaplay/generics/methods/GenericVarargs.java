/**
 * 
 */
package com.oriaxx77.javaplay.generics.methods;

import java.util.ArrayList;
import java.util.List;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Illustrates how to use generics with varargs
 * @author BagyuraI
 */
public class GenericVarargs
{
	/**
	 * Creates a list populated with the defined objects.
	 * @param <T> Type of list
	 * @param objects Objects to prepopulate the returned list with.
	 * @return A list prepopulated with the defined parameters.
	 */
	@SafeVarargs // Uncomment this to see what happens
	public static <T> List<T>  makeList( T... objects )
	{
		
		List<T> l = new ArrayList<T>();
		for ( T t : objects )
			l.add( t );
		
		// heap pollution
		/*
		Object[] objectsAsArray = objects;
		objectsAsArray[ 0 ] = new Integer( 5 );
		Print.print( objects[0] );
		*/
		return l;
	}
	
	/**
	 * Static entry point.
	 * @param args Program arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		List<String> l = GenericVarargs.makeList( "X", "Y", "C" );
		Print.print( l );
	}
}
