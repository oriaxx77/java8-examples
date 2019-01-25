/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * NOTE: A secured value object example.
 * 
 * Secured means:
 * 
 * 1) For classes with object reference fields that must remain private, defensively copy each object in such a field. 
 *    Mutable components of immutable classes fall into this category.
 *
 * 2) Check any invariants and throw an InvalidObjectException if a check fails. The checks should follow any defensive copying.
 *
 * 3) If an entire object graph must be validated after it is deserialized, use the ObjectInputValidation interface [JavaSE6, Serialization].
 *
 * 4) Do not invoke any overridable methods in the class, directly or indirectly. 
 *  
 * It represents a meeting with it's start and end date.
 * @author BagyuraI
 *
 */
public class SecureMeeting implements Serializable // NOTE: It implements the readObject() method and treat is s
												   //  a constructor with an ObjectInputStream parameter.
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
	 * Creates a SecureMeeting instance with it's start end end date.
	 * Invariant: start date > end date.
	 * @param start Start of the meeting. 
	 * @param end End of the meeting 
	 */
	public SecureMeeting(Date start, Date end)
	{
		super();
		// NOTE: secure copy. If the parameter object's state changes it wont effect this.start.
		this.start = new Date( start.getTime() );
		// NOTE: secure copy. If the parameter object's state changes it wont effect this.end.
		this.end = new Date( end.getTime() );
		// NOTE: Invariant checking
		checkInvariants();
	}
	
	/**
	 * @return the start
	 */
	public Date getStart()
	{
		// NOTE: secure copy. If the returned object's state changes it wont effect this.start.
		return new Date( start.getTime() );
	}

	/**
	 * @return the end
	 */
	public Date getEnd()
	{
		// NOTE: secure copy. If the returned object's state changes it wont effect this.end.
		return new Date( end.getTime() );
	}



	/**
	 * readObject for serialization is handled as a constructor.
	 * Read the non-static and non-transient fields of the current class from
     * this stream. It checks the invariants
     * 
	 * @param in Inputstream to read the object from.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		// NOTE: Always call the default reader to read all non-static, non-final instance variable
		in.defaultReadObject();
		// NOTE: Invariant checking
		checkInvariants();
		
	}
	
	/**
	 * Check the invariants. End > Start.
	 * @throws IllegalStateException If Start > End.
	 */
	private void checkInvariants() throws IllegalStateException
	{
		if ( start.after( end ))
			throw new IllegalStateException( start + " after " + end );
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
	 * @param args Not used
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException
	{
		Date start = new Date(101 , 10, 10 );
		Date end = new Date(101 , 10, 11 );
		SecureMeeting meeting = new SecureMeeting( start, end );
		print( "Meeting at the start: " + meeting );
		meeting.getEnd().setDate( 1 );
		print( "Meeting after start date (external) modification: " + meeting );
	}
	
	
	
	
}
