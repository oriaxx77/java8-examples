/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

import com.oriaxx77.javaplay.gof.GofExample;


/**
 * 'Subject' that knows its observers. Any number of Observer objects may observe a subject
 * provides an interface for attaching and detaching Observer objects. 
 * @author BagyuraI
 */
@GofExample(pattern="Observer", stereotype="Subject")
public class Stock
{
	/**
	 * Stock symbol.
	 */
	private String symbol;
	/**
	 * Stock price.
	 */
	private double price;
	/**
	 * Investors to be notified about the price changes.
	 */
	private List<IInvestor> investors = new ArrayList<IInvestor>();
	
	
	
	
	/**
	 * Creates a stock 
	 * @param symbol Stock symbol.
	 * @param price Stock price.
	 */
	public Stock(String symbol, double price)
	{
		super();
		this.symbol = symbol;
		this.price = price;
	}

	
	
	/**
	 * Add an investor to be notified
	 * @param investor Investor to be notified
	 */
	public void attach( IInvestor investor )
	{
		investors.add( investor );
	}
	
	/**
	 * Remove an observer from the notification list.
	 * @param investor Investor to be removed
	 */
	public void detach( IInvestor investor )
	{
		investors.remove( investor );
	}
	
	/**
	 * Notifies all observers about the status change.
	 */
	public void notifyInvestors()
	{
		for ( IInvestor investor: investors )
			investor.update( this );
	}



	/**
	 * Getter of the price
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}



	/**
	 * Set the value of the price to the value of price
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
		notifyInvestors();
	}



	/**
	 * Getter of the symbol
	 * @return the symbol
	 */
	public String getSymbol()
	{
		return symbol;
	}
	
	
}
