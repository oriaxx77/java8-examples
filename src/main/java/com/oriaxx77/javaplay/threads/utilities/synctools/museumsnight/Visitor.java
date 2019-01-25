package com.oriaxx77.javaplay.threads.utilities.synctools.museumsnight;

import java.util.Random;

import com.oriaxx77.javaplay.utility.Print;
import com.oriaxx77.javaplay.utility.Sleeper;

/**
 * A visitor who can enter an exhibition.
 * It can use the {@link #visit(Exhibition)} method to have fun in an 
 * exhibition. 
 * @author BagyuraI
 *
 */
public class Visitor
{
	/**
	 * Name of the visitor
	 */
	private String name;
	
	
	/**
	 * Creates a visitor
	 * @param name
	 */
	public Visitor(String name)
	{
		super();
		this.name = name;
	}

	
	
	/**
	 * Visiting an exhibition.
	 * @param exhibition
	 */
	public void visit( final Exhibition exhibition )
	{
		
		new Thread( new Runnable()
		{
			
			@Override
			public void run()
			{
				try
				{
					exhibition.entry( Visitor.this );
				}
				catch (InterruptedException e)
				{
					Thread.currentThread().interrupt();
					throw new RuntimeException( e );
				}
			}
		} ).start();
	}
	
	/**
	 * Enjoying the exhibition.
	 * @param exhibition
	 */
	public void enjoy( Exhibition exhibition )
	{
		Print.print( name + " having fun at " + exhibition.getName() );
		Sleeper.sleep( new Random().nextInt( 1000 ));
		Print.print( name + " is leaving the " + exhibition.getName() );
	}

	/**
	 * Returns with the {@link #name}.
	 * @return The value of the {@link #name}
	 */
	public String getName()
	{
		return name;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Visitor [name=" + name + "]";
	}
	
	
	
}
