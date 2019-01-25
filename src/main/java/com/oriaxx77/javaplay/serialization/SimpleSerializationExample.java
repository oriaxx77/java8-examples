/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A simple serialization example.
 * It serializes an object and stores it in the {@value #FILE} file then
 * deserializes it and prints the class name of the object on the standard output.
 * @author BagyuraI
 */
public class SimpleSerializationExample 
{
	/**
	 * Path of the file we serialize into.
	 */
	private static final String FILE = "./tmp/SimpleSerializationExample.bin" ;
	
	/** 
	 * A serializable class
	 * @author BagyuraI
	 */
	private static class CustomType implements Serializable
	{
		/**
		 * A simple final field. It will be serialized.
		 */
		private final int i;
		
		/**
		 * Serial version unique identifier.
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Creates a CustomType instance with the given number
		 * @param i
		 */
		public CustomType(int i) 
		{
			super();
			this.i = i;
		}

		/**
		 * Returns with the i.
		 * @return the value of the {@link #i} field
		 */
		public int getI() 
		{
			return i;
		}
		
		
		
	}
	
	
	/**
	 * @param args Runs this example.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		serialize( new CustomType( 36 ) );
		Object o = deserialize();
		CustomType ct = (CustomType)o;
		System.out.println( ct.getI() );
	}



	/**
	 * Serialize an object into the ./tmp/SimpleSerializationExample.bin file
	 * @param o Object to serialize
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void serialize( Serializable o) throws FileNotFoundException, IOException 
	{
		try ( ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( FILE ) );)
		{
			out.writeObject( o );
		}
	}
	
	/**
	 * Deserialize an object from the ./tmp/SimpleSerializationExample.bin file
	 * @return The deserialized object
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static Object deserialize() throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		try ( ObjectInputStream out = new ObjectInputStream( new FileInputStream( FILE ) );)
		{
			return out.readObject();
		}
	}
	

}
