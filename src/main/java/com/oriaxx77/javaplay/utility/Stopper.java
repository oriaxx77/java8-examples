/**
 * 
 */
package com.oriaxx77.javaplay.utility;

/**
 * A basic stopper to watch the elapsed time
 * @author BagyuraI
 */
public class Stopper
{
	private long start;
	private long elapsed;
	
	
	public void start()
	{
		start = System.currentTimeMillis();
	}
	
	public void stop()
	{
		elapsed = System.currentTimeMillis() - start; 
	}
	
	public long getElapsedMillis()
	{
		return elapsed;
	}
}
