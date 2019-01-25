/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * An example for using the {@link Exchanger} syncronization utility. An
 * {@link Exchanger} is an object that can be used between two threads to swap
 * objects. In the example a magician is using a magic hat. He places an object
 * into the magic hat. It disappears and a new object appears.
 * 
 * @author BagyuraI
 * 
 */
public class Cantrip
{

	/**
	 * Static entry point of that program. A magician is using a magic hat for swapping objects.
	 * @param args Command line args. It is not used atm.
	 */
	public static void main(String args[])
	{
		Exchanger<Object> exchanger = new Exchanger<Object>();
		Magician magician = new Magician(exchanger);
		new Thread(magician).start();
		MagicHat magicHat = new MagicHat(exchanger);
		new Thread(magicHat).start();
	}
}

/**
 * An object exchange that can swap objects with other exchangers.
 * @author BagyuraI
 *
 */
class ItemExchanger implements Runnable
{
	/** Exchanger used for swapping */
	private Exchanger<Object> exchanger;
	/** Random number generator to give the program some randomness */
	private Random rnd = new Random();

	/**
	 * Creates a magician.
	 * @param exchanger Exchanger used for swapping.
	 */
	ItemExchanger(Exchanger<Object> exchanger)
	{
		this.exchanger = exchanger;
	}

	/**
	 * @see Runnable#run()
	 * It swapt objects through the {@link #exchanger} then sleeps for a 0 - 999 randomly specified milliseconds.
	 */
	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				Object o = exchanger.exchange(new Object());
				System.out.println(getClass().getSimpleName() + " got " + o);
				Thread.sleep(rnd.nextInt(1000));
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				throw new RuntimeException();
			}
		}

	}
}

/**
 * A magician that can change any kind of objects with another one (swapping them in reality).
 * @author BagyuraI
 */
class Magician extends ItemExchanger
{

	/**
	 * Creates a magician.
	 * @param exchanger Exchanger to swap objects.
	 */
	public Magician(Exchanger<Object> exchanger)
	{
		super(exchanger);
	}
	
}

/**
 * A magic hat that can replace an object with another one.
 * @author BagyuraI
 */
class MagicHat extends ItemExchanger
{

	/**
	 * Creates a magic hat.
	 * @param exchanger Exchanger to swap objects.
	 */
	public MagicHat(Exchanger<Object> exchanger)
	{
		super(exchanger);
	}
	
}