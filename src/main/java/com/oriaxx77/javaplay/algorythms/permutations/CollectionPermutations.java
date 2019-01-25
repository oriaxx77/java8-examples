/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.permutations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that can define the permutations of a collection of element T
 * It creates the permutations upon construction time. The permutations can 
 * be obtained through the {@link #getPermutations()} method. It uses 
 * recursion to create the permutations.
 * @author BagyuraI
 */
public class CollectionPermutations<T>
{
	
	/**
	 * A list that contains the permutations of the elements of the input collection
	 * provided construction time.
	 */
	private List<List<T>> permutations = new ArrayList<List<T>>();
	
	/**
	 * Creates a CollectionPermutations object. It will create the permutations 
	 * of the provided colletion upon construction time.
	 * @param coll Elements to permutate.
	 */
	public CollectionPermutations( Collection<T> coll )
	{
		if ( coll == null )
			throw new IllegalArgumentException( "Null element collection." );
		
		if ( coll.isEmpty() )
			return;
		
		makePermutations( new ArrayList<T>(), coll );
	}
	
	/**
	 * Returns the permutations of the collection provided upon creatin time.
	 * @return the permutations
	 */
	public List<List<T>> getPermutations()
	{
		return permutations;
	}


	/**
	 * Makes the permutations of a given subset. This is where the recursion happens.
	 * @param head Head of a given permutation.
	 * @param coll Remaining possible elements.
	 */
	private void makePermutations( List<T> head, Collection<T> coll )
	{
		if ( coll.size() == 1 )
		{
			head.add( coll.iterator().next() );
			permutations.add( head );
			return;
		}
			
			
		for ( T e : coll )
		{
			Collection<T> eColl = new HashSet<T>( coll );
			eColl.remove( e );
			
			List<T> eHead = new ArrayList<T>( head );
			eHead.add( e );
			
			makePermutations( eHead, eColl );
		}
	}
	/**
	 * Static entry point to demonstrate this type.
	 * It permutates the collection of a,b,c strings.
	 * @param args Command line arguments. They are not used at this moment.
	 */
	public static void main(String[] args)
	{
		Set<String> elements = new HashSet<String>();
		elements.add( "a" );
		elements.add( "b" );
		elements.add( "c" );
		CollectionPermutations<String> collPerm = new CollectionPermutations<>( elements );
		
		for ( List<String> permutation : collPerm.getPermutations() )
			System.out.println( permutation );
	}
}
