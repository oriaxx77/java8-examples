/**
 * 
 */
package com.oriaxx77.javaplay.threads.waitandnotify.resourceallocator;

import java.util.Random;
import com.oriaxx77.javaplay.utility.Sleeper;


/**
 * A resource allocatos.
 * It periodically tries to allocate a random number of resources (1-3).
 * @author BagyuraI
 */
public class ResourceAllocator
{
	
	/**
	 * A resources to allocate resources from.
	 */
	private ResourcePool resourcePool;
	/**
	 * Random number generator to generate the number of needed resources.
	 */
	private Random random = new Random();
	
	
	/**
	 * Create a resource allocator thread
	 * @param resourcePool A resourcePool  to allocate resources from.
	 */
	public ResourceAllocator( ResourcePool resourcePool)
	{
		this.resourcePool = resourcePool;
	}

	/**
	 * Allocate resources continuously.
	 */
	public void allocateContinously()
	{
		while ( !Thread.currentThread().isInterrupted() )
		{
			// The number of needed resources
			int neededResourceCount = 1 + random.nextInt( 3 );
			// Allocate the resources.
			resourcePool.allocateResource( neededResourceCount );
			// Sleep 2 sec
			Sleeper.sleep( 2000 );
			// Free the resources
			resourcePool.freeResource( neededResourceCount );
			// Sleep 2 sec
			Sleeper.sleep( 2000 );
		}
	}
		
	
}
