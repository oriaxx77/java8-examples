/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.search;



/**
 * QucikSearch algorythm implementation.
 * @author BagyuraI
 *
 */
public class RecursiveQuickSearch
{

	/*
	 Pseudocode>
	 
	 search( array, startIdx, endIdx, el)
	 {
	 	1) Nothing to search in
	 	if ( endInx < startIdx )
	 		return -1 or insertion point. return with -(low+1)
	 		
	 	2) Choose a pivot
	 	pivot <- Choolse a pivot. e.g. start + (end-start)/2
	 	
	 	3) examine the element at pivot
	 	if ( el < array[pivot] )
	 		return search( array, startIdx, pivot-1 )
	 	else ( el > array[pivot] )
	 		return search( array, pivot+1, endIdx )
	 	else
	 		return array[pivot]
	 }
	 */
	
	/**
	 * Number array to search in.
	 */
	private int[] numbers;
	
	/**
	 * Creates a QuickSearch that can search in the provided numbers param.
	 * @param numbers Number array to search in.
	 */
	public RecursiveQuickSearch( int[] numbers )
	{
		this.numbers = numbers;
	}
	
	/**
	 * Search for the given number and returns with the position in the {@link #numbers}
	 * array or with -1 if it is not found.
	 * @param number Number to search for.
	 * @return the position of  the provided number in teh {@link #numbers} array or -1 if not found.
	 */
	public int find( int number )
	{
		return find( number, 0, numbers.length- 1);
	}
	
	/**
	 * Search in a subset of the {@link #numbers}
	 * @param number umber Number to search for.
	 * @param from start index of  the {@link #numbers} subset.
	 * @param to end index of  the {@link #numbers} subset.
	 * @return
	 */
	private int find( int number, int from , int to )
	{
		if ( to < from )
			return -(from+1);
		
		
		int mid = from + (to - from) /2;
		if ( number < numbers[mid] )
		{
			return find( number, from, mid-1 );
		}
		else if ( numbers[mid] < number )
		{
			return find( number, mid+1, to );
		}
		else
		{
			return mid;
		}
	}
	
	/**
	 * Runs the quicksearch example.
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] numbers = new int[]{ 2,3,4,5,11,48,119 };
		
		int [] numbersToFind = new int[]{ 5, 6 };
		for ( int numberToFind : numbersToFind )
		{
			int idx = new RecursiveQuickSearch( numbers ).find( numberToFind );
			System.out.println( "Numbers: " +  intArrayToString( numbers ) ); // Note: use the Arrays.toString( int[] );
			System.out.println( "Searching for: " + numberToFind );
			System.out.println( "Index: " + idx );
		}
	}

	/**
	 * Returns the string representation of an integer array.
	 * @param array int array to convert
	 * @return The string representation of an integer array.
	 */
	private static String intArrayToString(int[] array )
	{
		StringBuilder sb = new StringBuilder();
		for ( int i: array )
			sb.append( i ).append( ", " );
		return sb.toString();
	}

}
