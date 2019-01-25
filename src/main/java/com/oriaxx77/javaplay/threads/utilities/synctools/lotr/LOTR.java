/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools.lotr;

import java.util.concurrent.Phaser;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Lord of the rings example for demonstrating the usage of a {@link Phaser}.
 * In this example the Fellowship of the Ring starts to Mordor.
 * There are 3 stages where they wait for each other. This is the beginning of the 3 books.
 * During their adventures they have a (very big) chance to die though.
 * @author BagyuraI
 */
public class LOTR
{
	/**
	 * Phases
	 */
	public static final String[] PHASES = new String[]{ "Fellowship of the ring", "The two towers", "The return of the king", "The End"  };
	
	/** 
	 * The fellowship of the ring.
	 */
	private Fellowship fellowship;
	
	/**
	 * Creates a Lord of the rings.
	 */
	public LOTR()
	{
		super();
		fellowship = new Fellowship();
	}
	
	
	/**
	 * Starts the LOTR.
	 */
	public void start()
	{
		// Phase to synchronize the phases.
		Phaser phaser = new Phaser(1){

			
			
			
			/* (non-Javadoc)
			 * @see java.util.concurrent.Phaser#onAdvance(int, int)
			 */
			@Override
			protected boolean onAdvance(int phase, int registeredParties)
			{
				if ( phase == PHASES.length-1 || !fellowship.isLive() )
				{
					Print.print( PHASES[ phase ] + ". Do we have members alive? " + fellowship.isLive() );
					return true;
				}
				
				Print.print( PHASES[ phase ] + ". Do we have members alive? " + fellowship.isLive() );
				return false;
			}
			
		};
		
		for ( FellowshipMember member: fellowship.getMembers() )
		{
			member.start( phaser );
		}
		
	
		phaser.arriveAndDeregister(); // Do not wait for others. Continue running but decrease the party number.
	}
	
	
	
	public static void main(String[] args)
	{
		new LOTR().start();
	}
}
