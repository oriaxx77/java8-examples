/**
 * 
 */
package com.oriaxx77.javaplay.threads.problems;

import java.util.ArrayList;
import java.util.List;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * An example for the live lock with {@link Object#wait()} and {@link Object#notify()}
 * It demos when 2 cars arrives to a crossing at the same time. Both drivers are polite
 * and they are constantly waving to each other to pass through first. 
 * 
 * @author BagyuraI
 */
public class LiveLockWithWaithAndNotify
{
	
	
	/**
	 * The crossing. It keeps track of the waiting cars in it's {@link #cars} collection.
	 * You can add a waiting car through it's {@link #addCar(Car)} method.
	 * You can query the number of waiting cars with the {@link #carCount()} method.
	 * @author BagyuraI
	 *
	 */
	private static class Crossing
	{
		/**
		 * The waiting cars. They are waiting to pass through.
		 */
		private List<Car> cars = new ArrayList<Car>();
		
		/**
		 * The car is passing through.
		 * @param car The car to pass through.
		 */
		public synchronized void  passThrough( Car car )
		{
			Sleeper.sleep(1000);
			cars.remove( car );
			Print.print( "Passing throug: " + car );
		}
		
		
		/**
		 * Add a car to the waiting cars' list.
		 * @param car
		 */
		private void addCar( Car car )
		{
			cars.add( car );
		}
		
		/**
		 * Returns with the number of waiting cars.
		 * @return The number of waiting cars.
		 */
		private int carCount()
		{
			return cars.size();
		}
	}
	
	/**
	 * A driver. They are identified by their {@link #name}.
	 * They have a {@link #polite} flag
	 * @author BagyuraI
	 */
	private static class Driver
	{
		/** Name of the driver */
		private String name;
		/** Politeness flag */
		private boolean polite;
		
		/**
		 * Creates a Driver.
		 * @param name Name of the driver
		 * @param polite Politeness flag.
		 */
		public Driver(String name, boolean polite)
		{
			super();
			this.name = name;
			this.polite = polite;
		}
		
		/**
		 * @return the polite
		 */
		public boolean isPolite()
		{
			return polite;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "Driver [name=" + name + ", polite=" + polite + "]";
		}
		
		
		
	}
	
	/**
	 * A car with a {@link Driver} who want's to get through a {@link Crossing}
	 * @author BagyuraI
	 */
	private static class Car
	{
		/** The driver of the car */
		private Driver driver;
		
		
		/**
		 * Creates a Car
		 * @param driver Driver of  the car.
		 */
		public Car( Driver driver  )
		{
			this.driver = driver;
		}
		
		/**
		 * Passing through the given crossing.
		 * @param crossing Crossing to pass
		 */
		public void passThrough( Crossing crossing )
		{
			
			while ( true )
			{
				synchronized ( crossing )
				{
					System.out.println( this + " have the crossing." );
					if ( driver.isPolite() && crossing.carCount() > 1 )
					{
						System.out.println( this + " passing the right to cross" );
						try
						{
							crossing.notifyAll(); // notify the others to cross
							crossing.wait(); // go to sleep
						}
						catch (InterruptedException e)
						{
							Thread.currentThread().interrupt();
							throw new RuntimeException( e );
						}
					}
					else
					{
						System.out.println( this + " passing through" );
						crossing.passThrough( this );
						notifyAll();
						return;
					}
				}
				
			}
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "Car [driver=" + driver + "]";
		}
		
		
	}
	
	
	
	/**
	 * Demo that two polite drivers passing through a crossing. 
	 * @param args Command line arguments. They are not used at this moment.
	 */
	public static void main(String[] args)
	{
		
		final Car car1 = new Car( new Driver( "Spongya Bob", true ) );
		final Car car2 = new Car( new Driver( "Hello Kitty", true ) );
		
		final Crossing crossing = new Crossing();
		crossing.addCar( car1 );
		crossing.addCar( car2 );
		
		new Thread( new Runnable(){ 
			
			@Override
			public void run()
			{
				car1.passThrough( crossing );
			}
			
		}).start();
		
		new Thread( new Runnable(){ 
			
			@Override
			public void run()
			{
				car2.passThrough( crossing );
			}
			
		}).start();
		
	}

}
