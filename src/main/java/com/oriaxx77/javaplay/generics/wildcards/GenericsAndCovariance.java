/**
 * 
 */
package com.oriaxx77.javaplay.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * Example program for covariance with generics.
 * @author BagyuraI
 */
public class GenericsAndCovariance
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Wildcards allow covariance
		List<? extends Fruit> flist = new ArrayList<Apple>();
		
		// Compile time error.
		// Can't add any type of object.
		// You cannot know what is the actual type. It can be anything the derives from Fruit.
		// flist.add( new Apple() );
		// flist.add( new Fruit() );
		// flist.add( new Object() );

		
		// Adding null is legal.
		flist.add( null );
		
		// We know that it returns Fruit.
		Fruit f = flist.get(0);
	}

}
