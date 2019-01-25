/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * An example for demonstarting a Trip with some participnts using a {@link CyclicBarrier}
 * The {@link Participant} object will get a list of meetings points 
 * where they need to wait for each other to have a party and start together again.
 * TODO: refactor this barrier to reset the CyclicBarrier all the time.
 * @author BagyuraI
 *
 */
public class TripDemo
{
	/**
	 * A participant.
	 * @author BagyuraI
	 *
	 */
	public static class Participant implements Runnable
	{
		/** Name of the participant. */
		private String name;
		/** List of meeting points */
		private List<CyclicBarrier> meetingPoints;
		/** The actual meeting point. */
		private CyclicBarrier actMeetingPoint;
		/** Randomness generator. */
		private Random rnd = new Random();
		
		
		
		/**
		 * Creates a participants.
		 * @param name
		 * @param meetingPoints
		 */
		public Participant(String name, List<CyclicBarrier> meetingPoints)
		{
			super();
			this.name = name;
			this.meetingPoints = meetingPoints;
		}


		/** 
		 * Visits the meeting points and wait for the others on each point.
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			for ( CyclicBarrier meetingPoint : meetingPoints )
			{
				actMeetingPoint = meetingPoint;
				gotoMeetingPoint();
				waitInMeetingPoint();
			}
			
		}
		
		/**
		 * Going to the next meeting point
		 */
		private void gotoMeetingPoint()
		{
			Sleeper.sleep( rnd.nextInt( 1000) );
			Print.print( name + " arrived to meeting point" );
		
		}
		
		/**
		 * Wait on the act. meeting point.
		 */
		private void waitInMeetingPoint()
		{
			try
			{
				actMeetingPoint.await();
			}
			catch (BrokenBarrierException | InterruptedException e)
			{
				Thread.currentThread().interrupt();
				throw new RuntimeException( e );
			}
		}
	}
	
	/**
	 * Starts this example. It creates 5 participants and 3 meeting points.
	 * The events are logged onto the default output.
	 * @param args Command line arguments. They are not used at this moment.
	 */
	public static void main(String[] args)
	{
		int participantNo = 5;
		
		List<CyclicBarrier> meetingPoints = IntStream.range(0, 3)
													 .mapToObj( i -> new CyclicBarrier( participantNo, 
															 							() -> { System.out.println("Let's party together");}) )
													 .collect( Collectors.toList() );
		
		IntStream.range(0, participantNo).mapToObj( i -> new Participant( "participant_"+i , meetingPoints) )
							 .map( p -> {return new Thread(p); } ).forEach( Thread::start );
		
	}
}
