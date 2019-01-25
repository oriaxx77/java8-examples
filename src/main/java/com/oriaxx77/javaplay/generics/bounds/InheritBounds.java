/**
 * 
 */
package com.oriaxx77.javaplay.generics.bounds;

import java.awt.Color;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Illustrates the basic upper bound mechanism.
 * @author BagyuraI
 */

class HoldItem<T>
{
	T item;
	HoldItem( T item ){ this.item = item; }
	T getItem(){ return this.item; }
}

class Colored2< T extends HasColor> extends HoldItem<T>
{
	Colored2( T item ){ super(item); }
	Color color(){ return item.getColor(); }
	
}



class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T>
{
	
	ColoredDimension2( T item){ super(item); }
	int getX(){ return item.x; }
	int getY(){ return item.y; }
	int getZ(){ return item.z; }
	
}



// Upper bound = 1 class + multiple interfaces.
// Order matters. The class must come first.
class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T>
{
	Solid2( T item){ super(item); }
	int weight(){ return item.weight(); }
}






public class InheritBounds
{
	public static void main(String[] args)
	{
		Solid2<Bounded> solid = new Solid2<Bounded>( new Bounded() );
		Print.print( solid.getX() );
		Print.print( solid.color() );
		Print.print( solid.weight() );
	}
	
}
