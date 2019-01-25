/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.bad;

/**
 * An exception that indicates that there is not insufficient money on
 * an account for a transaction. The {@link #missingAmount} can be used to indicate the
 * missing amount.
 * @author BagyuraI
 *
 */
public class InsufficientFundException extends RuntimeException
{
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Indicates the missing amount for the transaction.
	 */
	private long missingAmount;

	/**
	 * Constructor.
	 * @param missingAmount The missing amount for the transaction.
	 */
	public InsufficientFundException(long missingAmount)
	{
		super();
		this.missingAmount = missingAmount;
	}

	/**
	 * @return the missingAmount
	 */
	public long getMissingAmount()
	{
		return missingAmount;
	}
	
	
}
