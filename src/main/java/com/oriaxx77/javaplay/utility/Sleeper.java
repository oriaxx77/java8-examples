/**
 * 
 */
package com.oriaxx77.javaplay.utility;

/**
 * Utlity to sleep in a thread correclty.
 * @author BagyuraI
 */
public class Sleeper
{
	/**
	 * Sleeps for a certain amount of millis.
	 * It will interrupt the current thread if the sleep
	 * is interrupted.
	 * @param millis Millis to sleep.
	 */
	public static void sleep( int millis )
	{
		new InterruptibleTaskExecutor().exec( () ->{ Thread.sleep(millis);} );
	}
}
