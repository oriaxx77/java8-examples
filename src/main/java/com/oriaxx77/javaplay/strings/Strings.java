/**
 * 
 */
package com.oriaxx77.javaplay.strings;

import static com.oriaxx77.javaplay.utility.Print.*;
/**
 * Examine the {{@link #main(String[])} for quick facts about strings in java.
 * @author BagyuraI
 */
public class Strings 
{
	/**
	 * Entry point of this app.
	 */
	public static void main(String[] args) 
	{
		
		// 1) Declaring a string
		String s = new String( "I am a string" );
		s = "I am also a string"; // This is automatically interned.
		
		// 2) Strings are immutable.
		s.toUpperCase();
		print( s );
		
		// 3) Intern
		
		// Comparison
		
		s = "x"; // // interned string, stored in a special memory location
		String s2 = "x"; // interned string, stored in a special memory location
		print( s == s2 );
		
		s2 = new String( "x" );
		print( s == s2 );
		
		
		s2 = new String( "x" ).intern(); // Return with the internal representation.
		print( s == s2 );
		
		// 4) String concatenation
		s = "I am " + "a" + " string!";
		print( s );
		
		// 5) Modification with StringBuilder && StringBuffer
		//    Notice the builder design patter.
		
		StringBuilder sb = new StringBuilder( "A sample " ).append( "modifiable " ).append( "string." );
		print( sb );
	}
}
