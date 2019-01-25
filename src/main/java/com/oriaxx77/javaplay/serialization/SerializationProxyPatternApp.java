/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * PROBLEM: Serialization is risky (hidden bugs, security problems) because it doesn't use constructors.
 * 
 * SOLUTION: Serialize proxies instead of instances. It is called <i>serialization proxy pattern</i>.
 * 
 * In the serializtion process when we serialize an object the proxy will be serialized (orig.writeReplace) and
 * a real object will be read back (proxy.readResolve)
 * 
 * DRAWBACKS/LIMITATIONS: Cannot be used when there are circular references. Takes more memory and cpu. 
 * 
 * In summary, consider the serialization 
 * proxy pattern whenever you find yourself having to write a 
 * readObject or writeObject method on a 
 * class that is not extendable by its clients.
 * 
 * @author BagyuraI
 */
public class SerializationProxyPatternApp
{
	// NOTE: the Serializable class
	public static class A implements Serializable
	{
		// NOTE: serial version id.
		private static final long serialVersionUID = 1L;
		
		// NOTE: Because A will never be serialized this field 
		//       can be final :)
		private final String a; 
				
		private A(String a){this.a=a;}
		
		@Override
		public String toString(){ return "A{a=" + a + "}";}
		
		// NOTE: Use an instance of SerializationProxy instead of the real object.
		//       So this class will never be serialized ( the proxy will be all times)
		private Object writeReplace()
		{
			SerializationProxy serialize = new SerializationProxy( this );
			System.out.println( "A.writeReplace() is using a proxy: " + serialize);
			return serialize;
		}
		
		// NOTE: an attacker might fabricate one in an attempt to violate the class’s invariants. 
		//       To guarantee that such an attack would fail, merely 
		//       add this readObject method to the enclosing class
		private void readObject( ObjectInputStream ois ) throws InvalidObjectException
		{
			throw new InvalidObjectException( "Proxy needed" );
		}

		// NOTE the private static serializable proxy inner class.
		// 		It should also implement Serializable
		private static class SerializationProxy implements Serializable
		{
			// NOTE: serial version id.
			private static final long serialVersionUID = 1L;
			
			// NOTE: the serializable proxy has the same fields as the enclosing class
			//       but with final
			private final String a;
			
			// NOTE: the constructor get a parameter of the enclosing class.
			private SerializationProxy( A a )
			{
				this.a = a.a;
			}
			// NOTE: reading the proxy from the stream should return a real object, not the proxy
			private Object readResolve()
			{
				A deserialized = new A( a );
				System.out.println( "SerializationProxy.readResolve() is returning a real object: " + deserialized );
				return deserialized;
			}
			
			@Override
			public String toString(){ return "SerializationProxy{a=" + a + "}";}
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
		A a = new A( "aa" );
		serialize( a );
		Object o = deserialize();
		System.out.println( "The deserialized object: " + o );
	}


	private static final String FILE = "./tmp/SerializationProxyPatternApp.bin";

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
