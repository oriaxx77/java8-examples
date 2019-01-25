/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.observer;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * 'ConcreteObserver  ': 
 * - maintains a reference to a ConcreteSubject object
 * - stores state that should stay consistent with the subject's
 * - implements the Observer updating interface to keep its state consistent with the subject's
 * @author BagyuraI
 */
@GofExample(pattern="Observer", stereotype="ConcreteObserver")
public class Investor implements IInvestor
{
	
	/**
	 * Name of the investor.
	 */
	private String name;
	
	
	/**
	 * Creates an investor with the given name.
	 * @param name Name of the investor.
	 */
	public Investor(String name)
	{
		super();
		this.name = name;
	}



	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.designpatterns.behavioral.observer.IInvestor#update(com.oriaxx77.javaplay.designpatterns.behavioral.observer.Stock)
	 */
	@Override
	public void update(Stock stock)
	{
		System.out.printf("Notified %s of %s's " +
		        "change to %s\n", name, stock.getSymbol(), stock.getPrice() );
	}
	
	

}
