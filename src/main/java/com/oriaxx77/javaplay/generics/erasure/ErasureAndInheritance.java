/**
 * 
 */
package com.oriaxx77.javaplay.generics.erasure;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A class that holds an object.
 * 
 * @author BagyuraI
 */
class Holder<T>
{
	/** Element to store */
	private T element;
	/** Return with the stored object */
	public T get() { return element; }
	/** Store an object */
	public void set(T element){ this.element = element; }
}

/**
 * A specialized holder. 
 * @param T type of the object.
 * @author BagyuraI
 */
class Derived1<T> extends Holder<T> 
{
}

/**
 * A specialized holder. It is raw. 
 * @param T type of the object. It has no effect.
 * @author BagyuraI
 */
@SuppressWarnings("rawtypes")
class Derived2<T> extends Holder
{	
}




public class ErasureAndInheritance
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		@SuppressWarnings("rawtypes")
		Derived2 d2 = new Derived2();
		d2.set( "abc" );// It accepts any object
		Object obj = d2.get();
		// No compile time error here :(.
		// No warning.
		d2.set( obj );
		Print.print( d2.get() );
		
		Derived1<String> dString = new Derived1<String>();
		dString.set( "a" );
		@SuppressWarnings("rawtypes")
		Derived1 dObject = dString; // Warning that you should parameterize dObject
		dObject.set( 5 );
		// Integer i = d3.get(); Compile time error.
		String s = dString.get(); // It returns an Object , we just see it as String in compile time.
		Print.print( s );
	}
}
