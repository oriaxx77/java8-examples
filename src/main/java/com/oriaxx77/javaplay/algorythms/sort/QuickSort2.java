/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.sort;

import java.util.Arrays;

/**
 * An in place quick sort imlementation.
 * Pseudocode:
 * 
 * sort( arr, low, high )
 * 	begin
 * 		i = partition( arr, low, high )
 *  	sort ( arr, low, index-1 )
 *  	sort( arr, index, low+1 )
 * 	end
 * 
 * Partitioning:
 * 
 * On the partition step algorithm divides the array into two parts and 
 * every element a from the left part is less or equal than every element b from the right part. 
 * Also a and b satisfy a ≤ pivot ≤ b inequality. After completion of the recursion calls 
 * both of the parts become sorted and, taking into account arguments stated above, the whole array is sorted.
 * 
 * 1) 1 12 5 26 7 14 3 7 2 (unsorted)
 * 2) 1(i) 12 5 26 7(pivot) 14 3 7 2(j) (unsorted, pivot chosen, pivot = 7)
 * 3) 1 12(i) 5 26 7(pivot) 14 3 7 2(j) (12 >= 7 >= 2, swap 12 and 2 )
 * 4) 1 2 5 26(i) 7(pivot) 14 3 7(j) 12 (26 >=7 >=7, swap 26 and 7)
 * 5) 1 2 5 7 7(pivot)(i) 14 3(j) 26 12 ( 7 >= 7 >= 3, swap 7 and 3)
 * 6) 1 2 5 7 3 (j) 14 (i) 7 26 12 ( i > j, stop partition)
 * 
 * 7) 1 2 5 7 3       -      14 7 26 12 ( run quick sort recursively) 
 * 
 * Further reading: http://www.algolist.net/Algorithms/Sorting/Quicksort
 * 
 * @author BagyuraI
 *
 */
public class QuickSort2
{

	/**
	 * Sorts and array with an in place quicksort.
	 * @param array Array to sort.
	 */
	public void sort( int[] array )
	{
		quickSort( array, 0, array.length -1 );
	}
	
	/**
	 * Sorts an array with an in place quicksort form low to high index in the array.
	 * @param arr Array to sort
	 * @param low Low index
	 * @param high High index
	 */
	private void quickSort( int arr[], int low, int high ) 
	{
	      int index = partition( arr, low, high );
	      
	      if (low < index-1)
	            quickSort( arr, low, index-1 );
	      
	      if (index < high)
	            quickSort(arr, index, high);
	}
	
	/**
	 * Partition step of the quick sort. It returns with an index.
	 * below that index every element is lower than the element at the index.
	 * after that index every element is higher than the element at the index.
	 * @param arr Array to partition
	 * @param low Partition start index
	 * @param high Partition end index
	 * @return Partitioned array
	 */
	private int partition( int arr[], int low, int high )
	{
		int i = low, 
	    j = high;
	    int tmp;
	    int pivot = arr[ (low + high) / 2];
	     
	    while (i <= j) 
	    {
	    	while (arr[i] < pivot)
	    		i++;
            while (arr[j] > pivot)
            	j--;
            if (i <= j) 
            {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
	    };
	     
	    return i;
	}
	 
	
	
	/**
	 * Static entry ponint to demo this program.
	 * @param args Command line arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		int[] data = new int[]{ 5, 11, 7 , 4, 39, 2, 44, 43, 47};
		System.out.println( Arrays.toString( data ) );
		new QuickSort2().sort( data ) ;
		System.out.println( Arrays.toString( data ) );
	}
}
