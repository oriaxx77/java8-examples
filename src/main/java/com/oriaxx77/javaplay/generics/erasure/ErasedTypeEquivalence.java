/**
 * 
 */
package com.oriaxx77.javaplay.generics.erasure;

import java.util.ArrayList;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Type erasure eqvivalence example.
 * @author BagyuraI
 */
public class ErasedTypeEquivalence
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Class<?> c1 = new ArrayList<String>().getClass();
		Class<?> c2 = new ArrayList<Integer>().getClass();
		
		Print.print( c1 == c2 );

	}

}
