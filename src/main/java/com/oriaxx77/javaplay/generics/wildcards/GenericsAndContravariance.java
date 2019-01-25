/**
 * 
 */
package com.oriaxx77.javaplay.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

class A { }

class B extends A { }

class C extends B {}


/**
 * Example for generic covariance and contravariance.
 * @author BagyuraI
 */
public class GenericsAndContravariance
{
	/**
	 * Method to test the {@link #contravariance(List, B)} and {@link #covariance(List)} methods.
	 */
	public void test()
	{
		List<A> aList = new ArrayList<A>();
		aList.add( new A() );
		
		List<B> bList = new ArrayList<B>();
		bList.add( new B() );
		
		List<C> cList = new ArrayList<C>();
		cList.add( new C() );
		
		// Demonstrating covariance
		@SuppressWarnings("unused") B b = covariance( bList );
		@SuppressWarnings("unused") B c = covariance( cList );
		
		// Demonstrating contravariance
		contravariance( bList, new B() );
		contravariance( aList, new B() );
		
	}
	
	
	// This cannot be invoked with a List<B>
	/*
	public A covariant(List<A> list)
	{
		return list.remove(0);
	}
	*/
	
	
	/**
	 * Method to demonstrate the generic covariance.
	 * @param list This can be invoked with List<C> and List<B>
	 * @return
	 */ 
	public B covariance( List< ? extends B > list )
	{
		// list.get() returns A. This way you can get an A from any list that extends A.
		// E.g. access an element as A from a List<B>
		// with list.set() you can't set anything. 
		return list.remove( 0 );
	}
	
	/**
	 * Method to demonstrate the generic contravariance
	 * @param list This can be invoked with List<A> and List<B>
	 * @return
	 */
	public void contravariance( List<? super B> list , B b )
	{
		// list.get() return with Object
		// list.set( ) set a B element. This way you can
		// put a B element into an A list.
		list.add( b );
	}
	
	
	
}
