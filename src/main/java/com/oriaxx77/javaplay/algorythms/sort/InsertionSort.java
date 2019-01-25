/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.sort;


/**
 * Insertion sorting algorithm example.
 * the {@link #main(String[])} method sort an array of int.
 * @author BagyuraI
 */
public class InsertionSort 
{
	/**
	 * Prints an int array onto the default output.
	 * @param arr int array to print.
	 */
	private static void print( int[] arr )
	{
		for ( int el: arr )
			System.out.print(el + ",");
		System.out.println();
	}

	
	
	
	/**
	 * Sort an int array using the insertion sort algorythm.
	 * @param data int array to sort.
	 */
	private static void sort( int[] data )
	{
	
		// Iterate through all element except the first one.
		for ( int i = 1; i < data.length; i++ )
		{
			// Move the current element down until it is 
			// on the right place.
			for ( int j = i; j > 0; j-- )
			{
				while ( data[j] < data[j-1] )
				{
					// The previous element is 
					// less than the actual.
					// Swap them.
					int temp = data[j];
					data[j] = data[j-1];
					data[j-1] = temp;
				}
			}
		}

	}
	
	/**
	 * Static entry point of this example.
	 * It uses the insertion sort algorythm to sort an array of int.
	 * @param args Command line arguments. It is not used at this moment.
	 */
	public static void main(String[] args)
	{
		int[] data = new int[]{ 5, 11, 7 , 4, 39, 2, 44, 43, 47};
		print( data );
		sort( data );
		print( data );
	}
}
