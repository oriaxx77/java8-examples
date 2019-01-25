/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools;

import java.util.Random;
import java.util.concurrent.Phaser;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * A {@link Phaser} example. It demos 3 hiker on their journey.
 * They start together and they wait for each other on 3 stations.
 * Then they start from there together again.
 * @author BagyuraI
 */
public class HikingWithPhaser 
{
	/**
	 * Static entry ponint of the app.
	 * @param args Command line arguments. It is not used at this moment.
	 */
	public static void main(String[] args) 
	{
		// Creating the phaser then registering 1 party (the main thread)
		final Phaser phaser = new Phaser() {
			protected boolean onAdvance(int phase, int parties) {
				if ( phase == 0 )
					Print.print( "Start." );
				else
					Print.print( "Everyone arrived. Phase: "+ phase + ", parties: " + parties );
				
				return parties == 0; // If true, the phaser should terminate
			}
		};
		phaser.register();
		
		// Starting 3 threads.
		// Each thread will add one waiting party
		// to the phaser. We have 3 threads so the waiting party no.
		// would be 4 (main thread + 3 worker threads)
		// The threads will wait to start their job 
		// until all parties arrive at the same point (main thread +
		// 3 worker threads).
		for ( int i = 0; i < 3; i++ )
		{
			// Add a waiting party to the phaser.
			phaser.register();
			// Create a thread
			new Thread( new Runnable() {
				
				@Override
				public void run() {
					// First time wait for all the parties to arrive here 
					// (party no=4. Main thread + 3 worker threads)
					// any other time wait for all parties to arrive here (party no=3, 3 workers threads because
					// the main thread deregister itself.
					for ( int i = 0; i < 4; i++)
					{
						phaser.arriveAndAwaitAdvance();
						Print.print( Thread.currentThread().getName() + " is hiking." );
						Sleeper.sleep( new Random().nextInt( 2000 ) ); 
					}
					
				}
			}).start();
		}
		
		// Arrive at the meeting point and deregister.
		// The first execution starts here.
		phaser.arriveAndDeregister();
	}
}
