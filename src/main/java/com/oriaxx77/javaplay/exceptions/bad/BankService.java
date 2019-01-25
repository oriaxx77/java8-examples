package com.oriaxx77.javaplay.exceptions.bad;

import java.sql.SQLException;

/**
 * A Bank Service tied to an account.
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
	 * Please note: InsufficientFundException is runtime (unchecked). Clients are not
	 * forced to dealt with it. It throws an SQLException. Clients need to deal with it.
	 * But it is just an extra code for them because they cannot recover from this.
	 * fault related exception is runtime. 
	 * 
	 * @param amount Amount to withdraw
	 * @throws InsufficientFundException Thrown when there is not enough cash on the account
	 * @throws SQLException Thrown when there is an SQL Error.
	 */
	public void withdraw( long amount ) throws InsufficientFundException, SQLException
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
	
	/**
	 * This method should be used to transfer money from one account to another.
	 * We should use the InsufficientFundException here (as checked).
     * Instead we return with a status. Clients are not forced to check the 
	 * tatus and deal with it. :(
	 * @param amount
	 * @param target
	 * @return
	 */
	public TransferStatus transfer( long amount, Account target )
	{
		return null;
	}
	
}
