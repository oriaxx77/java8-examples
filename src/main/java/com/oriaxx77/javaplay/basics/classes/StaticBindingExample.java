package com.oriaxx77.javaplay.basics.classes;

import com.oriaxx77.javaplay.utility.Print;


/**
 * <p>Example for static binding.
 *    Java uses static binding for overloaded methods.<
 * 
 * <p> We have two class {@link A} and {@link B}. B is a subclass of A.
 *     We have a third class {@link C} with two overloaded m method:  {@link C#m(A)} and {@link C#m(B)}.
 *     If we have an A a = new B() ( a has A static type and B dynamic type)
 * </p> 
 * 
 * @author BagyuraI
 */
public class StaticBindingExample 
{

	/** A simple class used for demonstration  in the {@link StaticBindingExample} */
	private static class A 
	{

		@SuppressWarnings("unused") // We get this warning because it is a private class. It is private because it is a demo.
		public void m()
		{
		  Print.print( "A.m" );
		}
	}

	/** A simple class used for demonstration  in the {@link StaticBindingExample} */
	private static class B extends A 
	{

		  public void m()
		  {
			  Print.print( "B.m" );
		  }
	}

	/** A simple class used for demonstration  in the {@link StaticBindingExample}.
	    It defines overloaded methods {@link C#m(A)} and {@link C#m(B)} */
	private static class C
	{
		public void m( A a ){ Print.print("C.m(A)");}
		
		@SuppressWarnings("unused") // We get this warning because it is a private class. It is private because it is a demo.
		public void m( B b ){ Print.print("C.m(B)");}
	}
	
	/**
	 * Run this method to demonstrate the static bounding with method overloading.
	 * Examine the code with the comments in the code.
	 * @param args Command line arguments. It is not used atm.
	 */
	public static void main(String[] args) 
	{
		A a = new B(); // NOTE: A static type and B dynamic type
		C c = new C();
		c.m(a); // NOTE: C.m() has 2 overloaded methods. Since method overloading uses static binding the a is statically A -> C.m( A ) is invoked.
		        // Please note the compiler warning at C.m( B ) that notes that it is never used locally :)
	}
	 	
}



