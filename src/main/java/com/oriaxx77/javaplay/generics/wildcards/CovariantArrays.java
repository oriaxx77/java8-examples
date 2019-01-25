/**
 * 
 */
package com.oriaxx77.javaplay.generics.wildcards;


/**
 * You can assign an array of a derived types to an array reference of the base type.
 * This is called CoVariance.	
 * @author BagyuraI
 */

class Fruit{}
class Apple extends Fruit{}
class Jonathan extends Apple{}
class Orange extends Fruit{}

public class CovariantArrays
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Fruit[] fruit = new Apple[10]; // Runtime type is Apple because of the new Apple();
		fruit[0] = new Apple(); // This is OK.
		fruit[1] = new Jonathan(); // This is OK
		
		try
		{
			// Runtime type is Apple[] and not Fruit[]
			// So this will throw an exception.
			// You will get an ArrayStoreException.
			fruit[2] = new Orange();
		}
		catch( Exception ex )
		{
			System.out.println( ex );
		}
		
		try
		{
			// Runtime type is Apple[] and not Fruit[]
			// So this will throw an exception.
			// You will get an ArrayStoreException.
			fruit[3] = new Fruit();
		}
		catch( Exception ex )
		{
			System.out.println( ex );
		}
		
		// It won't compile.
		// You can't assign a generic involving Apples to a generic involving Fruit.
		// List<Fruit> l = new ArrayList<Apple>();
		
		// It won't compile either.
		// You can't assign a generic involving Apples to a generic involving Fruit.
		// List<Apple> l = new ArrayList<Fruit>();
		
		
	}

}
