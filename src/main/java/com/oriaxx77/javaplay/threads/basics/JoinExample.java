/**
 * 
 */
package com.oriaxx77.javaplay.threads.basics;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A simple program that demonstrates the usage of the {@link java.lang.Thread#join()}.
 * @author BagyuraI
 */
public class JoinExample
{
	
	/**
	 * Thread that says hello for someone in every X sec. for 10 times.
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
				for ( int i = 0; i < 10; i++ )
				{
					Print.print( "Hello " + name + "!" );
					Thread.sleep( interval * 1000 );
				}
			}
			catch( InterruptedException ex )
			{
				ex.printStackTrace();
				this.interrupt();
			}
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
				Print.print( "Joining to the hello thread." );
				helloThread.join();
				Print.print( "Join end." );
			}
			catch( InterruptedException ex )
			{
				ex.printStackTrace();
				this.interrupt();
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
