/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.io.Serializable;
import java.util.Date;

/**
 * NOTE: An insecure value object example. It doesn't protect it's inner state. See the main method.
 * It represents a meeting with it's start and end date.
 * @author BagyuraI
 */
public class InsecureMeeting implements Serializable // NOTE: It should implement a readObject() that should 
													 // work like a constructor with an ObjectInputStream parameter.
{
	/**
	 * Default serial version UID. 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Start of the meeting */
	private final Date start;
	/** End of the meeting */
	private final Date end;
	
	/**
	 * NOTE: secure the member variables.
	 * NOTE: check invariants (start cannot be later than date)
	 * @param start Start of the meeting. NOTE: if you modify the passed parameter the inner state will also change. This is not good for a value object.
	 * @param end End of the meeting NOTE: if you modify the passed parameter the inner state will also change. This is not good for a value object.
	 */
	public InsecureMeeting(Date start, Date end)
	{
		super();
		// NOTE: it should secure the this.start.
		this.start = start;
		// NOTE: it should secure the this.end.
		this.end = end;
		// NOTE: it should check it's invariants. E.g. start date > end date
	}
	
	/**
	 * @return the start
	 */
	public Date getStart()
	{
		// NOTE: it should secure this.start.
		return start;
	}

	/**
	 * @return the end
	 */
	public Date getEnd()
	{
		// NOTE: it should secure this.end.
		return end;
	}





	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new StringBuilder( "Meeting{start:" ).append( start ).append( ",end:").append( end ).append( "}").toString();
	}

	/**
	 * Run  this app to see the demonstration of the weakness of the insecure Meeting class.
	 * @param args
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException
	{
		Date start = new Date(101 , 10, 10 );
		Date end = new Date(101 , 10, 11 );
		InsecureMeeting meeting = new InsecureMeeting( start, end );
		print( "Meeting at the start: " + meeting );
		end.setDate( 1 );
		print( "Meeting after start date (external) modification: " + meeting );
		
	}
	
	
	
}
