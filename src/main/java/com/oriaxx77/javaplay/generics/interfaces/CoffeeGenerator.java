/**
 * 
 */
package com.oriaxx77.javaplay.generics.interfaces;

import java.util.Random;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Coffee generator that gives random coffee.
 * @author BagyuraI
 */
public class CoffeeGenerator
	implements Generator<Coffee>
{
	/**
	 * Coffee types.
	 */
	private static Class<?>[] types = {Latte.class, Mocha.class, Americano.class};
	/**
	 * Random number generator that generates a random number that help to 
	 * pick up a random new coffee.
	 */
	private static Random rand = new Random(47);
	
	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.generics.interfaces.Generator#next()
	 */
	@Override
	public Coffee next() 
	{
		try
		{
			int idx = rand.nextInt( types.length );
			return (Coffee)types[idx].newInstance();
		}
		catch( Exception ex )
		{
			throw new RuntimeException();
		}
	}
	
	/**
	 * Satic entry point for the program that generates 3 new cup of random coffee.
	 * @param args Arguments. Not used.
	 */
	public static void main(String[] args)
	{
		CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
		for ( int i = 0 ; i < 3 ; i++ )
		{
			Coffee coffee = coffeeGenerator.next();
			Print.print( coffee );
		}
	}
	
}
