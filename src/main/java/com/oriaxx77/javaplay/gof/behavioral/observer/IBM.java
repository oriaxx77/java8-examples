/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.observer;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * ConcreteSubject:
 * - stores state of interest to ConcreteObserver
 * - sends a notification to its observers when its state changes
 * @author BagyuraI
 */
@GofExample(pattern="Observer", stereotype="Concrete Subject")
public class IBM extends Stock
{
	/**
	 * Creates an IBM stock
	 * @param price Price of the stock.
	 */
	public IBM( double price)
	{
		super( "IBM" , price);
	}

}
