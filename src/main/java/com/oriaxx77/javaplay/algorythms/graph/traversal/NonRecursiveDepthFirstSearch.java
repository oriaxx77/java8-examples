/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.graph.traversal;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Start at the root and follow one of the branches of  the tree
 * as far as possible till you find the searched node or you hit a leaf
 * node. If you hit a leaf node then you continue 
 * the search at the nearest ancestor.
 * 
 * @author BagyuraI
 *
 */
public class NonRecursiveDepthFirstSearch
{
	
	/*
	  stack.push( root )
	  
	  while ( not empty stack )
	  {
	  	n <- stack.pop()
	  	if ( ! n.visited() )
	  	{
	  		visit( n );
	  		for ( every child of n )
	  		{
	  		 	if ( !child.visited() )
	  		 		stack.push( child )
	  		}
	  	}
	  }
	 */
	
	/**
	 * Stack to put the nodes.
	 */
	private Deque<Node<String>> stack = new LinkedList<Node<String>>();
	
	/**
	 * Visit a node.
	 * @param startNode First ode to visit.
	 * @param visitor The visitor that visits each.
	 */
	private void visit(Node<String> startNode, Visitor<String> visitor)
	{
		stack.push( startNode );
		while ( !stack.isEmpty() )
		{
			Node<String> node = stack.pop();
			if ( !node.isVisited())
			{
				visitor.visit( node );
				for ( Node<String> child: node.getChildren() )
				{
					if ( !child.isVisited() )
					{
						stack.push( child );
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Runs this example.
	 * @param args Command line arguments. They are not used atm.
	 */
	public static void main(String[] args)
	{
		NonRecursiveDepthFirstSearch nrdfSearch = new NonRecursiveDepthFirstSearch();
		nrdfSearch.visit( Node.createSampleGraph(), new Visitor<String>() );
	}

	
	

	
}
