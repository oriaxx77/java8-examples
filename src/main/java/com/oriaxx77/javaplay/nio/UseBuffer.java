/**
 * 
 */
package com.oriaxx77.javaplay.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * A simple class to show how to use a {@link Buffer}.
 * @author BagyuraI
 */
public class UseBuffer 
{
	/** 
	 * It simply prints out the buffer's state: position,limit,capacity along with a statement
	 * @param stmt Statement to print
	 * @param buffer The buffer of which we the print the state. 
	 */
	private static void printBufferState( String stmt, IntBuffer buffer )
	{
		System.out.println( stmt + "[pos: " + buffer.position() + ", limit: " + buffer.limit() + " , cap: " + buffer.capacity() + "]" );
	}
	
	/**
	 * Static entry point for this sample
	 * @param args Command line arguments. Not used atm.
	 */
	public static void main( String[] args )
	{
		// Allocate a buffer with ten slots.
		IntBuffer buffer = IntBuffer.allocate( 10 );
		printBufferState( "Initial state: " , buffer );
		// Put some data into the buffer;
		for ( int i = 0 ; i < buffer.capacity() -2 ; i++ )
		{
			// Put one element into the buffer.
			// It increases position by 1.
			buffer.put( i ); 
			printBufferState( "After put: " + i + " - ", buffer );
		}
		// Prepare for drainning 
		// Limit is set to current position and position is set to 0.  
		buffer.flip();
		printBufferState( "After flipping: "  , buffer );
		
		// Read the buffer
		while ( buffer.hasRemaining() ) // position < limit
		{
			printBufferState( "Reading value: " + buffer.get() + " - " , buffer );
		}
		
		// Prepare for write again
		// Limit is set to the capacity and the position to 0
		buffer.clear(); 
		printBufferState( "After clear: "  , buffer );
	}
}
