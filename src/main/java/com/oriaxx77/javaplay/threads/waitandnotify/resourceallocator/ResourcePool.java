/**
 * 
 */
package com.oriaxx77.javaplay.threads.waitandnotify.resourceallocator;

/**
 * A class that keep tracks of available resources.
 * It has 2 methods for working with the resources, one for allocating resources, one for 
 * releasing them. The {@link #allocateResource(int)} allocates resources. If there is not 
 * enough resources it will wait. The {@link #freeResource(int)} give the resources back
 * and notifies the threads that are waiting for resources.
 * @author BagyuraI
 */
public class ResourcePool
{
	/**
	 * Number of allocated resources.
	 */
	private int resourceCount = 0;
	/**
	 * Maximum number of available resources.
	 */
	private int resourceMax = 0;
	
	/**
	 * Create a pool of resources.
	 * @param resourcemax Maximum number of available resources.
	 */
	public ResourcePool( int resourcemax )
	{
		this.resourceMax = resourcemax;
	}
	
	/**
	 * Allocate a certain number of resources.
	 * If this number cannot be allocated it will wait until there will be 
	 * enough resources.
	 * @param numberof The number of needed resources.
	 */
	public synchronized void allocateResource( int numberof )
	{
		while ( !Thread.currentThread().isInterrupted() )
		{
			print( "tries to allocate " + numberof + " res." );
			// Check if there are enough resources.
			if ( resourceCount + numberof <= resourceMax )
			{
				// There are enough resources. 
				// Get them and leave the endless loop.
				print( "allocated " + numberof + " res." );
				resourceCount += numberof;
				break;
			}
			print( "couldn't allocate " + numberof + " res." );
			print( "waits." );
			// There are not enough resources.
			// Wait for them.
			try 
			{ 
				wait();
				print( "resumed" );
			}
			catch( InterruptedException ex ) 
			{ 
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			};
			
		}
	}
	
	/**
	 * Prints the msg onto the standard output with the prefix of the name
	 * of the current thread.
	 * @param msg Message to print. 
	 */
	private static void print( Object msg )
	{
		System.out.println( Thread.currentThread().getName() + " " + msg );
	}
	
	/**
	 * Gives the number of resources back.
	 */
	public synchronized void freeResource( int numberof )
	{
		print ( "freed " + numberof + " res." );
		// Put the resources back to the pool.
		resourceCount -= numberof;
		// Notify all waiting thread.
		notifyAll();
	}
	
	
}
