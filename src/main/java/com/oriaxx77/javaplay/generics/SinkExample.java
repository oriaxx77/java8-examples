/**
 * 
 */
package com.oriaxx77.javaplay.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * A contravariance example where we use a sink to flush 
 * all elements of a collection.
 * @author BagyuraI
 */
public class SinkExample 
{

	
	private static class Sink<T>
	{
		void flush( T obj )
		{
			// do something
		}
		
		
	}
	
	private static class User
	{
		
	}
	
	private static class FBIAgent extends User
	{
		
	}
	
	private static class CIAAgent extends User
	{
		
	}
	
	public static <T>  T writeAll( List<T> coll, Sink< ? super T> snk )
	{
		T last = null;
		for ( T el: coll )
		{
			last = el;
			snk.flush( el );
		}
		return last;
	}

	
	public static void main(String[] args) {
		List<User> lu = new ArrayList<User>();
		lu.add( new User() );
		
		List<FBIAgent> lfbia = new ArrayList<FBIAgent>();
		lfbia.add( new FBIAgent() );
		
		writeAll( lfbia, new Sink<User>() );
	}
}
