package com.oriaxx77.javaplay.annotations.basics;

/**
 *  A class that demonstrates the usage of the {@link Test} annotation.
 * @author BagyuraI
 */
public class Testable
{
	/** A method to test (a business method) */
	public void execute()
	{
		System.out.println( "Executing" );
	}
	
	/** A method that tests the business method */
	public @Test void testExecute()
	{
		execute();
	}
}
