/**
 * 
 */
package com.oriaxx77.javaplay.generics.erasure;

import com.oriaxx77.javaplay.utility.Print;


/**
 * T's upper bound is java.lang.Object. T is treated as a java.lang.Objecct object at compile time.
 */
class Manipulator<T>
{
	/** Object to manipulate */
	private T obj;
	
	/** Construct a manipulator with an object to manipulate. */
	public Manipulator( T x ){ obj = x ;}
	/** Return with the object that is intended to manipulate. */
	public T getObj(){ return this.obj; }
	/** Manipulate the object. */
	public void manipulate() 
	{
		// Error cannot find symbol: method :f();
		// This is because T has an upper bound type: java.lang.Object.
		// and it is treated according to this. The Object doesn't have
		// the method f().
		// obj.f(); 
	}
}

/**
 * T's upper bound is HasF. T is treated as a HasF object at compile time.
 * @author BagyuraI
 *
 * @param <T>
 */
class Manipulator2< T extends HasF > extends Manipulator<T>
{
	
	/** Construct a manipulator with an object to manipulate */
	public Manipulator2( T x ){ super( x ); }
	
	/** Manipulate the object. */
	public void manipulate() 
	{
		getObj().f(); 
	}
}

/**
 * HasF class.
 * @author BagyuraI
 */
class HasF
{
	public void f()
	{
		Print.print( "HasF.f()" );
	}
}
/**
 * Example for the upper bound.
 * @author BagyuraI
 */
public class UpperBoundExample
{
	/**
	 * Static entry point of this example.
	 * @param args Not used.
	 */
	public static void main(String[] args)
	{
		HasF hf = new HasF();
		Manipulator<HasF> m = new Manipulator<HasF>( hf );
		m.manipulate();
		
		Manipulator2<HasF> m2 = new Manipulator2<HasF>( hf );
		m2.manipulate();
	}
}



