/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.atomic;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;

import com.oriaxx77.javaplay.utility.Sleeper;


/**
 * Example application that simulates updating a shared site statistics with page access count and errors.
 * The {@link Request} is a runnable one time request that shares a single {@link Statistics}. 
 * 
 * Examples for {@link AtomicReference}
 * 1) Use them when you want to access data atomically.
 * 2) Atomic references have the same memory semantics as volatile variables.
 * 3) It is useful if you have an object that you occasionally want to replace with a new copy.
 * 4) The object should be immutable by design.
 * 5) It is used when you want to update more than one field atomically.
 * 
 * {@link AtomicStampedReference} is similar to {@link AtomicReference} except it 
 * also has an int called initialStamp.  
 * The purpose of the initialStamp is to prevent the situation 
 * where immutable object being referenced goes from A to B and back to A again.  
 * The idea is you pass a unique value as the initialStamp.  
 * If the reference to the immutable object is the same but the initialStamp is different this suggests that A ==> B ==> 
 * A occurred. When A ==> B ==> A occurs the atomic update will fail and it will attempt to do the CAS (compare-and-swap) operation again.
 * 
 * 
 * @author BagyuraI
 */
public class WebStatisticsApp
{
	/** 
	 * Access statistics for a webapp.
	 * It contains metrics for access count and error count. 
	 */
	private static final class Statistics
	{
		/** Access count */
		private final int accessCount;
		/** Error count */
		private final int errorCount;
		/**
		 * Creates an Statistics.
		 * @param accessCount Access count
		 * @param noErrors Error count
		 */
		public Statistics( int accessCount, int errorCount )
		{ 
			this.accessCount = accessCount; 
			this.errorCount = errorCount;
		}
		
		/**
		 * Creates a new statistics based on the current one.
		 * The accessCount and the errorCount are increased by one.
		 * @return the new Statistics object.
		 */
		public Statistics incWithError()
		{
			return new Statistics( (accessCount+1) , (errorCount+1) );
		}
		
		/**
		 * Creates a new statistics based on the current one.
		 * The accessCount is increased by one, the errorCount untouched.
		 * @return The new Statistics object
		 */
		public Statistics incWithoutError()
		{
			return new Statistics( (accessCount+1), errorCount );
		}
		
		/**
		 * String representation of the object.
		 */
		@Override
		public String toString(){ return "Statistics:[accesses: " + accessCount + ", errors:" + errorCount + "]"; }
	}
	
	/**
	 * A one time page access. Running it will update a shared site statistics.
	 * @author BagyuraI
	 */
	private static final class Request implements Runnable
	{
		/**
		 * Site statistics. It is a shared instance.
		 */
		private final AtomicReference<Statistics> statistics;
		/**
		 * Constructor
		 * @param statistics the site statistics (possible shared)
		 */
		public Request( AtomicReference<Statistics> statistics )
		{ 
			this.statistics = statistics; 
		}
		
		/**
		 * Updates the site statistics. It increments the page access by one
		 * and it MAY increment the error counter by one. It is determined by the {@link #errorOccured()} method.
		 * It randomly sleeps before attempting to do the update. 
		 * 
		 */
		@Override
		public void run()
		{
			Statistics currentStatistics, newStatistics;
			 
			do
			{
				
				Sleeper.sleep(500);
				
				// NOTE: get the atomic object
				// Do not use stats.get().getXYZ because other threads may change the underlying value by
				// setting it to a new variable.
				currentStatistics = statistics.get();
				newStatistics = createNewStatistics( currentStatistics );
				
				// NOTE: set the atomic object. If the prev is not == with
				// The object held by the AtomicReference then someone has updated the object.
			} while (!statistics.compareAndSet( currentStatistics, newStatistics ));	
			print( "stats, " + Thread.currentThread().getName() + " - " + statistics );
			
		}

		private Statistics createNewStatistics( Statistics currentStatistics )
		{
			boolean isErrorOccured = new Random().nextInt( 3 ) % 3 == 0;
			Statistics newStatistics = isErrorOccured ? currentStatistics.incWithError()
												: currentStatistics.incWithoutError();
			
			return newStatistics;
		}
		
	}
	
	/**
	 * Static entry point of the application. It creates a shared {@link Statistics} wrapped in an {@link AtomicReference}.
	 * Then it creates 10 {@link Request} objects that share the {@link Statistics}. The requests are run in their own thread
	 * and they try to update the shared {@link Statistics} 
	 * @param args Command line arguments. It is not used at this moment.
	 * @throws InterruptedException Someone interrupted a {@link Request#run()}
	 */
	public static void main(String[] args) throws InterruptedException
	{
		AtomicReference<Statistics> stats = new AtomicReference<Statistics>( new Statistics( 0, 0) );
		
		IntStream.range(0, 10)
				.mapToObj( i -> new Thread( new Request( stats )))
				.forEach( t -> t.start() );
		Sleeper.sleep( 15000 );
		print( "Final stats: " + stats );
	}
}
