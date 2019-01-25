/**
 * 
 */
package com.oriaxx77.javaplay.arrays;

import java.util.Arrays;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Array examples.
 * @author BagyuraI
 */
public class Arrrays 
{
	/**
	 * Runs this example.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// 1) Declaring arrays
		String[] sa = new String[]{ "a", "b" };
		String[] sa2 = new String[2]; // Define only the capacity. Every element is null here.
		@SuppressWarnings("unused")
		String[] sa3 = { "a", "b", "c"}; // It can only be used in initializers
		
		// 2) Iterating over arrays
		//    Arrays are 0 based
		Print.print( "Elements in sa: " );
		for ( int i = 0; i < sa.length; i++ )
		{
			// Accessing elements
			Print.print( sa[i] );
		}
		
		// 2) Iterating over arrays
		for ( String s : sa )
		{
			// Accessing elements
			Print.print( s );
		}
		
		Print.print( "Elements in sa2: " );
		for ( int i = 0; i < sa2.length; i++ )
		{
			// Accessing elements
			Print.print( sa2[i] );
		}
		
		
		
		// 3) toString() is pretty useless
		Print.print( "sa.toString() (useless): " );
		System.out.println( sa );
		
		
		try 
		{
			sa[3] = "c" ;	
		} 
		catch ( ArrayIndexOutOfBoundsException e) 
		{
			Print.print( e );
		}
		
		// 4 copying arrays
		char[] src = new char[]{ 'h', 'e', 'l' , 'l', 'o' };
		char[] dest = new char[6]; // every element is ascii 0 here.
		
		Print.print( "ASCII chars in dest before the copy (all 0) " );
		for ( int i = 0; i < dest.length; i++ )
		{
			// Accessing elements
			Print.print( (int)dest[i] );
		}
		
		
		System.arraycopy( src, 0, dest, 0, src.length);
		Print.print( "ASCII chars in dest after the copy" );
		for ( int i = 0; i < dest.length; i++ )
		{
			// Accessing elements
			Print.print( (int)dest[i] );
		}
		
		
		// 5) Array covariance
		sa = new String[]{ "a", "b", "c" };
		Object[] so = sa; // Aliasing is legal
		
		try 
		{
			so[0] = new Integer(1); // Illegal. Throws an ArrayStoreException
			Print.print( so[0] );
		} 
		catch ( java.lang.ArrayStoreException e) 
		{
			Print.print( e );
		}
		
		// 6) Copying arrays
		String[] origArray = new String[]{ "a", "b" };
		String[] destArray = new String[ origArray.length ];
		System.arraycopy( origArray , 0, destArray, 0, origArray.length);
		
		// 7) Comparing arrays
		Print.print( origArray.equals( destArray ));
		Print.print( Arrays.equals( origArray, destArray));
		
	}
	
	
	
}
