/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools;

import java.util.Random;
import java.util.concurrent.Semaphore;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * Using a Semaphore as a mutex lock (synchronize the access to a given code).
 * It means that a code can only be accessed by 1 thread.
 * In the example we want to control the access of 
 * an account from which we want to withdraw some amount of money
 * periodically. There is a compare and set here (withdraw the money if we have
 * enough on the account) that should only be accessed by one thread at any given 
 * time.
 * @author BagyuraI
 *
 */
public class MutexWithSemaphore
{
	/**
	 * Account class. Use the {@link #withdraw(long)} method to get some
	 * money from it.
	 * The {@link #withdraw(long)}  is where we use the {@link Semaphore} to
	 * synchronize the access.
	 * @author BagyuraI
	 *
	 */
	public static class Account 
	{
		/**
		 * Actual balance.
		 */
		private long balance;

		/**
		 * The synchronization lock that controls the withdrawal.
		 * It only allows access to 1 thread at any given time to the {@link #withdraw(long)} method.
		 */
		private Semaphore mutex = new Semaphore(1);
		
		/**
		 * Constructor
		 * @param balance starting balance on the account
		 */
		public Account(long balance)
		{
			super();
			this.balance = balance;
		}
		
		/**
		 * Withdraws a given amount of money from the account.
		 * It allows the withdraw if the amount is less or equals to the balance.
		 * NOTE: exception handling is missing. 
		 * @param amount
		 */
		public void withdraw(long amount ) 
		{
			try
			{
				mutex.acquire();
				try
				{
					if ( balance >= amount )
						balance -= amount;
				}
				finally
				{
					mutex.release();
				}
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			}
			
		}
		/**
		 * Returns with the balance.
		 * @return balance of the account
		 */
		public long getBalance()
		{
			return balance;
		}
	}
	
	
	/**
	 * Starts this example.
	 * It periodically tries to withdraw some money from an account
	 * that is initialized with 100.
	 * @param args Command line arguments. It is not used atm.
	 */
	public static void main(String[] args)
	{
		final Account account = new Account( 100L );
		System.out.println( "Starting balance: 100" );
		for ( int i = 0; i < 10; i++ )
		{
			new Thread( new Runnable()
			{
				
				@Override
				public void run()
				{
					Random random = new Random();
					Sleeper.sleep( random.nextInt( 1000 ) );
					long amount = random.nextInt( 50 ) + 10;
					String threadName = Thread.currentThread().getName();
					Print.print( threadName + " tries to withdraw: " + amount);
					account.withdraw( amount );
					Print.print( threadName + " balance: " + account.getBalance());
					
				}
			}).start();
		}
	}
}
