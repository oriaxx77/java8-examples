/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools.lotr;

import java.util.Random;
import java.util.concurrent.Phaser;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * A fellowship member.
 * It has a {@link #name} and an {@link #alive} flag.
 * Call the {@link #die()} method if you want to kill one.
 * Use the start() method to start the LOTR journey of this member in a thread.
 * @author BagyuraI
 *
 */
public class FellowshipMember
{
	/**
	 * Name of  the member.
	 */
	private final String name;
	/**
	 * Is alive flag of the member
	 */
	private volatile boolean alive;
	
	/**
	 * Creates a FellowshipMember.
	 * It is automatically alive
	 * @param name Name of the member.
	 */
	public FellowshipMember(String name)
	{
		super();
		this.name = name;
		alive = true;
	}
	
	/**
	 * It sets the {@link #alive} flag to false.
	 */
	public void die()
	{
		alive = false;
	}
	
	/**
	 * Returns with the {@link #alive} flag.
	 * @return {@code TRUE} if the member is alive.
	 */
	public boolean isAlive()
	{
		return alive;
	}
	
	/**
	 * Returns with the {@link #name}.
	 * @return Value of the {@link #name}.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Starts the LOTR journey of this member in a started thread.
	 * @param phaser contains the phases where is should meet his fellow buddies.
	 */
	public void start(final Phaser phaser)
	{
		phaser.register();
		new Thread( new Runnable()
		{
			@Override
			public void run()
			{
				Random rnd = new Random();
				while ( alive && !phaser.isTerminated() )
				{
					Sleeper.sleep( rnd.nextInt( 1000 )) ;
					if ( alive && rnd.nextBoolean() )
					{
						die();
						Print.print( name + " died before: " + LOTR.PHASES[phaser.getPhase()] );
						phaser.arriveAndDeregister();
					}
					else
					{
						if ( !phaser.isTerminated() )
						{
							Print.print( name + " arrived to: " + LOTR.PHASES[phaser.getPhase()] );
							phaser.arriveAndAwaitAdvance();
						}
					}
				}
				
			}
		}).start();
	}
}
