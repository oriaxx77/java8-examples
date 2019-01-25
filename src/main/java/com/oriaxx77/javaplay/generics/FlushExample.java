/**
 * 
 */
package com.oriaxx77.javaplay.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple example for contravariance.
 * We have a list and we want to flush each elements in the list
 * through a sink.
 * @author BagyuraI
 *
 */
public class FlushExample {

	private static class Car {}
	
	private static class Mini extends Car {}
	
	private static class BMW extends Car {}
	
	/**
	 * A Sink to flush objects.
	 * @author BagyuraI
	 *
	 * @param <T> Type we want to flush
	 */
	private static class Sink<T>
	{
		public void flush( T o )
		{
			// do something useful
		}
	}
	
	/**
	 * Flushes each elements in the list and return with the 
	 * last element.
	 * @param list Elements to be sinked.
	 * @param sink Sink to flush the elements
	 * @return The last element that was flushed.
	 */
	static <T> T flushAll( List<T> list, Sink<? super T> sink )
	{
		T last = null;
		for ( T el: list )
		{
			sink.flush( el );
			last = el;
		}
		return last;
	}
	
	/**
	 * Runs this example
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// Flush some minis
		List<Mini> minis = new ArrayList<Mini>();
		minis.add( new Mini() );
		Sink<Car> sink = new Sink<Car>();
		Mini lastMini = flushAll( minis, sink );
		System.out.println( lastMini );
		
		// Flush some BMWs
		List<BMW> bmws = new ArrayList<BMW>();
		bmws.add( new BMW() );
		BMW lastBMW = flushAll( bmws, sink );
		System.out.println( lastBMW );

	}

}
