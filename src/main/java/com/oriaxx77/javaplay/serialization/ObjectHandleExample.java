/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A serialization example that does the following:
 * <ol>
 * 	<li>Creates an object</li>
 *  <li>Writes it on to an ObjectOutputStream that points to the "./tmp/ObjectHandleExample.bin" file</i>
 *  <li>Changes the state of the object <li>
 *  <li>Writes it again on to same ObjectOutputStream that points to the "./tmp/ObjectHandleExample.bin" file</i>
 *  <li>Reads the objects from the stream and compares them.</li>
 * </ol>
 * 
 * When we serialize an object on to a stream, the subsequent serialization of the same object with the same stream
 * will only create object handles. So when we read the object back we will get the first state. 
 * 
 * @author BagyuraI
 *
 */
public class ObjectHandleExample 
{
	/** A serializable type */
	public static class MyType implements Serializable
	{
		/** Serial version unique identifier.*/
		private static final long serialVersionUID = 1L;
		
		/** An example serialiable field */
		private String field;
		
		/**
		 * Constructor
		 * @param field
		 */
		public MyType(String field) 
		{
			super();
			this.field = field;
		}

		/**
		 * Getter for the {@link #field}
		 * @return the field
		 */
		public String getField() 
		{
			return field;
		}

		/**
		 * Setter for the {@link #field}
		 * @param field the field to set
		 */
		public void setField(String field) 
		{
			this.field = field;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() 
		{
			return "MyType [field=" + field + "]";
		}
		
		
		
	}

	/**
	 * Runs this example.
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		
		String file = "./tmp/ObjectHandleExample.bin";
		
		
		// Serialize the same object with different state multiple times
		try ( ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( file )) ; ) 
		{
			MyType obj = new MyType( "x" );
			out.writeObject( obj );
			obj.setField( "y" );
			out.writeObject( obj );
		} 
		
		// Deserialize the object and compare them.
		try ( ObjectInputStream in = new ObjectInputStream( new FileInputStream( file )) ; ) 
		{
			MyType obj1 = (MyType)in.readObject();
			MyType obj2 = (MyType)in.readObject();
			System.out.println( obj1 );
			System.out.println( obj2 );
			System.out.println( "obj1 == obj2: " + (obj1 == obj2) );
		} 
		

	}

}
