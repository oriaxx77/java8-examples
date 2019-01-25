/**
 * 
 */
package com.oriaxx77.javaplay.generics.intro;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A simple class that shows the usefulness / purpose of generics
 * with collections.
 * 
 * @author BagyuraI
 */
public class Holders
{
	
	/**
	 * A Holder class that holds a String.
	 * It is not very flexible.
	 * @author BagyuraI
	 */
	public static class StringHolder
	{
		/**
		 * The stored data.
		 */
		private final String data;

		/**
		 * Constructor
		 * @param data Data to store
		 */
		public StringHolder(String data)
		{
			super();
			this.data = data;
		}
		
		/**
		 * Returns with the stored data.
		 * @return The stored data.
		 */
		public String get()
		{
			return data;
		}
	}
	
	/**
	 * A Holder class that can hold any type of object.
	 * When you use it you can put here anything (so no restriction)
	 * and you must use type casts. It is too generic and too permissive.
	 * You can't make restrictions. It is not type safe. You need 
	 * to remember the actual type of the store object and need to use type casts.
	 * It can lead runtime errors.
	 * 
	 * @author BagyuraI
	 */
	public static class BasicHolder
	{
		/**
		 * Data to hold.
		 */
		private final Object data;

		/**
		 * Constructor.
		 * @param data Data to store
		 */
		public BasicHolder(Object data)
		{
			super();
			this.data = data;
		}
		
		/**
		 * Return with the stored data.
		 * @return The stored data.
		 */
		public Object get()
		{
			return data;
		}
	}
	
	
	
	/**
	 * Holder with generic. It can hold any type of objects
	 * in a type-safe way. The type safety is enforced in compile time.
	 * 
	 * @author BagyuraI
	 */
	public static class GenericHolder <T>
	{
		/**
		 * Data to hold
		 */
		private final T data;

		/**
		 * Constructor
		 * @param data
		 */
		public GenericHolder(T data)
		{
			super();
			this.data = data;
		}
		
		/**
		 * Getter for {@link #data}
		 * @return
		 */
		public T get()
		{
			return data;
		}
	}
	
	
	/**
	 * Static entry point.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// It can only hold a String
		StringHolder notVeryFlexibleHolder = new StringHolder( "xyz" );
		String s = notVeryFlexibleHolder.get();
		Print.print( s );
		
		// It  can hold any objects but 
		// it is not type safe. We need to remember the type of the stored object.
		// We need to cast and it can lead runtime errors.
		BasicHolder basicHolder = new BasicHolder( "xyz" );
		s = (String)basicHolder.get();
		Print.print( s );
		
		// A generic holder.
		// It is type-safe and comple time checked.
		GenericHolder<String> genericHolder = new GenericHolder<String>( "xyz" );
		s = genericHolder.get();
		Print.print( s );
		
	}
	
}
