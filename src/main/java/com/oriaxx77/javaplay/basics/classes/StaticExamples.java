
/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

import static com.oriaxx77.javaplay.utility.Print.*;

/**
 * Example for using the <i>static</i> keyword 
 * in various context.
 * @author BagyuraI
 */
public class StaticExamples 
{
	/**
	 * A static member variable.
	 */
	private static int counter;
	
	/**
	 * A static initialization block.
	 */
	static {
		counter = 0;
	}
	
	/**
	 * A constructor that uses the {@code #counter}
	 * static member variable.
	 */
	public StaticExamples()
	{
		counter++;
	}
	
	/**
	 * Static member method that returns with the {@link #counter}
	 * @return returns with the {@link #counter}
	 */
	public static int getCounter()
	{
		return counter;
	}
	
	/**
	 * An instance member method.
	 */
	public void doSomethingUseful()
	{
		print( "Doing something useful" );
	}
	
	/**
	 * A static nested class.
	 * @author BagyuraI
	 */
	private static class InnerClass
	{
		/**
		 * An instance member method.
		 */
		public void doSomethingUseful()
		{
			print( "Doing something useful" );
		}
	}
	
	/**
	 * Entry point of this app.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) 
	{		
		for ( int i = 0 ; i < 10; i++ )
		{
			new StaticExamples();
			// Usage of a static method
			print( StaticExamples.getCounter() );
			// Instantiation of a static nested class.
			InnerClass innerClass = new InnerClass();
			innerClass.doSomethingUseful();
		}
	}
}
