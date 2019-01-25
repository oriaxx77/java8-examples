/**
 * 
 */
package com.oriaxx77.javaplay.threads.problems;

import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * Live lock example from http://stackoverflow.com/questions/1036364/good-example-of-livelock.
 * It demos a young couple who have only one spoon. They are both polite so the spoon owner will
 * pass the spoon to the other if the other is still hungry. There is a problem if both are still
 * hungry. The spoon will go around endlessly.
 * 
 * 
 * @author BagyuraI
 *
 */
public class LiveLockWithoutWaitAndNotify
{
	/**
	 * A simple spoon you can eat with.
	 * Use the {@link #use()} to eath with it
	 * @author BagyuraI
	 */
	private static class Spoon
	{
		/**
		 * Current owner of the spoon.
		 */
		private Diner owner;
		
		/**
		 * Creates a spoon with an owner.
		 * @param owner
		 */
		public Spoon( Diner owner ) { this.owner = owner; }
		
		/**
		 * The owner is using the spoon.
		 */
		public void use() 
		{ 
			System.out.println( owner.name + " is eating" );
		}
		
		/**
		 * Set a new owner.
		 * @param owner The new owner
		 */
		public void setOwner( Diner owner )
		{
			this.owner = owner;
		}
	}
	
	/**
	 * The diner who can use a spoon to eath with if it is hungry.
	 * @author BagyuraI
	 */
	private static class Diner
	{
		/**
		 * Name of the diner.
		 */
		private String name;
		/**
		 * Flag that can indicate if the diner is hungry or not.
		 */
		private boolean hungry;
		
		/**
		 * Creates a new, hungry diner.
		 * @param name Name of the diner.
		 */
		public Diner( String name )
		{ 
			this.name = name; 
			hungry = true;
		}
		
		/**
		 * Returns if the diner is hungry or not.
		 * @return TRUE if the diner is hungry.
		 */
		public boolean isHungry()
		{ 
			return hungry; 
		};
		
		/**
		 * A method to eath with a spouse.
		 * @param spoon A shared spoon
		 * @param spouse The spouse to eath with
		 */
		public void eathWith( Spoon spoon, Diner spouse )
		{
			while ( isHungry() )
			{
				// Do not have the spoon. Wait for the spouse.
				if ( spoon.owner != this )
				{
					Sleeper.sleep( 100 );
					System.out.println( name + " does not have the spoon. Wait for the spouse." );
					continue;
				}
				
				// I have the spoon but the spouse is hungry. I will pass the spoon
				if ( spouse.isHungry() )
				{
					spoon.setOwner( spouse );
					System.out.println( name + " has the spoon but the spouse is hungry. Passing the spoon to " + spouse.name );
					continue;
				}
				
				spoon.use();
				hungry = false;
				System.out.println( name + " is not hungry anymore" );
				spoon.setOwner( spouse );

			}
		}
	}
	
	
	
	/**
	 * Starts the Livelock example where we have two diners in throw threads
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		final Diner husband = new Diner( "Spongya Bob" );
		final Diner wife = new Diner( "Hello Kitty" );
		final Spoon spoon = new Spoon( husband );
		
		new Thread( new Runnable(){ 
			
			@Override
			public void run()
			{
				husband.eathWith(spoon, wife);
			}
			
		}).start();
		
		new Thread( new Runnable(){ 
			
			@Override
			public void run()
			{
				wife.eathWith(spoon, husband);
			}
			
		}).start();
		
	}

}
