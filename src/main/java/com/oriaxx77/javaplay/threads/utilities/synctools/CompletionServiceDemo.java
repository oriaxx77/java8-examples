/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Example for starting asynch tasks and waiting for the result in a blocking manner,
 * without polling periodically. It starts 10 {@link Callable} tasks and wait for their completion.
 * It is achieved by using a {@link CompletionService}.
 * 
 * @author BagyuraI
 *
 */
public class CompletionServiceDemo
{

	/**
	 * Creates a list of 10 {@link Callable} task objects.
	 * The callables will return a random Long.
	 * @return  a list of 10 {@link Callable} task objects.
	 * The callables will return a random Long.
	 */
	private static List<Callable<Long>> createTasks()
	{
		List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
		for ( int i = 0; i< 10; i++ )
		{
			tasks.add( new Callable<Long>()
			{
				
				@Override
				public Long call() throws Exception
				{
					return new Random().nextLong();
				}
			});
		}
		return tasks;
	}
	
	/**
	 * It starts 10 {@link Callable} tasks and wait for their completion.
	 * @param args Command line arguments. It is not used atm.
	 * @throws InterruptedException interrupted while we are waiting for task completion
	 * @throws ExecutionException is thrown if there is a computation exception.
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		ExecutorService executor = Executors.newFixedThreadPool( 3 );
		CompletionService<Long> service = new ExecutorCompletionService<>( executor );
		List<Callable<Long>> tasks = createTasks();
		
		
		try
		{
			for ( Callable<Long> task : tasks )
			{
				service.submit(task);
			}
			
			
			for ( int i = 0; i < tasks.size(); i++ )
			{
				Future<Long> result = service.take();
				System.out.println( "Result: " +  result.get() );
			}
		}
		finally
		{
			executor.shutdown();
		}
		
		
		
		

	}

}
