/**
 * 
 */
package com.oriaxx77.javaplay.threads.basics;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A simple program that demonstrates the stoppage of a Thread.
 * @author BagyuraI
 */
public class StopExample
{
	
	/**
	 * Thread that says hello for someone in every X sec..
	 * @author BagyuraI
	 */
	private static class HelloThread extends Thread
	{
		/**
		 * Name to say hello to.
		 */
		private String name;
		/**
		 * Wait interval between the hellos in sec.
		 */
		private int interval;
		/**
		 * Flag that indicates if this should stop.
		 */
		private volatile boolean shouldStop;

		/**
		 * Creates a HelloThread
		 * @param name Name to say hello to.
		 */
		public HelloThread(String name, int interval)
		{
			super();
			this.name = name;
			this.interval = interval;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() 
		{
			try
			{
				while ( !shouldStop )
				{
					Print.print( "Hello " + name + "!" );
					Thread.sleep( interval * 1000 );
				}
			}
			catch( InterruptedException ex )
			{
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		
		/**
		 * Stops the execution of the current thread.
		 */
		public void stopIt()
		{
			this.shouldStop = true;
		}
		
	}
	
	/**
	 * A thread that starts a HelloThread and waits for it's termination.
	 * @author BagyuraI
	 */
	private static class MyThread extends Thread
	{
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() 
		{
			try
			{
				HelloThread helloThread = new HelloThread( "Istvan" , 1 );
				Print.print( "Starting the hello thread." );
				helloThread.start();
				Print.print( "Sleeping for 10 sec." );
				Thread.sleep( 60000 );
				helloThread.stopIt();
				helloThread = null;
			}
			catch( InterruptedException ex )
			{
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	
	/**
	 * Static entry point of the app.
	 * @param args Program arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		new MyThread().start();
	}

}
