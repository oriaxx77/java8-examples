/**
 * 
 */
package com.oriaxx77.javaplay.threads.problems;

import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * Deadlock can occure if two threads want to acquire a series of locks in a different order
 * that ends up each holding a lock which the other is waiting for.
 * This is an example for this.
 * 
 * @author BagyuraI
 *
 */
public class DeadLock
{

	/**
	 * A thread that tries to use two locks.
	 * Use two of these thread with the same two objects (o1, o2) in
	 * different constructor argument order T1(o1,o2), T2(o2,o1)
	 * @author BagyuraI
	 */
	private static final class DeadThread implements Runnable
	{
		/** The first lock object */ 
		private Object lock1;
		/** The second lock object */
		private Object lock2;
		 
		/**
		 * Creates a DeadThread
		 * @param lock1 Lock 1
		 * @param lock2 Lock 2
		 */
		public DeadThread(Object lock1, Object lock2)
		{
			super();
			this.lock1 = lock1;
			this.lock2 = lock2;
		}



		/**It synch {@link #lock1} then sleep for 100 millis then
		 * tries to synch on {@link #lock2}.
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			synchronized (lock1)
			{
				print( "I have lock1 : " + lock1 );
				Sleeper.sleep( 1000 );
				print( "Trying to acquire lock2: "+ lock2 );
				synchronized ( lock2 )
				{
					print( "I have lock2 :" + lock2 );	
				}
			}
			
		}
		
		/**
		 * Prints a message prefixed with the current thread's name.
		 * @param msg
		 */
		private void print( String msg )
		{
			System.out.println( Thread.currentThread().getName() + " - " + msg );
		}
		
		
		
		
	}
	
	/**
	 * Static entry point for this example
	 * @param args Command line arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		Object lock1 = new Object();
		Object lock2 = new Object();
		
		new Thread( new DeadThread( lock1, lock2), "T1" ).start();
		new Thread( new DeadThread( lock2, lock1), "T2" ).start();

	}

}
