package com.oriaxx77.javaplay.basics.classes;

/**
 * <p>Example for Dynamic and static typing in Java.
 * Static binding for overloaded methods
 * Dynamic binding for overridden methods.</p>
 * 
 * @author BagyuraI
 */
public class DynamicStaticBinding1 
{

	/**
	 * Run some dynamic and static typing related code.
	 * Please see the code with the explanation (as comments).
	 * @param args command line arguments. They are not used atm.
	 */
	public static void main(String[] args) 
	{

		Knight p1 = new Knight();
		Piece p2 = new Knight();
		p1.capture(p2); // call 1; "Knight is bored" is supposed to be printed
		
		// Call 1 ) Explanation (p1.capture(p2))
		// p1 is knight, p2 is statically a Piece -> Knight.capture( Piece p ) will be invoked.
		
		p2.capture(p1); // call 2; "Knight is bored" is supposed to be printed
		
		// Call 2. ) Explanation( p2.capture(p1); )
		// p2.capture( p1 ) -> p2 is declared as a Piece. so you can only call
		// methods of Piece.
		// It is like List l = new ArrayList(); You can only call methods of
		// List
		// Since capture(Piece p ) is overwridden in Knight.capture( piece ) it
		// will be executed
		// Piece.capture( Piece ) -> (overwrite) -> Knight.capture( Piece )

		
	}

	

}

/**
 * A sample class used by the {@link DynamicStaticBinding1} for demonstrating the 
 * dynamic and static binding.
 * @author BagyuraI
 */
class Piece 
{
	public void capture() 
	{
		System.out.println("Capturing");
	}

	public void capture(Piece p) 
	{
		System.out.println("I'm bored");
	}
}

/**
 * A sample class used by the {@link DynamicStaticBinding1} for demonstrating the 
 * dynamic and static binding.
 * @author BagyuraI
 */
class Knight extends Piece 
{

	public void capture(Piece p) 
	{
		System.out.println("Knight is bored");
	}

	public void capture(Knight k) 
	{
		System.out.println("TOO SLOW BUDDY");
	}
}