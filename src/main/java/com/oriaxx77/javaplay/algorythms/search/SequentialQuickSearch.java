package com.oriaxx77.javaplay.algorythms.search;

import java.util.Arrays;

/**
 * Quick Search implemented with a sequential algorythm using a stack.
 * The {@link #numbers} should be sorted.
 * @author BagyuraI
 */
public class SequentialQuickSearch
{
	/**
	 * Numbers to search in.
	 */
	private int[] numbers;
	
	/**
	 * Creates a search that can be used to search in an array
	 * with sequential quick search algorythm.
	 * @param numbers Numbers to search in.
	 */
	public SequentialQuickSearch(int[] numbers)
	{
		this.numbers = numbers;
	}
	
	/**
	 * Search for the specified number in the {{@link #numbers}.
	 * @param numberToFind Numbers to search for.
	 * @return The idx of the element if found or the insertion index calculated in the following formula -1 * (idx + 1)
	 */
	private int find(int numberToFind)
	{
		
		
		int start = 0;
		int end = numbers.length;
		
		while ( start <= end )
		{
			int mid = ( start + end ) >>> 1;
			if ( numberToFind < numbers[mid] )
				end = mid-1;
			else if ( numberToFind > numbers[mid] )
				start = mid+1;
			else
				return mid;
		}
		
		return -(start+1);
	}

	/**
	 * Static entry point for this demo.
	 * @param args Command line arguments. It is not used.
	 */
	public static void main( String[] args )
	{
		int[] numbers = new int[] { 3,4,5,11,13,79,100}; //NOTE: quick search requires sorted collection 
		int[] numbersToFind = new int[]{ 7, 11 };
		
		SequentialQuickSearch search = new SequentialQuickSearch( numbers );
		
		for ( int numberToFind : numbersToFind )
		{
			System.out.println( "number to find: " + numberToFind );
			System.out.println( "numbers in array: " + Arrays.toString( numbersToFind ) );
			System.out.println( "idx: " + search.find( numberToFind) );
		}
		
	}

	
}
