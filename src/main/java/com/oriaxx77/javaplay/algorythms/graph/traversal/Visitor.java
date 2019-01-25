/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.graph.traversal;

/**
 * A simple visitor that upon a visit
 * marks the visited {@link Node} as visited
 * and prints out the node onto the default output. 
 * @author BagyuraI
 *
 */
public class Visitor<T>
{
	/**
	 * Visits a node. It marks the visited {@link Node} as visited
	 * and prints out the node onto the default output. 
	 * @param node Node to visit.
	 */
	public void visit( Node<T> node )
	{
		node.setVisited( true );
		System.out.println( node + " visitid.");
	}
}
