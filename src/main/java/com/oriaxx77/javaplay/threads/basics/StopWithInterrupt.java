/**
 * 
 */
package com.oriaxx77.javaplay.threads.basics;

import java.util.Date;

/**
 * Example for stopping the thread with interrupt
 * It instantiates a thread that says Hi There until it is interrupted.
 * This thread is interrupted after 10 millis.
 * @author BagyuraI
 */
public class StopWithInterrupt 
{

	/**
	 * A thread that runs till it is interrupted.
	 * It queries the {@link #isInterrupted()} flag.
	 * @author BagyuraI
	 */
	private static class MyThread extends Thread
	{

		@Override
		public void run() 
		{
			Date start = new Date();
			while ( !isInterrupted() )
				System.out.println( "Hi there" );;
				
			Date end = new Date();
			System.out.println( "MyThread stopped after: " + 
							(end.getTime() - start.getTime())+ " ms");
		}
		
	}
	
	/**
	 * Example for stopping the thread with interrupt
	 * It instantiates a thread that says Hi There until it is interrupted.
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		MyThread thread = new MyThread();
		thread.start();
		Thread.sleep( 10 );
		thread.interrupt();

	}

}
