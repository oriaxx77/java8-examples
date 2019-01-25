/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.sort;


/**
 * Quick sorting algorithm example.
 * the {@link #main(String[])} method sort an array of int.
 * @author BagyuraI
 */
public class QuickSort 
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

	public static void sort(int[] numbers)
	{
		// check for empty or null array
		if (numbers == null || numbers.length == 0)
		{
			return;
		}

		quicksort(numbers, 0, numbers.length - 1);
	}

	private static void quicksort(int[] numbers, int low, int high)
	{
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = numbers[low + (high - low) / 2];

		// Divide into two lists
		while (i <= j)
		{
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (numbers[i] < pivot)
			{
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (numbers[j] > pivot)
			{
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j)
			{
				exchange(numbers, i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quicksort(numbers, low, j);
		if (i < high)
			quicksort(numbers, i, high);
	}

	private static void exchange(int[] numbers, int i, int j)
	{
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	/**
	 * Static entry point of this example.
	 * It uses the Quick sort algorythm to sort an array of int.
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
