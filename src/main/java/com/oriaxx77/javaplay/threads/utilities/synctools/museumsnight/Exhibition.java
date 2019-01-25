package com.oriaxx77.javaplay.threads.utilities.synctools.museumsnight;

import java.util.concurrent.Semaphore;
import com.oriaxx77.javaplay.utility.Print;

/**
 * An art exhibition that can host only a given number 
 * of parties at the same time. This is solved by a {@link Semaphore}. 
 * @author BagyuraI
 */
public class Exhibition
{
	/**
	 * A Semapohore that permits entries.
	 * It keeps track of the visitors in the {@link #entry(Visitor)} method.
	 */
	private Semaphore mutex;
	
	/**
	 * Name of the exhibition.
	 */
	private String name;
	
	
	/**
	 * Creates an exhibition with the given name and the maximum number of visitors
	 * it can host at the same time.
	 * @param name Name of the exhibition
	 * @param visitorNo The maximum number of visitors it can host at the same time.
	 */
	public Exhibition( String name, int visitorNo )
	{
		super();
		this.name = name;
		this.mutex = new Semaphore( visitorNo );
	}


	/**
	 * Allows a visitor to enter the exhibition.
	 * It uses the {@link #mutex} to control the number of 
	 * visitors. If someone cannot have a permit then he/she should wait.
	 * @param visitor Visitor who wants to enter the exhibition.
	 * @throws InterruptedException the using thread is interrupted.
	 */
	public void entry(Visitor visitor) throws InterruptedException
	{
		Print.print( visitor.getName() + " is trying to enter into the " + getName() + 
								", available permits: " + mutex.availablePermits() );
		mutex.acquire();
		visitor.enjoy(  this );
		mutex.release();
	}

	



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Exhibition [name=" + name + "]";
	}
	
	/**
	 * Returns with the {@link #name}.
	 * @return The value of the {@link #name}
	 */
	public String getName()
	{
		return name;
	}
	

}
