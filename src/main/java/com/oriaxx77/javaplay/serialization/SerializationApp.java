/**
 * 
 */
package com.oriaxx77.javaplay.serialization;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * RULE0: Implement the java.io.Serializable marker interface.
 * RULE1: When you serialize an object, the whole inheritance hierarchy is serialized till the first non-serializable class of that object.
 * RULE2: The first non-serializable class must have an empty (and accessible - usually non-private) constructor.
 * RULE3: Descendants of a serializable are serializable.
 * RULE4: writeObject( ObjectOutputStream ) and readObject( ObjectOutputStream ) can be used to modify the serialization mechanism (callbacks) of the given class (not the parent related attributes).
 * RULE5: The Externalizable interface can be used to implement a custom serializable mechanism. E.g. to serialize the non-serialzable super class attributes etc..
 * RULES6: Externalizable classes must have a public no-arg constructor
 * 
 * 
 * @author BagyuraI
 *
 */
public class SerializationApp
{
	// NOTE: A non-serialzable super class.
	//       1) It's state will not be serialized. (<i>a</i> member)
	//       2) It must have an accessible no-arg constructor for deserialization.
	private static class A
	{
		// NOTE: Since A is not serializable this member variable will not be serialized
		private String a;

		// NOTE: For deserialization the inheritance tree's first non-serializable class must have a non-arg constructor.
		protected A()
		{
			super();
		}
		
		private A( String a )
		{
			this.a = a;
		}


		public String getA() { return a; }
		public void setA( String a ){ this.a = a; }
		
		
	}
	
	// NOTE: serializable because implements java.io.Serializable
	private static class B extends A implements Serializable
	{
		// NOTE: Serial version UID. Used for version checking.
		//       Increase it if it is necessary. See the serialization doc.
		private static final long serialVersionUID = 1L;

		// NOTE: non-static and non-final instance variables will be serialized 
		private String b;
		
		// NOTE: transient field will not be serialized.
		@SuppressWarnings("unused")
		private transient String bb;
		
		// NOTE: final fields will be serialized
		@SuppressWarnings("unused")
		private final String bbb;
		
		// NOTE: static fields will not be serialized
		@SuppressWarnings("unused")
		private static String bbbb;
		
		private B( String a, String b )
		{
			super( a );
			this.b = b;
			this.bbb = "final and cannot be serialized";
		}
		
		public String getB(){ return b;}
		@SuppressWarnings("unused")
		public void setB(String b){this.b = b;}
		
		@Override
		public String toString(){ return "B{a=" + getA() + ",b=" + getB()+"}";} 
		
		
		// NOTE: If you implement a class with instance fields that is serializable and extendable,
		// there is a caution you should be aware of. If the class has invariants that would be violated 
		// if its instance fields were initialized to their default values (zero for integral types, 
		// false for boolean, and null for object reference types), you must add this readObjectNoData method to the class:
		// readObjectNoData() is used when there is no data found in the stream for
		// that data. It helps when the data stream is tampered.
		//
		// Example
		// Considering an object Monkey that extends only Object java class and that it's serialized as a 
		// file in disk with monkey.dat filename. So consider that another day, 
		// this Monkey starts extending Animal (that also implements Serializable interface) 
		// and we try to restore this file (monkey.dat). ObjectInputStream will try to recover all data that belongs to the actual class hierarchy, 
		// but there is no Animal data within this file. So, in this case readObjectNoData will be invoked, Animal is the best place to
		// implement readObjectNoData because there is no better one that knows how to be restored than Animal itself. 
		@SuppressWarnings("unused")
		private void readObjectNoData() throws InvalidObjectException 
		{
			// Throw an exception here or initialize the fields according to your constraints.
			throw new InvalidObjectException("Stream data required");
		}
	}
	
	// NOTE: It is Serializable because one of it's parenent's implements java.io.Serializable (B).
	private static class C extends B
	{
		// NOTE: Serial version UID. Used for version checking.
		//       Increase it if it is necessary. See the serialization doc.
		private static final long serialVersionUID = 1L;
		
		private String c;
		
		private C( String a, String b, String c)
		{
			super( a,b);
			this.c = c;
		}
		
		public String getC(){ return c; };
		
		@Override
		public String toString(){ return "C{a=" + getA() + ",b=" + getB() + ",c=" + getC() +"}";} 
	}
	
	// NOTE: It is Serializable because one of it's parenent's implements java.io.Serializable (B).
	private static class D extends B
	{
		// NOTE: Serial version UID. Used for version checking.
		//       Increase it if it is necessary. See the serialization doc.
		private static final long serialVersionUID = 1L;
		
		private String d;
		
		private D( String a, String b, String d)
		{
			super( a,b);
			this.d = d;
		}
		
		public String getD(){ return d; };
		
		// NOTE: The serialization mechanism will use this instead of the default method.
		private void writeObject( ObjectOutputStream out ) throws IOException
		{
			d = "meow"; 
			// NOTE: Invoke this to write the fields
			out.defaultWriteObject();
			// Serialize the inherited a field too.
			out.writeObject( this.getA() );
		}
		
		// NOTE: The serialization mechanism will use this to read the input stream instead of the standard method.
		private void readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException
		{
			// NOTE: Invoke this to read the fields
			in.defaultReadObject(); 
			if ( "meow".equals( d ) ) d = "meow meow";
			// Deserialize and initialize the inherited a.
			// It is not automatically serialized since A class is not Serializable.
			setA( (String)in.readObject());
		}
		
		@Override
		public String toString(){ return "D{a=" + getA() + ",b=" + getB() + ",d=" + getD() +"}";} 
	}
	
	// NOTE: Externalizable takes control of the whole serialization.
	// 		The serialization process only prints it's class name then the rest
	// 		is done bye E.writeExternal().
	private static class E extends A implements Externalizable
	{
		// NOTE: Serial version UID. Used for version checking.
		//       Increase it if it is necessary. See the serialization doc.
		private static final long serialVersionUID = 1L;
		
		private String e;
		
		// All extarnalizable must have an accessible no-arg constructor.
		@SuppressWarnings("unused")
		public E()
		{
			super();
		}
		
		private E( String a, String e)
		{
			super( a);
			this.e = e;
		}
		
		public String getE(){ return e; }
		public void setE(String e){this.e = e;}
		
		@Override
		public String toString(){ return "B{a=" + getA() + ",e=" + getE() +"}";}

		// NOTE: It handles the whole serialization.
		@Override
		public void writeExternal(ObjectOutput out) throws IOException
		{
			out.writeObject( getA() );
			out.writeObject( getE() );
			
		}

		// NOTE: it handles the whole deserialization.
		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException
		{
			setA( (String)in.readObject() );
			setE( (String)in.readObject() );
			
		} 
	}
	
	// NOTE: It is Serializable because one of it's parenent's implements java.io.Serializable (B).
	private static class F extends B
	{
		// NOTE: Serial version UID. Used for version checking.
		//       Increase it if it is necessary. See the serialization doc.
		private static final long serialVersionUID = 1L;
		
		private String f;
		
		private String f2;
		
		// NOTE: By default all non-static and non-transient fields are serialized.
		// 		 We can override this by specifying the serializable fields.
		private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField( "f2", String.class)};
		
		private F( String a, String b, String f, String f2)
		{
			super( a, b );
			this.f = f;
			this.f2 = f2;
		}
		
		public String getF(){ return f; }
		@SuppressWarnings("unused")
		public void setF(String f){this.f = f;}
		
		public String getF2(){ return f2; }
		@SuppressWarnings("unused")
		public void setF2(String f2){this.f2 = f2;}
		
		@Override
		public String toString(){ return "F{a=" + getA() + ",b=" + getB() + ",f=" + getF() + ",f2=" + getF2() + "}";}
	}
	
	// NOTE: It is Serializable because one of it's parenent's implements java.io.Serializable (B).
	public static class G implements Serializable, ObjectInputValidation
	{
		// NOTE: Serial version UID. Used for version checking.
		//       Increase it if it is necessary. See the serialization doc.
		private static final long serialVersionUID = 1L;
		
		private List<String> elements = new ArrayList<String>();

		
		
		// NOTE: It is Serializable because one of it's parenent's implements java.io.Serializable (B).
		private void readObject(ObjectInputStream in) throws IOException,
				ClassNotFoundException
		{
			// NOTE: It registers a validator that will be called when the whole object graph is deserialized.
			in.registerValidation(this, 0);
			in.defaultReadObject();
		}
		
		// NOTE: This validation will be called when the whole object is deserialized (the whole graph).
		@Override
		public void validateObject() throws InvalidObjectException
		{
			print( "Elements: " + elements.size() );
			if ( elements.size() < 2 )
				throw new InvalidObjectException( "It must contain at least two elements" );
			
		}
		
		public void addElement( String s )
		{
			elements.add( s );
		}
		
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		
		C c = new C( "a", "b", "c" );
		D d = new D( "a", "b", "d" );
		E e = new E( "a", "e" );
		F f = new F( "a", "b", "f", "f2");
		G g1 = new G();
		G g2 = new G();
		g2.addElement( "1" );
		g2.addElement( "2" );
		print( "orig c: " +  c );
		print( "orig d: " + d );
		print( "orig e: " + e );
		print( "orig f: " + f );
		
		try ( ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( "serial.bin" )))
		{
			oos.writeObject( c );
			oos.writeObject( d );
			oos.writeObject( e );
			oos.writeObject( f );
			oos.writeObject( g1 );
			oos.writeObject( g2 );
		}
		
		
		try ( ObjectInputStream ois = new ObjectInputStream( new FileInputStream( "serial.bin" )) )
		{
			C c2 = (C)ois.readObject();
			print( "deserialized c: " + c2 );
			D d2 = (D)ois.readObject();
			print( "deserialized d: " + d2 );
			E e2 = (E)ois.readObject();
			print( "deserialized e: " + e2 );
			F f2 = (F)ois.readObject();
			print( "deserialized f: " + f2 );
			try { G g1r = (G)ois.readObject(); 
				 print( "deserialized g1: " + g1r );} catch( InvalidObjectException ex ){ print( ex ); }
			
			G g2r = (G)ois.readObject();
			print( "deserialized g2: " + g2r );
		}
	}
}
