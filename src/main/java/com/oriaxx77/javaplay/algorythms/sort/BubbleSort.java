/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.sort;


/**
 * Bubble sorting algorithm example.
 * the {@link #main(String[])} method sort an array of int.
 * @author BagyuraI
 */
public class BubbleSort 
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
	 * Sort an int array using the bubble sort algorythm.
	 * @param data int array to sort.
	 */
	private static void sort( int[] data )
	{
		// End of each bubbling of an element.
		// First we find the last element (n).
		// Then the n-1. Then the n-2. ...... then the first element.
		for ( int i = data.length -1; i > 0; i-- )
		{
			for ( int j = 0; j < i-1; j++ )
			{
				if ( data[j] > data[j+1 ])
				{
					int tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
				}
			}
		}
		

	}
	
	/**
	 * Static entry point of this example.
	 * It uses the bubble sort algorythm to sort an array of int.
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
