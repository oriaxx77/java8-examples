/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Instance methods in inheritance are overridden.
 * Class methods are only shadowed. There is no dynamic behaviour.
 * The actual type doesn't matter only the declared type.
 * E.g. A a = new B()
 *      a.staticMethod() means always A.staticMethod() even if B.staticMethod() exists.
 * 
 * Rules for overriding:
 * 
 * 1) Access modifiers and overriding
 * - An overriden method must not be more restrictive (e.g. public -> protected -> default -> private). 
 * - The default is more restrictive than protected.
 * 
 * 2) The method throws a checked exception
 * - Can throw the same checked exception or the sublcass of this checked exception.
 * - Can throw no exception
 * - Can throw extra runtime exception
 * 
 * 3) The method doesn't throw checked exception
 * - Cannot throw checked exception
 * - Can throw any runtime exception
 * 
 * 3) Runtime exception
 * - No rules for that. You can declare, omit etc.
 * 
 * 4) Return type is covariant.
 * - E.g. super return with Object and child with String
 * - Before Java 5.0, when you override a method, both parameters and return type must match exactly. 
 *   In Java 5.0, it introduces a new facility called covariant return type. 
 *   You can override a method with the same signature but returns a subclass of the object returned. 
 *   In another words, a method in a subclass can return an object whose type is a 
 *   subclass of the type returned by the method with the same signature in the superclass.
 * 
 * Articles:
 * http://www.javatpoint.com/method-overriding-in-java
 * http://www.javatpoint.com/access-modifiers#accessoverriding
 * http://www.javatpoint.com/exception-handling-with-method-overriding
 * 
 * @author BagyuraI
 */
public class OverridingAndShadowing
{
	// Base class
	private static class A
	{
		// Class method.
		public static void classMethod()
		{
			Print.print( "A.classMethod()" );
		}
		
		// Instance method
		public Object instanceMethod() throws Exception
		{
			Print.print( "A.instanceMethod()" );
			return "";
		}
	}
	
	// Child class
	private static class B extends A
	{
		// Class methods cannot be overridden.
		// It is only shadowing.
		public static void classMethod()
		{
			Print.print( "B.classMethod()" );
		}
		
		// Overridden instance method.
		// NOTE: doesn't throw exception. The overriden method does.
		// NOTE: it returns a covariant type.
		@Override
		public String instanceMethod()
		{
			Print.print( "B.instanceMethod()" );
			return "";
		}
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception // NOTE: b.instanceMethod() doesn't throw exception, but the compiler only see this as A :)
														    // So in runtime you cannot have this exception but the compiler doesn't know about this.
	{
		A a = new B();
		// The actual type doesn't matter for class methods.
		// Only the delared type matters. Here A. (Shadowing)
		a.classMethod();
		// The actual type does matter for instance methods.
		// Here B. (Polymorphism)
		a.instanceMethod();
	}
}
