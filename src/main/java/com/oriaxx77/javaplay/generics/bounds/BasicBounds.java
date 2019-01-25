/**
 * 
 */
package com.oriaxx77.javaplay.generics.bounds;

import java.awt.Color;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Illustrates the basic upper bound mechanism.
 * The upper bound allows to relax the restrictions on a variable.
 * E.g. instead writing List<Integer> you write List< ? extends Number>
 * to use any type as actual type parameter that extends the Number.
 * @author BagyuraI
 */

interface HasColor{ Color getColor(); }

// Simpple bound. The upper bound of T will be HasColor.
class Colored<T extends HasColor>
{
	T item;
	Colored( T item ){ this.item = item; }
	T getItem(){ return this.item; }
	// You can call item.getColor because the upper bound of T is HasColor.
	Color getColor(){ return item.getColor(); }
	
}

class Dimension{ public int x, y, z;}

// Multiple bounds
// In case of multiple bounds after extends classes must come first.
// So T extends HasColor & Dimension won't work because HasColor is an interface
// and must come after the Dimension that is a class.
// You can have only one class and multiple interfaces. (makes sense since there is 
// no multiple inheritance in java.
class ColoredDimension<T extends Dimension & HasColor>
{
	T item;
	ColoredDimension( T item){ this.item = item; }
	T getItem(){ return this.item; }
	Color color(){ return this.item.getColor(); }
	int getX(){ return item.x; }
	int getY(){ return item.y; }
	int getZ(){ return item.z; }
	
}

interface Weight{ int weight(); }

// Upper bound = 1 class + multiple interfaces.
// Order matters. The class must come first.
class Solid<T extends Dimension & HasColor & Weight>
{
	T item;
	Solid( T item){ this.item = item; }
	T getItem(){ return this.item; }
	Color color(){ return this.item.getColor(); }
	int getX(){ return item.x; }
	int getY(){ return item.y; }
	int getZ(){ return item.z; }
	int weight(){ return item.weight(); }
}

class Bounded extends Dimension implements HasColor, Weight
{
	public Color getColor(){ return null; }
	public int weight(){ return 0;}
}




public class BasicBounds
{
	public static void main(String[] args)
	{
		Solid<Bounded> solid = new Solid<Bounded>( new Bounded() );
		Print.print( solid.getX() );
		Print.print( solid.color() );
		Print.print( solid.weight() );
	}
	
}
