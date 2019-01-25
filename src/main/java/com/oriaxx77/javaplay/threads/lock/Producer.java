/**
 * 
 */
package com.oriaxx77.javaplay.threads.lock;

import java.util.Random;

/**
 * Producer that puts a value into the queue continuously.
 * @author BagyuraI
 */
public class Producer implements Runnable
{
	/**
	 * Queue to put object into.
	 */
	private Queue queue;
	
	/**
	 * Create a producer that put objects into the supplied queue.
	 * Itt will start the queue thread
	 * @param queue Queue to put object into.
	 */
	public Producer( Queue queue)
	{
		this.queue = queue;
		new Thread( this, "Producer" ).start() ;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		int o = 0;
		Random rnd = new Random();
		while ( true )
		{
			try
			{
				for ( int i = 0 ; i < rnd.nextInt(3) + 1 ; i++ )
				{
					// Put objects into the queue.
					queue.put( new Integer( o++ ) );
				}
				
				// Put some randomness here.
				Thread.sleep( rnd.nextInt( 1000 ));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	
}
