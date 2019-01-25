package com.oriaxx77.javaplay.generics.intro;

import com.oriaxx77.javaplay.utility.Print;

/**
 * A stack using generics.
 * @author BagyuraI
 */
public class LinkedStack<T>
{
	/**
	 * A node in the stack. Holds it's data and points to the next node.
	 * @author BagyuraI
	 *
	 * @param <U> Type of the data item the node holds
	 */
	private static class Node<U>
	{
		/**
		 * Item to hold
		 */
		private U item;
		/**
		 * Next node
		 */
		private Node<U> next;
		
		/**
		 * No arg constructor
		 */
		public Node()
		{ 
			this.item = null; 
			this.next = null;
		}
		
		/**
		 * Creates a node with the given item and a reference to the next node.
		 * @param item Item to store
		 * @param next The next node
		 */
		public Node( U item, Node<U> next )
		{
			this.item = item;
			this.next = next;
		}
		
		/**
		 * Return {@code TRUE} if the item is null and there is no
		 * next node.
		 */
		public boolean end()
		{
			return item == null && next == null;
		}
	}
	
	
	/**
	 * The top node. End Sentinel.
	 */
	private Node<T> top = new Node<T>(); 
	
	/**
	 * Push an item into the top of the stack
	 * @param item
	 */
	public void push( T item )
	{
		top = new Node<T>(item, top);
	}
	
	/**
	 * Pop the top most item from the stack.
	 * @return
	 */
	public T pop()
	{
		T result = top.item;
		if ( !top.end() )
			top = top.next;
		return result;
	}
	
	/**
	 * Static entry point for demostrating the usage of LinkedStack class.
	 */
	public static void main(String[] args)
	{
		LinkedStack<String> stack = new LinkedStack<String>();
		for ( String s : "Push me into the stack".split( " " ) )
		{
			stack.push( s );
		}
		String s;
		while (  (s = stack.pop()) != null )
		{
			Print.print( s );
		}
	}
}
