/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.bad;

import java.sql.SQLException;

/**
 * Application that demonstrates the bad way of exception handling. 
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
		catch( SQLException sqlE )
		{
			// Why do we need to deal with that?
			// It is something that will not add functionality to our app. Just unnecessary code.
			System.out.println( "Recover from that. More code to read :) Or throw it and you client needs to deal with the extra code :D" );
		}
		// I missed the InsufficientFunException because it is Unchecked :(
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

}
