/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * An ATM simultation where we have {@link ATMUser} objects as one shot threads
 * which withdraw some money from a shared {@link Account}.
 * This shared {@link Account} protects it's balance with an {@link AtomicReferenceFieldUpdater}. So it is thread-safe. 
 * It utilize the {@link AtomicReferenceFieldUpdater#compareAndSet(Object, Object, Object)} method in the {@link Account#withdraw(Long)}.
 * This way withdrawing some money is volatile and atomic
 * @author BagyuraI
 *
 */
public class ATMWithAtomicUpdater
{
	/**
	 * A thread-safe account that can be shared among users.
	 * It has a {@link Account#balance} that keeps track of the money on the account.
	 * It provides a {@link Account#withdraw(Long)} method to withdraw a certain amount from the
	 * account. The invariance is that a balance cannot be negative. The withdraw method utilize
	 * the  {@link AtomicReferenceFieldUpdater#compareAndSet(Object, Object, Object)} method to be 
	 * volatile and atomic.
	 * @author BagyuraI
	 *
	 */
	private static class Account
	{
		/**
		 * Balance of the account.
		 * NOTE: it must be volatile
		 */
		private volatile Long balance;
		
		/**
		 * Atomic updater for the {@link #balance}
		 */
		private static final AtomicReferenceFieldUpdater<Account, Long> balanceUpdater 
			= AtomicReferenceFieldUpdater.newUpdater( Account.class, Long.class, "balance" );
		
		/**
		 * Creates an account with it's initial balance.
		 * @param balance Initial balance
		 */
		public Account(long balance)
		{
			super();
			this.balance = balance;
		}

		/**
		 * Withdraw a certain amount from the
	     * account. The invariance is that a balance cannot be negative. The withdraw method utilize
	     * the  {@link AtomicReferenceFieldUpdater#compareAndSet(Object, Object, Object)} method to be 
	     * volatile and atomic. The balance cannot be negative. After the balance invariance chek it sleeps for
	     * 5000 millisec for demonstration purposes.
		 * @param balance the balance to set
		 * @exception NotEnoughMoneyException thrown when there is not enough money on the account to fulfill the withdraw request. 
		 */
		public void withdraw(Long amount) throws NotEnoughMoneyException
		{
			Long prevBalance = null, newBalance = null;
			do
			{
				prevBalance = balanceUpdater.get( this );
				if ( prevBalance - amount >= 0 )
				{
					// Sleep for some sec.
					// This way the fields can mess up the balance. 
					
					Sleeper.sleep( 5000 );			
					newBalance = prevBalance - amount;
					
				}
				else 
				{
					// Not enough money
					throw new NotEnoughMoneyException();
				}
			} 
			while ( !balanceUpdater.compareAndSet( this, prevBalance, newBalance) );
				
		}
		
		

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "Account [balance=" + balance + "]";
		}
		
	}
	
	/**
	 * Exception that indicates that there is not enough money on the account to fulfill the withdraw request.
	 * @author BagyuraI
	 */
	public static class NotEnoughMoneyException extends Exception
	{

		/**
		 * Serial version identifier.
		 */
		private static final long serialVersionUID = 1L;

	}
	
	/**
	 * An ATM user that uses an {@link Account} to withdraw 100 money.
	 * The {@link Account} is taking care of it's thread safety.
	 * @author BagyuraI
	 */
	private static class ATMUser implements Runnable
	{
		/** 
		 * Account to work with. It will be shared in the example. 
		 */
		private Account account; 
		
		/** 
		 * Creates an ATMUser
		 * @param account The account. It will be shared in the example.
		 */
		public ATMUser(Account account)
		{
			super();
			this.account = account;
		}


		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			Long amount = new Long(100);
			try
			{
				
				System.out.println( Thread.currentThread().getName() + " is trying to withdraw 100." );
				account.withdraw( amount );
				System.out.println( Thread.currentThread().getName() +  " withdrawn " + amount + ". Act balance: " + account.balance );
			}
			catch ( NotEnoughMoneyException e)
			{
				System.out.println( Thread.currentThread().getName() +  " cannot withdraw " + amount + ". Act balance: " + account.balance );
			}
				
		}
		
	}

	/**
	 * It creates an {@link Account} with 350 money, then starts 10 {@link ATMUser} threads.
	 * They shares the account and they try to withdraw 100 money. 
	 * @param args Command line arguments. They are not used at this moment.
	 */
	public static void main(String[] args)
	{
		Account acc = new Account( 350 );
		for ( int i = 0; i < 10; i++ )
			new Thread( new ATMUser( acc ) ).start();
	}
	
}
