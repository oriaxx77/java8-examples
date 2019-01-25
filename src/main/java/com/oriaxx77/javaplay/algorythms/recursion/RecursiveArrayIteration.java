/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.recursion;

/*
 * Example for array iteration with recursion.
 * It only prints out the elements. (Tip: Visitor Design pattern)
 * @author BagyuraI
 */
public class RecursiveArrayIteration 
{

	/**
	 * Print the elements of the provided array one at a line.
	 * This method starts the recursion.
	 * @param array Array of elements to print.
	 */
	private static void printElements( int[] array )
	{
		if ( array == null || array.length == 0 )
			return;
			
		printElements( array, 0 ); // Starting the recursion
	}
	
	/**
	 * Print one element of the provided array in a separated line,
	 * then continues the recursion
	 * @param array Array of elements to print.
	 */
	private static void printElements( int[] array, int idx )
	{
		// We are done
		if ( idx >= array.length )
			return;
		
		// Do the work
		System.out.println( array[idx] );
		
		// Recursive call
		if ( idx < array.length-1 ) // It is unnecessary, but eliminates one recursive call. Is it worth?
			printElements( array, ++idx );
	}
	
	/**
	 * Entry point of this program.
	 * It prints out an array of integer using a recursive function.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] array = new int[]{1,2,3,4,5};
		printElements( array );
	}

}
