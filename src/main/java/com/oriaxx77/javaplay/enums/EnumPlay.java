/**
 * 
 */
package com.oriaxx77.javaplay.enums;



/**
 * Enum demonstration. It demoes:
 * - {@link Enum} super class.
 * - switch statement with enums.
 * - for each with enums.
 * @author BagyuraI
 */
public class EnumPlay
{

	/**
	 * Static entry point of the app.
	 * It demoes:
	 * - {@link Enum} super class.
	 * - switch statement with enums.
	 * - for each with enums.
	 * @param args Command line args. Not used.
	 */
	public static void main(String[] args)
	{
		
		// Iterating through the colors
		for ( Color c : Color.values() )
			System.out.println( c );
	
		// Usage with switch
		Color myColor = Color.BLUE;
		switch (myColor)
		{
			case BLUE: 
				System.out.println( "My color is blue." );
				break;
			default:
				break;
		}
		
		// String to enum
		Color color = Color.valueOf( "BLUE" );
		System.out.println( color );
		
	}

}
