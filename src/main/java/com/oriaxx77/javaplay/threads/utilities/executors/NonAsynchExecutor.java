/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.executors;

import java.util.concurrent.Executor;

/**
 * A very simple non-asynchronous {@link Executor} implementation.
 * The {@link #execute(Runnable)} method simply calls the passed 
 * command's run() method. The {@link #main(String[])} method 
 * demoes the usage of this class with executing an anonymous
 * 'Hello World' printer {@link Runnable}. Noteice that there is no new thread here, jut the main thread.
 * @author BagyuraI

 */
public class NonAsynchExecutor implements Executor
{

	/**
	 * It simply calls the run method of the provided command. 
	 * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
	 */
	@Override
	public void execute(Runnable command)
	{
		command.run();
	}

	/**
	 * Demo the usage of this class with executing an anonymous
	 * Hello World printer {@link Runnable} that prints our the <i>Hello World!</i> sentence.
	 * @param args
	 */
	public static void main(String[] args)
	{
		Executor executor = new NonAsynchExecutor();
		executor.execute( new Runnable() {
			@Override
			public void run() { System.out.println( "Hello World!" ); }
		});
	}
}
