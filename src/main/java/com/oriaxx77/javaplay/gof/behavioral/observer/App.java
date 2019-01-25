/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.observer;

/**
 * An application the demonstrates the Observer design pattern.
 * Observer design pattern: Define a one-to-many dependency between objects so that when one object changes state, 
 * 							all its dependents are notified and updated automatically.  
 * 
 * @author BagyuraI
 */
public class App
{

	/**
	 * Static entry point of the app.
	 * @param args Program arguments. Not used atm.
	 */
	public static void main(String[] args)
	{
		// Create the IBM stock.
		IBM ibm = new IBM( 2.22 );
		// Create some investors
		// and register them as observers.
		Investor jozsi = new Investor( "Jozsi" );
		Investor bela = new Investor( "Bela" );
		ibm.attach( jozsi );
		ibm.attach( bela );
		// Change the price of the IBM stock
		ibm.setPrice( 2.23 );
		// Jozsi no longer interested in the IBM stock.
		ibm.detach( jozsi );
		// Change the price of the IBM stock
		ibm.setPrice( 2.24 );

	}

}
