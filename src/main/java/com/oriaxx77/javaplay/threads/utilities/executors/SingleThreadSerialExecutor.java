/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * It executes the submitted commands in one single thread following in a FIFO manner.
 * An interruption of this single thread ends the run of the thread. 
 * At this simple implementation any runtime exception will ungracefully ends the underlying thread.
 * A shortcoming, there is no way to see if a task completed or not.
 * @author BagyuraI
 */
public class SingleThreadSerialExecutor implements Executor
{
	/**
	 * A blocking queue that holds the submitted commands.
	 */
	private java.util.concurrent.BlockingQueue<Runnable> commands = new LinkedBlockingQueue<Runnable>();
	/**
	 * The single thread that will be used to execute the tasks.
	 */
	private Thread thread;
	
	/**
	 * It creates a {@link SingleThreadSerialExecutor}.
	 * It starts the single thread that it will use to execute the commands.
	 */
	public SingleThreadSerialExecutor()
	{
		startThread(); // NOTE: it may be not good to have here. Should we move it to the execute()?
	}
	
	/**
	 * Executes the submitted command in it's single thread. 
	 * @throws IllegalStateException thrown when the underlying thread is not running.
	 * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
	 */
	@Override
	public synchronized void execute(final Runnable command) throws IllegalStateException
	{
		if ( !isAvailable() )
			throw new IllegalStateException( "The underlying thread is not running" );
		commands.offer( command );
	}
	
	/**
	 * Starts the single thread that will be used to execute the submitted commands.
	 * The single thread is initialized with an anonym, inner {@link Runnable} that 
	 */
	private void startThread()
	{
		thread = new Thread( new Runnable()
		{
			@Override
			public void run()
			{
				while ( !Thread.currentThread().isInterrupted() )
				{
					try
					{
						commands.take().run();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		thread.start();
	}
	
	/**
	 * Query the availability of the Executor.
	 * We think that the executor is available if the underlying thread is not in the terminated state.
	 * @return {@code TRUE} if this executor can execute commands.
	 */
	private boolean isAvailable()
	{
		return (thread.getState() != Thread.State.TERMINATED);
	}
	
	/**
	 * It cleans up the executor, interrupts the working thread.
	 */
	public void shutdown()
	{
		thread.interrupt();
	}
	
	/**
	 * Static entry point of this app to demo the {@link SingleThreadSerialExecutor}.
	 * It creats a {@link SingleThreadSerialExecutor} and submits 3 tasks to it.
	 * The tasks simply prints out some data onto the default output.
	 * @param args Command line arguments. They are not used.
	 */
	public static void main(String[] args)
	{
		Executor executor = new SingleThreadSerialExecutor();
		for ( int i = 0; i < 3; i++ )
		{
			final int id = i;
			executor.execute( new Runnable()
			{
				@Override
				public void run()
				{
					System.out.println( "Task " + id );
				}
			});
		}
	}
}
