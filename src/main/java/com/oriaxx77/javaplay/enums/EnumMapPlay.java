package com.oriaxx77.javaplay.enums;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.util.EnumMap;
import java.util.Map;

/**
 * Example usage of {@link EnumMap}.
 * @author BagyuraI
 */
public class EnumMapPlay
{
	/**
	 * Represents a command.
	 * @author BagyuraI
	 *
	 */
	static interface Command
	{
		/**
		 * The logic, executed by the command.
		 */
		void action();
	}
	
	/**
	 * Static entry point of the app.
	 * It demoes an alarm point - action {@link EnumMap} usage where an intruder on
	 * the alarm point will trigger an action
	 * @param args Command line args. Not used.
	 */
	public static void main(String[] args)
	{
		// Define an empty map
		EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints,Command>( AlarmPoints.class );
		
		
		// Put some alert - action into the map.
		em.put( AlarmPoints.KITCHEN, new Command()
		{
			@Override
			public void action()
			{
				print( "The kitchen is in fire. Call 105!");
			}
		});
		
		em.put( AlarmPoints.BATHROOM , new Command()
		{
			@Override
			public void action()
			{
				print( "Overflow. Call the housekeeper!" );
			}
		});
		
		// Trigger the actions.
		for ( Map.Entry<AlarmPoints,Command> e : em.entrySet() )
		{
			print( "Trouble in " + e.getKey() + ". Executing the command!" );
			e.getValue().action();
			
		}
		
	}

}
