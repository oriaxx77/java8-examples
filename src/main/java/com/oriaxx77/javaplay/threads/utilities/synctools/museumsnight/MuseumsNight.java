/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.synctools.museumsnight;

import java.util.concurrent.Semaphore;

/**
 * An example program for using {@link Semaphore}.
 * The program is about the Museums' Night.
 * It starts with an {@link Exhibition} that can host 3 {@link Visitor}s
 * at the same time. If there are more visitors, they have to wait.
 * @author BagyuraI
 */
public class MuseumsNight
{
	/**
	 * Static entry point of the program.
	 * It creates and {@link Exhibition} named Leonardo Art Exhibition
	 * that can host 3 {@link Visitor}s at the same time.
	 * @param args Command line arguments. It is not used at this moment.
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Exhibition leonardoExhibition = new Exhibition( "Leonardo Art Exhibition", 3 );
		for ( int i = 0 ; i < 10; i++ )
		{
			Visitor visitor = new Visitor( "visitor_" + i );
			visitor.visit( leonardoExhibition );
		}
	}
}
