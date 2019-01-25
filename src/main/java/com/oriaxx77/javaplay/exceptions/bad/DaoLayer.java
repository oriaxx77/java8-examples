/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.bad;

/**
 * A class that enables the DB layer to work.
 * It is singleton.
 * @author BagyuraI
 */
public class DaoLayer
{
	/**
	 * Flag that enable/disable the DB access operations.
	 */
	private static boolean enabled = true;

	/**
	 * Return true if the DB layer is enabled.
	 * @return the enabled
	 */
	public static boolean enabled()
	{
		return enabled == true;
	}
	
	/**
	 * Return true if the DB layer is disabled.
	 * @return the enabled
	 */
	public static boolean disabled()
	{
		return enabled == false;
	}
	
	/**
	 * Enable the DB layer.
	 */
	public static void enable()
	{
		enabled = true;
	}
	
	/**
	 * Disable the DB layer.
	 */
	public static void disable()
	{
		enabled = false;
	}
	
}
