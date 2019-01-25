/**
 * 
 */
package com.oriaxx77.javaplay.threads.synch;
import static com.oriaxx77.javaplay.utility.Sleeper.sleep;
/**
 * A banking example without synchronization.
 * Two person tries to use the same account. The result is disastrous for the bank
 * (and for the programmer).
 * @author BagyuraI
 */
public class BankingExampleWithoutSynch
{
	
	
	/**
	 * Automated teller machine
	 */
	private static class ATM
	{
		/**
		 * Account to withdraw cash from.
		 */
		private Account acc;
		/**
		 * Sleep in millis we wait between the assertion of eligibility of the withdraw attempt and
		 * the actual withdraw. This helps us to show the possible synchronization fault in our program.
		 */
		private int sleepMillis;
		
		/**
		 * Creates an ATM.
		 * @param acc Account to withdraw cash from.
		 * @param sleepMillis Sleep in millis we wait between the assertion of eligibility of the withdraw attempt and
		 * 					  the actual withdraw. This helps us to show the possible synchronization fault in our program.
		 */
		public ATM( Account acc, int sleepMillis )
		{
			this.acc = acc;
			this.sleepMillis = sleepMillis;
		}
		
		/**
		 * Withdraw cash from the atm.
		 * @param amount Amount to withdraw.
		 */
		public void withdraw( float amount )
		{
			try 
			{ 
				acc.deduct(amount,sleepMillis);
				dispense( amount );
			}
			catch( InvalidAmountException ex )
			{
				log( "Cannot deduct: " + amount );
			}
				
			printAccountTotal(acc.getTotal());
		}
		
		/**
		 * Dispense the cash.
		 * @param amount Amount to dispense
		 */
		private void dispense( float amount )
		{
			log( " dispense: " + amount );
		}
		
		/**
		 * Print the total amount remained on the account.
		 * @param total total amount of money on the account.
		 */
		private void printAccountTotal( float total )
		{
			log( " total: " + total );
		}
	}
	
	
	/**
	 * Account
	 * @author BagyuraI
	 */
	private static class Account
	{
		/**
		 * The total amount of money on the account.
		 */
		private float total;
		
		/**
		 * Creates an account.
		 * @param total The total amount of money on the account.
		 */
		public Account( float total)
		{
			if ( total < 0 )
				throw new IllegalArgumentException( "Account total cannot be negative." );
			this.total = total;
		}
		
		/**
		 * Deduct some money from the account.
		 * @param amount amount of money to deduct
		 * @param sleepMillis Sleep in millis we wait between the assertion of eligibility of withdraw and
		 * 					  the actual withdraw. This helps us to show the possible synchronization fault of our program.
		 * @return {@code TRUE} the deduct happened.
		 */
		public void deduct( float amount, int sleepMillis) throws InvalidAmountException
		{
			log( " tries to deduct " + amount  );
			
			if ( amount > total )
				throw new InvalidAmountException( "Not enough cash on the account." );
				
			log( " eligible to deduct " +amount  );
			log( " sleeping " + sleepMillis  );
			sleep( sleepMillis );
			total -= amount;
			log( " deducted " + amount  );
		}
		
		/**
		 * @return the total
		 */
		public float getTotal()
		{
			return total;
		}

	}

	
	
	/**
	 * Logs the given message on to the default out channel
	 * prefixed with the current thread name. 
	 * @param msg message to print out.
	 */
	private static void log( Object msg )
	{
		System.out.println( Thread.currentThread().getName() + " " + msg.toString()  );
	}
	
	/**
	 * A thread that can withdraw money from an account.
	 */
	private static class WithdrawThread extends Thread
	{
		/**
		 * Account to withdraw from.
		 */
		private Account acc;
		/**
		 * Amount to withdraw.
		 */
		private int amount;
		/**
		 * Sleep in millis we wait between the assertion of eligibility of withdraw and
		 * the actual withdraw. This helps us to show the possible synchronization fault of our program.
		 */
		private int sleepMillis;
		
		/**
		 * Creates a WithdrawThread
		 * @param acc ccount to withdraw from.
		 * @param name Name of the thread. It is used for debugging.
		 * @param amount Amount to withdraw.
		 * @param sleepMillis Sleep in millis we wait between the assertion of eligibility of withdraw and
		 * 					  the actual withdraw. This helps us to show the possible synchronization fault of our program.
		 */
		public WithdrawThread( Account acc, String name, int amount, int sleepMillis)
		{
			super( name );
			if ( amount <= 0 )
				throw new IllegalArgumentException( "The withdrawal amount must be greater than 0." );
			if ( sleepMillis <= 0 )
				throw new IllegalArgumentException( "Sleep interval of the withdrawal must be greater than 0." );
			this.acc = acc;
			this.amount = amount;
			this.sleepMillis = sleepMillis;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run()
		{
			ATM atm = new ATM( acc, sleepMillis );
			atm.withdraw( amount );
		}
		
	}
	
	/**
	 * Static entry point of the app.
	 * @param args Application arguments. Not used atm.
	 */
	public static void main(String[] args)
	{
		Account sharedAccount = new Account( 60 );
		
		WithdrawThread withdraw1 = new WithdrawThread(sharedAccount, "Jozsi", 45 , 1000 );
		withdraw1.start();
		
		WithdrawThread withdraw2 = new WithdrawThread(sharedAccount, "Gabor", 45 , 1 );
		withdraw2.start();
	}

}
