/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Example app. for the usage of the {@link BlockingQueue}.
 * It starts two thread. One thread will put things into the queue, the other take tha things 
 * out. the {@link BlockingQueue#put(Object)} and {@link BlockingQueue#take()} operations 
 * block the thread until they can be performed.
 * 
 * @author BagyuraI
 */
public class BlockingQueueExample
{

	/**
	 * Thread that put things into the queue.
	 * If there is not enough space in the queue it will wait.
	 * See the {@link BlockingQueue#put(Object)} method.
	 * @author BagyuraI
	 */
	private static class Producer implements Runnable
	{
		
		/**
		 * The queue.
		 */
		private BlockingQueue<String> queue;
		/**
		 * Name of the producer.
		 */
		private String name;
		/**
		 * Objects to put
		 */
		private String[] objectsToPut;
		
		/**
		 * Creates a producer
		 * @param name Name of the producer.
		 * @param queue Queue to use.
		 */
		public Producer(String name, BlockingQueue<String> queue, String[] objectsToPut )
		{
			super();
			this.name = name;
			this.queue = queue;
			this.objectsToPut = objectsToPut;
		}



		/**
		 * It periodically puts object into the {@link #queue}. 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			for ( String o : objectsToPut )
			{
				try
				{
					// Put an object into the queue.
					// If there is no space it will wait.
					queue.put( o );
					System.out.println( name + " put " + o  );
					
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	
	/**
	 * Thread that take things from the queue.
	 * If there is no object to take in the queue it will wait.
	 * See the {@link BlockingQueue#take()} method.
	 * @author BagyuraI
	 */
	private static class Consumer implements Runnable
	{
		/**
		 * The queue.
		 */
		private BlockingQueue<String> queue;
		
		/**
		 * Name of the consumer.
		 */
		private String name;
		
		/**
		 * Creates a consumer.
		 * @param name Name of the consumer.
		 * @param queue Queue to use.
		 */
		public Consumer(String name, BlockingQueue<String> queue)
		{
			super();
			this.name = name;
			this.queue = queue;
		}



		/** 
		 * It periodically takes objects from the {@link #queue}.
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			while (true )
			{
				try
				{
					// Take an object from the queue.
					// If there is no space it will wait.
					String str = queue.take();
					System.out.println( name + " took " + str  );
					
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	/**
	 * Starts the app.
	 * @param args Program arguments. They are not used atm.
	 */
	public static void main(String[] args)
	{
		// Create a queue with 3 space (capacity)
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>( 3 );
		
		
		// Create two producer and one consumer
		new Thread( new Producer( "Producer1", queue, new String[]{ "a" , "x" } ) ).start();
		new Thread( new Producer( "Producer2", queue,  new String[]{ "c" , "xx" , "z"}  ) ).start();
		new Thread( new Consumer( "Consumer1", queue ) ).start();
	}

}
