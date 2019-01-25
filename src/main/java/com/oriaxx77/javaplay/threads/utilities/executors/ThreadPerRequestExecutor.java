/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.executors;

import java.util.concurrent.Executor;

/**
 * An executor that starts a new thread for each submitted tasks.
 * @author BagyuraI
 */
public class ThreadPerRequestExecutor implements Executor
{

	/**
	 * Executes the submitted command in a new thread. 
	 * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
	 */
	@Override
	public void execute(Runnable command)
	{
		new Thread( command ).start();
	}

}
