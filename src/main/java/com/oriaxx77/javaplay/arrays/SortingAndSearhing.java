/**
 * 
 */
package com.oriaxx77.javaplay.arrays;

import java.util.Arrays;
import static com.oriaxx77.javaplay.utility.Print.print;

/**
 * Example program for sorting and searching in arrays.
 * See {@link Arrays#sort(byte[])} and {@link Arrays#binarySearch(byte[], byte)} and
 * similar methods.
 * @author BagyuraI
 */
public class SortingAndSearhing
{

	/**
	 * Example program for sorting and searching in arrays.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// User array
		User[] users = new User[]{ new User("x",2), new User("k",1), new User( "b",9 ), new User("a", 10)};

		print( users );
	
		
		
		
		// Sort users by User.compareTo()
		Arrays.sort( users );
		
		print( users );
		
		// Sort users by name comparator
		Arrays.sort( users, new UserNameComparator() );
		
		print( users );
		
		// Sort users by rank comparator
		Arrays.sort( users, new UserRankComparator() );
		
		print( users );
		
		
		// find users by rank
		int index = Arrays.binarySearch( users, new User("z",1), new UserRankComparator() ); // Notice the name difference.
		print( users[index] );

		// The insertion point is the index of the first element greater than the key,
		// or the array.size(), if all elements in the array are less than the specified key.
		// Insertion point: -(insertion point) -1
		index = Arrays.binarySearch( users, new User("z",5), new UserRankComparator() ); 
		System.out.println( index );
		
	}

}
