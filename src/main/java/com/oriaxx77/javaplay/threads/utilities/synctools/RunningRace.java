/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;
import com.oriaxx77.javaplay.utility.Stopper;

/**
 * A running race where the start of the race and the end is controlled by
 * {@link CountDownLatch} objects.
 * @author BagyuraI
 *
 */
public class RunningRace
{
	/**
	 * A runner that uses the {@link #starterLatch} {@link CountDownLatch} to start and
	 * uses the {@link #runningEndedLatch} {@link CountDownLatch} to indicate that the running  
	 * ended. The duration of the running is modelled by a random sleep.
	 * It logs info on to the standard output.
	 * @author BagyuraI
	 */
	public static class Runner implements Runnable
	{
		/** Name of the runner */
		private String name;
		/** Latch to synchronize the start of the race. */
		private CountDownLatch starterLatch;
		/** Latch to indicate the end of the running. */
		private CountDownLatch runningEndedLatch;
		/** A stoper that is used to measure the duration of the running. */
		private Stopper stopper = new Stopper();
		/** Flag to indicate that the tape is breasted */
		private boolean tapeReached;
		
		/**
		 * Creates a new runner.
		 * @param name Name of the runner
		 * @param starterLatch Latch to synchronize the start of the race.
		 * @param runningEndedLatch Latch to indicate the end of the running.
		 */
		public Runner(String name, CountDownLatch starterLatch,
				CountDownLatch runningEndedLatch)
		{
			 this.name = name;
			 this.starterLatch = starterLatch;
			 this.runningEndedLatch = runningEndedLatch;
			 this.tapeReached = false;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() 
		{
			try
			{
				// Wait for the start
				starterLatch.await();
				
				// Running
				stopper.start();
				Print.print( name + " started.");
				Sleeper.sleep( new Random().nextInt( 10000 ));
				stopper.stop();
				tapeReached = true;
				Print.print( name + " breasted the tape in " + stopper.getElapsedMillis() + " ms." );
				
			}
			catch( InterruptedException e )
			{
				// Handle thread interruption.
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			finally
			{
				// Indicate the end of the running.
				runningEndedLatch.countDown();
			}
			
		}

		/**
		 * @return the tapeReached
		 */
		public boolean isTapeReached()
		{
			return tapeReached;
		}
		
	}
	
	/**
	 * Starts a race with 3 runner.
	 * @param args  Command line arguments. It is not used atm.
	 * @throws InterruptedException one of the synch. tools is interrupted.
	 */
	public static void main(String[] args) throws InterruptedException
	{
		// Create the runners and the control latches.
		int participantNo = 3;
		CountDownLatch starterLatch = new CountDownLatch( 1 );
		CountDownLatch raceEndedLatch = new CountDownLatch( participantNo );
		for ( int i = 0; i < 3; i++ )
		{
			new Thread( new Runner( "Runner_" + i, starterLatch, raceEndedLatch ) ).start();
		}
		// Start the race.
		Print.print( "Starting the running with " + participantNo + " runner. " );
		
		starterLatch.countDown();
		raceEndedLatch.await();
		
		Print.print( "The end." );
		
	}
}
