/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.observer;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * 'Observer': defines an updating interface for objects that should be notified of changes in a subject
 * @author BagyuraI
 */
@GofExample(pattern="Observer", stereotype="Observer")
public interface IInvestor
{
	/**
	 * Update the investor with the new data of the stock.
	 * @param stock The changed stock data.
	 */
	public void update(Stock stock);
	

}
