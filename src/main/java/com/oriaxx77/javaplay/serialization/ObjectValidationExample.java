/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Example for validating an fully read object graph.
 * Register an object with the {@link ObjectInputStream#registerValidation(ObjectInputValidation, int)}
 * to be validated before the graph is returned.  
 * While similar to resolveObject these validations are called after the entire
 * graph has been reconstituted. 
 * 
 * This example has a {@link SpaceRocket} class that implements the {@link ObjectInputValidation}.
 * It registers itself in the {@link SpaceRocket#readObject(ObjectInputStream)}.
 * 
 * Use the {@link #main(String[])} method to run a good and a bad data set.
 * 
 * @author BagyuraI
 *
 */
public class ObjectValidationExample
{

	/**
	 * Super class for all Rockets. It is serializable.
	 * @author Bagyura
	 */
	private static class Rocket implements Serializable
	{
		/**
		 * Serial version UID
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Name of this space rocket.
		 */
		private String name;
		/**
		 * Year of the invention of this space rocket.
		 */
		private int yearOfInvention;

		/**
		 * Creates a new Rocket
		 * @param name Name of this space rocket.
		 * @param yearOfInvention Year of the invention of this space rocket
		 */
		public Rocket(String name, int yearOfInvention)
		{
			super();
			this.name = name;
			this.yearOfInvention = yearOfInvention;
			checkInvariants();
		}
		
		/**
		 * Checking the invariants of this rocket object.
		 * If yearOfInvention < 1900 then it throws an exception.
		 */
		public void checkInvariants()
		{
			if ( yearOfInvention < 1900 )
				throw new IllegalArgumentException( "yearOfInvention must be greater than 1900. It is " + yearOfInvention );
		}
		
		/**
		 * Returns with the {@link #yearOfInvention} field
		 * @return the {@link #yearOfInvention} field
		 */
		public int getYearOfInvention()
		{
			return yearOfInvention;
		}

		/**
		 * Returns with the {@link #name} field
		 * @return the {@link #name} field
		 */
		public String getName()
		{
			return name;
		}
		
		
		
	}
	
	/**
	 * A SpaceRocket that extends the {@link Rocket} implements the {@link ObjectInputValidation}.
	 * @author BagyuraI
	 *
	 */
	private static class SpaceRocket extends Rocket implements ObjectInputValidation
	{
		/**
		 * Serial version UID
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Range of the space rocket.
		 */
		private long maxRange;
		
		
		
		/**
		 * Creates a space rocket.
		 * @param name Name of this space rocket.
		 * @param yearOfInvention Year of the invention of this space rocket
		 * @param maxRange Range of the space rocket.
		 */
		public SpaceRocket(String name, int yearOfInvention, long maxRange)
		{
			super(name, yearOfInvention);
			this.maxRange = maxRange;
		}

		/**
		 * Checking the invariants of this rocket object.
		 * If yearOfInvention < 1950 then it throws an exception.
		 */
		public void checkInvariants()
		{
			if ( getYearOfInvention() < 1950 )
				throw new IllegalArgumentException( "No way for this invetion date for a space rocket. Current year of invention:  " + getYearOfInvention() );
		}

		/**
		 * Overwrites the default deserialization mechanism of this class.
		 * @param in
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
		{
			in.registerValidation( this , 0);
			in.defaultReadObject();
			
		}
		
		/**
	     * Validates the object.
	     *
	     * @exception InvalidObjectException If the object cannot validate itself.
	     */
		@Override
		public void validateObject() throws InvalidObjectException
		{
			checkInvariants();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "SpaceRocket [maxRange=" + maxRange
					+ ", getYearOfInvention()=" + getYearOfInvention()
					+ ", getName()=" + getName() + "]";
		}

		
		
		
	}
	
	
	
	
	/**
	 * Deserialize an object from it's binary representation.
	 * @param binaryObject The binary representation of the object.
	 * @return
	 * @throws IOException IO related deserialization error.
	 * @throws ClassNotFoundException The deserialized class cannot be found in the classpath.
	 */
	private static Object deserialize( byte[] binaryObject ) throws IOException, ClassNotFoundException
	{
		try ( ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream( binaryObject ) ) )
		{
			return ois.readObject();
		}
	}
	
	/**
	 * Serialize the object into a byte array.
	 * @param objectToSerialize Object to be serialized.
	 * @return The serialized object in binary format.
	 * @throws IOException  IO related serialization error.
	 */
	private static byte[] serialize( Serializable objectToSerialize ) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try ( ObjectOutputStream oos = new ObjectOutputStream( baos ) )
		{
			oos.writeObject( objectToSerialize );
			return baos.toByteArray();
		}
	}
	
	/**
	 * Runs this example.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// Good data
			SpaceRocket soyuz = new SpaceRocket( "Soyuz", 1967 , 2000 );
			byte[] binary = serialize( soyuz );
			System.out.println( deserialize( binary ) );
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			// Bad data
			SpaceRocket soyuz = new SpaceRocket( "Soyuz", 1900 , 2000 );
			byte[] binary = serialize( soyuz );
			System.out.println( deserialize( binary ) );
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
