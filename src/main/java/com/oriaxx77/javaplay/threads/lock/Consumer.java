/**
 * 
 */
package com.oriaxx77.javaplay.threads.lock;

import java.util.Random;

/**
 * Consumer that continuously read from a queue.
 * It randolmy tries to read 1-3 items, then sleeps for a random 
 * @author BagyuraI
 */
public class Consumer implements Runnable
{

	/**
	 * The queue to read from.
	 */
	private Queue queue;
	
	/**
	 * Create a consumer that reads the supplied queue.
	 * This will start the consumer thread.
	 * @param queue Queue to read from.
	 */
	public Consumer( Queue queue )
	{
		this.queue = queue;
		// Start the consumer.
		new Thread( this, "Consumer" ).start();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		Random rnd = new Random();
		
		while ( true )
		{
			try
			{
				for ( int i = 0; i < rnd.nextInt( 3 )+1; i++ )
				{
					// Read the queue.
					queue.take();
				}
				
				// Put some randomness here.
				Thread.sleep( rnd.nextInt( 1000 ));
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}


	
}
