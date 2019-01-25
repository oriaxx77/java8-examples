/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.sort;

/**
 * Merge sort. Use this as the following:
 * MergeSort sort = new MergeSort( data );
 * sort.sort();
 * 
 * @author BagyuraI
 */
public class MergeSort
{
	/**
	 * Array to sort.
	 */
	private int[] data;
	/**
	 * A temporary array that is used for sorting
	 */
	private int[] tmp;
	
	/**
	 * Creates a MergeSort.
	 * @param data int array to sort.
	 */
	public MergeSort( int[] data )
	{
		this.data = data;
		this.tmp = new int[ data.length ];
	}
	
	/**
	 * Sort the {@link #data}
	 */
	public void sort()
	{
		sort( 0, data.length-1);
	}
	
	/**
	 * Sort the {@link #data} from min idx to max idx.
	 * @param min Min index of the sorting range.
	 * @param max Max index of the sorting range.
	 */
	private void sort( int min, int max)
	{
		// Leave the recursion
		if ( min >= max )
			return;
		
		// Divide
		int mid = min + (max-min)/2;
		sort( min, mid);
		sort( mid+1, max );
		
		// Conquer
		merge( min, mid, max );
		
			
	}
	
	/**
	 * Merge the two areas of the array (min to mid) and (mid+1 to max)
	 * @param min
	 * @param mid
	 * @param max
	 */
	private void merge(int min, int mid, int max)
	{
		for ( int i = min; i <= max; i++ )
		{
			tmp[i] = data[i];
		}
		
		int i = min;
		int j = mid+1;
		int k = min;
		
		while ( i <= min && j <= max )
		{
			if ( tmp[i] <= tmp[j] )
			{
				data[k] = tmp[i];
				i++;
			}
			else 
			{
				data[k] = tmp[j];
				j++;
			}
			k++;
		}
		
		while ( i <= mid )
		{
			data[k] = tmp[i];
			i++;
			k++;
		}
		
		while ( j <= max )
		{
			data[k] = tmp[j];
			j++;
			k++;
		}
		
		
		
	}

	/**
	 * Print the {@link #data} on to the standard output.
	 * @param data Data to print.
	 */
	private static void print( int[] data )
	{
		for ( int i : data )
		{
			System.out.print( "" + i + ", ");
		}
		System.out.println();
	}
	
	/**
	 * Static entry point of this app. It demonstrates the 
	 * MergeSort.
	 * @param args Command line args. It is not used atm.
	 */
	public static void main(String[] args)
	{
		int[] data = new int[]{ 5, 1, 3, -2, 111};
		print( data );
		MergeSort sort = new MergeSort( data );
		sort.sort();
		print( data );
	}
}
