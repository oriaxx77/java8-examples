/**
 * 
 */
package com.oriaxx77.javaplay.arrays;

import com.oriaxx77.javaplay.utility.Print;



/**
 * A binary search implementation example.
 * @author BagyuraI
 */
public class BinarySearch 
{
	/**
	 * Binary search for a string in a string array
	 * @param sa array to search in.
	 * @param s element we search.
	 * @return Index of the element in the array or -1 if not found.
	 */
	public static int search( String[] sa, String s )
	{
		return binarySearch( sa, s, 0, sa.length);
	}
	
	/**
	 * The recursive binary search method
	 * @param sa String arrray to search in.
	 * @param key Key to search for
	 * @param min Min idx of the range to search for.
	 * @param max Max id of the range to search for
	 * @return Idx of the searched element in the array or -1 if not found.
	 */
	private static int binarySearch( String[] sa, String key, int min, int max )
	{
		// test if the array is empty
		if ( sa == null || max < min )
			return -1;

		// Midpoint
		int mid = min + ((max - min) / 2);
		
		if ( sa[mid].compareTo( key ) > 0 ) 
			// the key is in the lower subset 
			return binarySearch( sa, key, min, mid-1 );
		else if ( sa[mid].compareTo( key ) < 0 )
			// the key is in the upper subset
			return binarySearch( sa, key, mid+1, max );
		else
			// the key is found
			return mid;
	}
	
	
	
	
	
	public static void main(String[] args) 
	{
		String[] sa = { "a", "b", "c", "d", "e" };
		int idx = search( sa, "e" );
		Print.print( idx );
	}
}
