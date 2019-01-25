package com.oriaxx77.javaplay.exceptions.good;

/**
 * A Bank Service tied to an account.
 * This is just for demonstration for good exception handling.
 * This service doesn't deal with synchronization issues for the 
 * sake of simplicity.
 * @author BagyuraI
 */
public class BankService
{
	/**
	 * The account the service works on.
	 */
	private Account account;
	
	/**
	 * Creates a bank service that can work on an account
	 * @param account
	 */
	public BankService( Account account )
	{
		this.account = account;
	}
	
	/**
	 * Method to withdraw the specified amount from the account.
	 * Please note: Checked exceptions are thrown for special business logic. All
	 * fault related exception is runtime. 
	 * 
	 * @param amount Amount to withdraw
	 * @throws InsufficientFundException Thrown when there is not enough cash on the account
	 */
	public void withdraw( long amount ) throws InsufficientFundException
	{
		if ( account.getBalance() >= amount )
		{
			// Normal course of event.
			// Please notice that all DB operations use unchecked exceptions.
			long newBalance = account.getBalance() - amount;
			account.setBalance( newBalance );
			BankDao bankDao = new BankDao();
			bankDao.save( account );
		} 
		else
		{
			// Special case.
			// There is not cover on the account for the transaction.
			// Let the client handle this.
			long missingAmount = amount - account.getBalance();
			throw new InsufficientFundException( missingAmount );
		}
	}
	
}
