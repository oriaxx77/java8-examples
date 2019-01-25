package com.oriaxx77.javaplay.basics.classes;

import com.oriaxx77.javaplay.utility.Print;


/**
 * <p>Example for Dynamic and static typing in Java.
 * Static binding for overloaded methods
 * Dynamic binding for overridden methods.</p>
 * 
 * @author BagyuraI
 */
public class DynamicStaticBinding2 
{
	/**
	 * A class that defines a new equals method.
	 * Note that this new {@link #equals(Test)} doesn't overwrite the {@link #equals(Object)} method.
	 * It is an overloading (There is static binding here).
	 */
	static class Test 
	{
		  public boolean equals( Test other ) 
		  {
		    Print.print( "Test.equals( Test)" );
		    return false;
		  }
	}
	 

	/**
	 * Run some dynamic and static typing related code.
	 * Please see the code with the explanation (as comments).
	 * @param args command line arguments. They are not used atm.
	 */
	public static void main(String[] args) 
	{
		Object t1 = new Test();
		Object t2 = new Test();
		Test t3 = new Test();
		Object o1 = new Object();

		
		System.out.println( t1.equals(t2) ); // false, no print , Explanation: Test.equals( Object ) runs. -> It is not overriden so it uses the Object.equals()
		
		System.out.println( t1.equals(t3) ); // false, no print, Explanation: Test.equals( Object ) runs. -> It is not overriden so it uses the Object.equals()
		
		System.out.println( t3.equals(o1) ); // false, no print, Explanation: Test.equals( Object ) runs -> -> It is not overriden so it uses the Object.equals()
		
		System.out.println( t3.equals(t3) );// false, print, Explanation: Test.equals( Test ) runs 
		
		System.out.println( t3.equals(t2) ); // false, no print, Explanation: Explanation: Test.equals( Object ) runs -> -> It is not overriden so it uses the Object.equals()
	}	

}

