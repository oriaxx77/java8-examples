package com.oriaxx77.javaplay.algorythms.graph.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * A node. It holds a T item.
 * It has a {@link #visited} flag for the examples.
 * It can have many node children.
 * 
 * @author BagyuraI
 * 
 * @param <U>
 *            Type of the data item the node holds
 */
public class Node<T>
{
	/**
	 * Item to hold
	 */
	private T item;
	/**
	 * Visited flag. It is for demonstrating the Breadth First Search and the Depth First Search traversals
	 */
	private boolean visited = false;
	/**
	 * Child nodes. TODO: call them neighbours instead?
	 */
	private List<Node<T>> children;

	/**
	 * No arg constructor
	 */
	public Node( T item)
	{
		this.item = item;
		this.children = new ArrayList<Node<T>>();
	}

	/**
	 * Creates a node with the given item and a reference to the next node.
	 * 
	 * @param item
	 *            Item to store
	 * @param next
	 *            The next node
	 */
	public Node(T item, List<Node<T>> children)
	{
		this.item = item;
		this.children = children;
	}
	
	/**
	 * Creates a node with the given item and a reference to the next node.
	 * 
	 * @param item
	 *            Item to store
	 * @param next
	 *            The next node
	 */
	public Node(T item, Node<T>[] children)
	{
		this.item = item;
		
		this.children = new ArrayList<Node<T>>();
		for ( Node<T> n : children )
		{
			this.children.add( n );
		}
	}
	
	
	
	
	/**
	 * Returns with the children.
	 * @return
	 */
	public List<Node<T>> getChildren()
	{
		return children;
	}

	/**
	 * Returns the item of the node.
	 * @return the item
	 */
	public T getItem()
	{
		return item;
	}

	/**
	 * Add a child to this node.
	 * @param n Node to add.
	 * @return The added node.
	 */
	public Node<T> addNode( Node<T> n )
	{
		children.add( n );
		return n;
	}
	
	/**
	 * Add children to this node.
	 * @param nodes Nodes to add.
	 */
	public void addNodes( Node<T>[] nodes )
	{
		for ( Node<T> n : nodes )
			addNode( n );
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited()
	{
		return visited;
	}

	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	
	
	
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Node [item=" + item + "]";
	}

	/**
	 * Creates a sample graph that can be used for examples.
	 * @return The root node of a sample graph that can be used for examples.
	 */
	@SuppressWarnings("unchecked")
	public static Node<String> createSampleGraph()
	{
		Node<String> a = new Node<String>( "a" );
		Node<String> b = new Node<String>( "b" );
		Node<String> c = new Node<String>( "c" );
		Node<String> d = new Node<String>( "d" );
		Node<String> e = new Node<String>( "e" );
		Node<String> g = new Node<String>( "g" );
		Node<String> f = new Node<String>( "f" );
		Node<String> h = new Node<String>( "h" );
		Node<String> i = new Node<String>( "i" );
		
		
		a.addNodes( new Node[]{ b, d, e }  );
		b.addNodes( new Node[]{ a, c, e }  );
		c.addNodes( new Node[]{ b, f }  );
		d.addNodes( new Node[]{ a, g}  );
		e.addNodes( new Node[]{ a, b, g }  );
		f.addNodes( new Node[]{ c }  );
		g.addNodes( new Node[]{ d, e , h}  );
		h.addNodes( new Node[]{ g, i }  );
		i.addNodes( new Node[]{ h }  );
		
		return a;
	}
	
	
}

