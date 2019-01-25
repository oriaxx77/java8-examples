/**
 * 
 */
package com.oriaxx77.javaplay.enums;

import java.util.EnumSet;

/**
 * An example that compare bitwise operations with ints and 
 * enums. The example is defines the 4 major directions (north,south,west,east)
 * and a method to define if a direction contains a major direction or not.
 * It has two implementations:
 * 1. A class with int constants and bitwise operators.
 * 2. An enum where we use {@link EnumSet}.
 * 
 * @author BagyuraI
 */
public class BitwiseOperatorsVsEnums
{
	
	/**
	 * A class that can be used to examine if someone can go to the 4 major directions.
	 * The directions (south, north, west, east) are defined with int constant. 1 in one of the lower 4 bits
	 * indicates the direction. The order is SOUTH(0001), NORTH(0010),  WEST(0100), EAST(1000).
	 * The {@link #canGoThatDirection(int, int)} can be used to examine if someone can go to a direction.
	 * The {@link #canGoSouth(int)} can be used to examine if someone can go to south. They use bitwise OR.
	 * @author BagyuraI
	 *
	 */
	private static final class Direction
	{
		/**
		 * Constant for EAST
		 */
		public static final int SOUTH = 0b0001;
		/**
		 * Constant for NORTH
		 */
		public static final int NORTH = 0b0010;
		/**
		 * Constant for WEST
		 */
		public static final int WEST = 0b0100;
		/**
		 * Constant for EAST
		 */
		public static final int EAST = 0b1000;
		
		/**
		 * Examines if the allowed directions contains south.
		 * @param allowedDirections allowed directions.
		 * @return TRUE if the allowed directions contains south.
		 */
		public boolean canGoSouth( int allowedDirections )
		{
			return  canGoThatDirection( allowedDirections, SOUTH );
		}
		
		/**
		 * Examines if the allowed directions contains north.
		 * @param allowedDirections allowed directions.
		 * @return TRUE if the allowed directions contains north.
		 */
		public boolean canGoNorth( int allowedDirections )
		{
			return  canGoThatDirection( allowedDirections, NORTH );
		}
		
		/**
		 * Examines if the allowed directions contains the expected direction
		 * @param allowedDirections allowed directions.
		 * @param expectedDirection expected direction
		 * @return TRUE if the allowedDirections contains the expectedDirection direction.
		 */
		public boolean canGoThatDirection( int allowedDirections, int expectedDirection )
		{
			return (allowedDirections & expectedDirection) != 0;
		}
	}
	
	/**
	 * An enum to define the 4 major directions.
	 * It has a method {@link #canGoSouth(EnumSet)} to decide if someone can go to the direction
	 * @author BagyuraI
	 *
	 */
	private enum Corner
	{
		/** Represents south */
		SOUTH,
		/** Represents north */
		NORTH,
		/** Represents west */
		WEST,
		/** Represents east */
		EAST;
		
		/**
		 * Examine if the allowedDirections contains that major direction.
		 * @param allowedDirections allowed directions
		 * @return TRUE if the allowedDirections contain that direction.
		 */
		boolean canGoThatDirection( EnumSet<Corner> allowedDirections )
		{
			return allowedDirections.contains( this );
		}
	}
	

	/**
	 * Demonstration for bitwise operator usage vs enums in {@link EnumSet}.
	 * @param args Command line arguments. It is not used.
	 */
	public static void main(String[] args)
	{
		Direction dir = new Direction();
		
		int[] directions = new int[]{ 0b1001, 
									 Direction.SOUTH | Direction.EAST, 
									 Direction.NORTH | Direction.WEST | Direction.EAST};
		for ( int direction: directions )
			System.out.println( Integer.toBinaryString( direction ) + 
					            " can go north (" + Integer.toBinaryString( Direction.NORTH ) + "): " + dir.canGoNorth( direction ) + 
					            " ,can go south (" + Integer.toBinaryString( Direction.SOUTH ) + "): " + dir.canGoSouth( direction ) );
			

		EnumSet<Corner> allowedDirections = EnumSet.of( Corner.NORTH, Corner.EAST );
		System.out.println( allowedDirections.toString() + 
	            " can go south (" + Corner.SOUTH + ") " + Corner.SOUTH.canGoThatDirection( allowedDirections ) );
	}

}
