/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.search;

import java.util.ArrayDeque;
import java.util.Deque;



/**
 * QucikSearch algorythm implementation.
 * @author BagyuraI
 *
 */
public class SequentialQuickSearchWithStack
{

	
	
	/**
	 * Number array to search in.
	 */
	private int[] numbers;
	
	/**
	 * Creates a QuickSearch that can search in the provided numbers param.
	 * @param numbers Number array to search in.
	 */
	public SequentialQuickSearchWithStack( int[] numbers )
	{
		this.numbers = numbers;
	}
	
	public static class SearchParameters 
	{
		private int from;
		private int to;
		private int mid;
		
		/**
		 * @param from
		 * @param to
		 */
		public SearchParameters(int from, int to)
		{
			super();
			this.from = from;
			this.to = to;
			this.mid = (from + to) >>> 1;
		}

		/**
		 * @return the from
		 */
		public int getFrom()
		{
			return from;
		}

		/**
		 * @return the to
		 */
		public int getTo()
		{
			return to;
		}
		
		public int getMid()
		{
			return mid;
		}
		
		
		
	}
	
	/**
	 * Search for the given number and returns with the position in the {@link #numbers}
	 * array or with -1 if it is not found.
	 * @param number Number to search for.
	 * @return the position of  the provided number in teh {@link #numbers} array or -1 if not found.
	 */
	public int find( int number )
	{
		Deque<SearchParameters> stack = new ArrayDeque<SearchParameters>();
		stack.add( new SearchParameters( 0, numbers.length) );
		
		SearchParameters searchParam = null;
		while ( !stack.isEmpty() )
		{
			searchParam = stack.pop();
			if ( searchParam.getFrom() > searchParam.getTo() )
				return -(searchParam.getFrom()+1);
			
			if ( number < numbers[ searchParam.getMid() ] )
			{
				stack.push( new SearchParameters( searchParam.getFrom(), searchParam.getMid()-1 ) );
			}
			else if ( numbers[ searchParam.getMid() ] < number )
			{
				stack.push( new SearchParameters( searchParam.getMid()+1, searchParam.getTo() ) );
			}
			else
			{
				return searchParam.getMid();
			}
		}	
		
		
		return -1;
		
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
			int idx = new SequentialQuickSearchWithStack( numbers ).find( numberToFind );
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
