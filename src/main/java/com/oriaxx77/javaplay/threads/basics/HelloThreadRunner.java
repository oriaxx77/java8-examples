/**
 * 
 */
package com.oriaxx77.javaplay.threads.basics;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A simple program that runs several thread that says hello.
 * It extends the {@link Thread}.
 * @author BagyuraI
 */
public class HelloThreadRunner
{

	/**
	 * Thread that says hello.
	 * @author BagyuraI
	 */
	private static class HelloThread extends Thread
	{
		/**
		 * Name to say hello to.
		 */
		private String name;

		/**
		 * Creates a HelloThread
		 * @param name Name to say hello to.
		 */
		public HelloThread(String name)
		{
			super();
			this.name = name;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run()
		{
			Print.print( "Hello " + name + "!" );
		}
		
	}
	
	/**
	 * Static entry point of the app.
	 * @param args Program arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		String[] names = new String[]{ "István", "Péter", "Pimpa", "Picúr" };
		for ( String name : names )
		{
			HelloThread helloThread = new HelloThread( name );
			helloThread.start();
		}

	}

}
