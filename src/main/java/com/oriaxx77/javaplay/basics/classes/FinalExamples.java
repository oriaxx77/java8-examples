/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

/**
 * Examples for the final keyword.
 * @author BagyuraI
 */
public class FinalExamples 
{

	/**
	 * A final class -> Cannot inherite from this
	 */
	public static final class FinalClass
	{
		/**
		 * A final instance member variable -> Cannot be changed 
		 */
		@SuppressWarnings("unused")
		private final String finalInstanceMember = "Final Instance Variable";
		
		/**
		 * A final class member variable -> Cannot be changed
		 */
		@SuppressWarnings("unused")
		private final static String finalClassMember = "Final Class Variable";
		
		/**
		 * A final method. It cannot be overridden.
		 * @param param A param that cannot be changed.
		 */
		public final void doSomething( final Object param )
		{
			// param = new Object(); // It is illegal
			
			// A final local variable
			// It's value cannot be changed later
			@SuppressWarnings("unused")
			final Object localVar = new Object();
			// Changing the localVar's value is.
			// localVar = new Object();
		}
	}
	
	
	

}
