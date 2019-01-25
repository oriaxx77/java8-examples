/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.bad;

/**
 * A Bank Account.
 * @author BagyuraI
 */
public class Account
{
	/**
	 * Balance of the account
	 */
	private Long balance;

	/**
	 * Creates a new Account with the initial balance.
	 * @param balance The initial balance of the account.
	 */
	public Account(Long balance)
	{
		super();
		this.balance = balance;
	}

	/**
	 * @return the balance
	 */
	public Long getBalance()
	{
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Long balance)
	{
		this.balance = balance;
	}
	
	
	
	
}
