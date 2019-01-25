package com.oriaxx77.javaplay.basics.equality;

import java.util.HashMap;
import java.util.Map;

 /**
  * Example for equality and hashcode management in Maps.
  * 
  * 
  * When // public int hashCode() { return 9; } is uncommented m.size() 
  * returns 2, when it's left commented it returns three. Why?
  * You have overidden equals without overriding hashCode. 
  * You must ensure that for all cases where equals returns true for two objects, hashCode returns the same value. 
  * The hash code is a code that must be equal if two objects are equal (the converse need not be true). 
  * When you put your hard-coded value of 9 in, you satisfy the contract again.
  * In your hash map, equality is only tested within a hash bucket. 
  * Your two Monday objects should be equal, but because they are returning different hash codes, 
  * the equals method isn't even called to determine their equality - 
  * they are put straight into different buckets, 
  * and the possibility that they are equal isn't even considered.
  * 
  * 
  *  
  * When you don't override the hashCode() method, your ToDos class inherits 
  * the default hashCode() method from Object, which gives every object a distinct hash code. 
  * This means that t1 and t2 have two different hash codes, even though were you to compare them, they would be equal. 
  * Depending on the particular hashmap implementation, the map is free to store them separately (and this is in fact what happens).
  * When you do correctly override the hashCode() method to ensure that equal objects get equal hash codes, 
  * the hashmap is able to find the two equal objects and place them in the same hash bucket.
  * A better implementation would give objects that are not equal different hash codes, like this:
  * public int hashCode() {     return (day != null) ? day.hashCode() : 0; }
  */
public class MapEqualityCheck
{
	/** A ToDo object */
	private static class ToDos
	{
		/** Due date of the ToDo */
		String day;

		/**
		 * Creates a todo with the due date
		 * @param d Due date of the todo.
		 */
		private ToDos(String d)
		{
			day = d;
		}

		/**
		 * See {@link Object#equals(Object)}.
		 */
		public boolean equals(Object o)
		{
		    if ( this == o ) return true;
		    if ( o == null ) return false;
		    if ( this.getClass() != o.getClass() ) return false;
		    ToDos other = (ToDos)o;
			return this.day.equals( other.day ) ;
		} 
		// Using the default hashCode the count returns 3
		// public int hashCode() { return 9; } // count 2,

	}
	
	/** 
	 * Runs this example.
	 * @param args Command line args. It is not used atm. 
	 */
	public static void main(String[] args) 
	{    
		Map<ToDos, String> m = new HashMap<ToDos, String>();    
		ToDos t1 = new ToDos("Monday");    
	    
		ToDos t3 = new ToDos("Tuesday");    
		m.put(t1, "doLaundry");    
		m.put(t1, "payBills");    
		m.put(t3, "cleanAttic");
		
		System.out.println( "Todo size: " + m.size() );
		
		// NOTE: It returns null since the hash code of t1 and the 
		//       anonym local variable are different values.
		System.out.println( m.get( new ToDos( "Monday" ) ) );
		
	}
}


