package com.oriaxx77.javaplay.arrays;

/**
 * Example usage of variable argument list.
 * @author BagyuraI
 */
public class Varargs
{
	/**
	 * Print the elements of an array sequentially.
	 * @param args Array to print.
	 */
	static void printArray( Object[] args )
	{
		for ( Object o: args )
			System.out.print( o + " ");
		System.out.println();
	}
	
	/**
	 * Print the elements of an array sequentially.
	 * @param args objects to print
	 */
	static void printArray2( Object... args )
	{
		for ( Object o: args )
			System.out.print( o + " ");
		System.out.println();
	}
	
	/**
	 * Static entry point of the program
	 * @param args Program arguments. Not used atm.
	 */
	public static void main(String[] args)
	{
		Object[] array = new Object[]{ new Integer(47), new Float(3.14), new Double(11.11)};
		
		
		printArray( array );
		// printArray( "X", "Y" ); // This doesn't work. Not interchangeable.
		
		printArray2( array );
		printArray2( "X", "Y" );
		
		
	}
}
