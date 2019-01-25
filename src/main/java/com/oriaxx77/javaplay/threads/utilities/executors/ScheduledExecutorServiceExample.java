/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Example app. for the usage of the {@link ScheduledExecutorService}.
 * It starts a task periodically, executing it in every 2 seconds.
 * Then it starts a second task that will cancel this periodic task after 10 seconds of execution.
 * 
 * @author BagyuraI
 */
public class ScheduledExecutorServiceExample
{

	
	
	/**
	 * Starts the app.
	 * @param args Program arguments. They are not used atm.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		
		// ExecutorService that can schedule commands to run after a given delay 
		// or to execute them periodically
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool( 2 );
		
		// Execute a task periodically every 2 seconds.
		Runnable periodicTask = new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println( "Executing command..." );
				
			}
		};
		final ScheduledFuture<?> periodicTaskHandler = scheduler.scheduleAtFixedRate( periodicTask, 0, 2, TimeUnit.SECONDS );
		
		
		// Schedule a task to cancel our periodic task after 10 seconds
		Runnable cancelTask = new Runnable()
		{
			
			@Override
			public void run()
			{
				periodicTaskHandler.cancel( true );
				System.out.println( "Cancelling task1." );
			}
		};
		
		
		
		final ScheduledFuture<?> cancelTaskHandler = scheduler.schedule( cancelTask, 10, TimeUnit.SECONDS );
		cancelTaskHandler.get();
		System.out.println( "PeriodicTask.isDone: " + periodicTaskHandler.isDone() + ", PeriodicTask.isCancelled: " + periodicTaskHandler.isCancelled() );
		
		
	}

}
