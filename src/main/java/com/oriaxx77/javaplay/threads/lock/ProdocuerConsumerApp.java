/**
 * 
 */
package com.oriaxx77.javaplay.threads.lock;

/**
 * Sample app to test a producer - consumer behaviour where
 * the producer and the consumer use a queue and this queue can only
 * contain one value.
 * The consumer waits till a value appears in the queue and read it,
 * the producer waits till the queue empty and then put a value into it.
 * @author BagyuraI
 */
public class ProdocuerConsumerApp
{

	/**
	 * Static entry point of the application.
	 * @param args Application arguments. Not used atm.
	 */
	public static void main(String[] args)
	{
		// create a Queue to read and write.
		Queue queue = new Queue();
		// Start producer thread
		new Producer( queue );
		// Start consumer thread
		new Consumer( queue );
		
	}

}
