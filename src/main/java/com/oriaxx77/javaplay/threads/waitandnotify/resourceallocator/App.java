/**
 * 
 */
package com.oriaxx77.javaplay.threads.waitandnotify.resourceallocator;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Application that demonstrates the usage of the wait and notify methods
 * within synchronized blocks. It creates 4 shops that periodically allocates 
 * a random number (1-3) cars. 
 * @author BagyuraI
 */
public class App
{

	/**
	 * Static entry point of the app.
	 * @param args Application arguments. Not used atm.
	 */
	public static void main(String[] args)
	{
		ResourcePool carRental = new ResourcePool( 3 );
		
		IntStream.range(0, 4).mapToObj( i -> new ResourceAllocator( carRental ) )
								  .map( allocator -> new Thread( () -> {allocator.allocateContinously();}))			
								  .forEach( Thread::start );
		

	}

}
