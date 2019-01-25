package com.oriaxx77.javaplay.enums;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Enum where the constants have a specific method.
 * @author BagyuraI
 */
public enum ConstantSpecifiedMethodEnum
{
	/**
	 * Return with the CLASSPATH JVM environmental setting.
	 */
	CLASSPATH {
		/** 
		 * Returns with the CLASSPATH JVM environmental setting.
		 */
		@Override
		String getInfo()
		{
			return System.getenv( "CLASSPATH" );
		}
	},
	
	/**
	 *  Return with the JVM version.
	 */
	VERSION {

		/**
		 * Returns with the <i>java.version</i>.
		 */
		@Override
		String getInfo()
		{
			return System.getProperty( "java.version" );
		}
		
	};
	/**
	 * Get the infor about this enum.
	 * @return Info about this enum.
	 */
	abstract String getInfo();
	
	/**
	 * Static entry point of the app. It calls the {@link #getInfo()}
	 * of all specified enum int that class and prints them to the default output.
	 * @param args Command line arguments. They are not used atm.
	 */
	public static void main(String[] args)
	{
		for ( ConstantSpecifiedMethodEnum e : values() )
		{
			Print.print( e + " - " + e.getInfo() );
		}
	}
}
