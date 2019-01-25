/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.good;

/**
 * App. that demonstrates the right way of exception handling. 
 * 
 * @author BagyuraI
 */
public class App
{
	/**
	 * Withdraw some money from an account.
	 * @param bankService Bank service that can access an account.
	 * @param amount Amount to withdraw.
	 */
	private static void withdraw( BankService bankService , Long amount )
	{
		try
		{
			// Normal course of event
			bankService.withdraw( amount );
			System.out.println( "Withdrawed: " + amount );
		} 
		catch (InsufficientFundException e)
		{
			// Not enough money on the account to cover the transaction
			System.out.println( "Missing amount: " + e.getMissingAmount());
		}
	}

	
	
	/**
	 * Static entry point of the app.
	 * @param args Arguments. They are not used atm.
	 */
	public static void main(String[] args)
	{
		// Create an account and 
		// a bank service that can access / allow to manipulate it.
		Account account = new Account( 150L );
		BankService bankService = new BankService( account );
		
		
		// Do some withdraw.
		try
		{
			// We do not have a DB so disable the DAO layer to 
			// avoid DAO related Runtime exceptions.
			DaoLayer.disable();
			withdraw( bankService, 50L );
			withdraw( bankService, 150L );
			// We do not have a DB so enable the DAO layer to 
			// avoid DAO related Runtime exceptions.
			DaoLayer.enable();
			withdraw( bankService, 50L );
		} 
		catch ( RuntimeException ex)
		{
			
			// Unintended behaviour
			// 1) Log the error for further investiagation
			ex.printStackTrace();
			// 2) Nice error message to the user
			System.out.println( "Out of order. Please contact the system administrator." );
		}
		
		
		
	}

}
