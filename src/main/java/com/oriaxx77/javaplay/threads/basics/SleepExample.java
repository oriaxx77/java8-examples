/**
 * 
 */
package com.oriaxx77.javaplay.threads.basics;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A simple program that runs several thread that says hello with
 * some sleeping.
 * @author BagyuraI
 */
public class SleepExample
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
			while ( !this.isInterrupted() )
			{
				Print.print( "Hello " + name + "!" );
				try
				{		
					Thread.sleep( interval * 1000 );
				}
				catch( InterruptedException ex )
				{
					ex.printStackTrace();
					this.interrupt();
				}
			}
						
		}
		
		
		
	}
	
	/**
	 * Static entry point of the app.
	 * @param args Program arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		String[] names = new String[]{ "István", "Péter", "Pimpa", "Picúr" };
		int[] intervals = new int[]{ 1 , 2 , 3 , 4};
		
		for ( int i = 0 ; i < names.length; i++ )
		{
			HelloThread helloThread = new HelloThread( names[i], intervals[i] );
			helloThread.start();
		}

	}

}
