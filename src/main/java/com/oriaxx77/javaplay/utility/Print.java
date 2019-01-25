package com.oriaxx77.javaplay.utility;

import java.util.Arrays;



/**
 * Utility to print to the default system out.
 * @author BagyuraI
 */
public class Print
{
	/**
	 * Print the specified object to the default output.
	 * It uses the {@link Object#toString()} except for arrays
	 * where it prints out the elements. 
	 * @param o Object to print.
	 */
	public static void print( Object o )
	{
		if ( o instanceof Object[] )
		{
			// The object is an array.
			// Print it's elements.
			System.out.println( Arrays.toString( (Object[])o ) );
		}
		else
		{	
			// Print the object.
			System.out.println( o );
		}
		
		
	}
	/**
	 * Print the specified int array to the default output.
	 * @param arr int array to print
	 */
	public static void print( int[] arr )
	{
		for ( int i : arr )
		{
			System.out.println( i );
		}
	}
	
	
	
	/**
	 * Prints the binary representation of the provided integer.
	 * It will prepend with leading 0 chars until the maxLength is reached.
	 * 
	 * @param n Number to print the binary represeantation of
	 * @param maxLength The length of the printed string. 
	 */
	public static void printAsBinary( int n, int maxLength )
	{
		StringBuilder binary =  new StringBuilder( Integer.toBinaryString(n) );
		while ( binary.length() < maxLength )
		{
			char[] prefix = new char[ maxLength - binary.length()];
			Arrays.fill( prefix , '0' );
			binary.insert( 0, new String( prefix ) );
		}
			
		System.out.println( binary );
		
	}
	
	/**
	 * Prints the byte as a binary string.
	 * @param b The byte to print.
	 */
	public static void printByteAsInBinary( byte b )
	{
		printAsBinary( b, 7 );
	}
}
