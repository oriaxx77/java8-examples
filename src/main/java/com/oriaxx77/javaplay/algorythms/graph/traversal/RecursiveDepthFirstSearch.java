/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.graph.traversal;


/**
 * Start at the root and follow one of the branches of  the tree
 * as far as possible till you find the searched node or you hit a leaf
 * node. If you hit a leaf node then you continue 
 * the search at the nearest ancestor.
 * 
 * @author BagyuraI
 *
 */
public class RecursiveDepthFirstSearch
{
	
	/*
	 ------------------------------ Recursive --------------------------------------------
	 dfsPreorder(v)
	 {
	 	visit v;
	 	for(each neighbour w of v)
			if(w has not been visited)
				dfsPreorder(w);
	 }
	 
	 dfsPostorder(v)
	 {
		mark v;
		for(each neighbour w of v)
		if(w is not marked)
			dfsPostorder(w);
		visit v;
	}
	 */
	
	/**
	 * Visits the graph.
	 * @param startNode starting node of the traversal.
	 * @param visitor Visitor that visits each node.
	 */
	private void visit(Node<String> startNode, Visitor<String> visitor)
	{
		if ( !startNode.isVisited() )
		{
			visitor.visit( startNode );
			for ( Node<String> child : startNode.getChildren() )
			{
				if ( !child.isVisited() )
				{
					visit( child, visitor );
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
		RecursiveDepthFirstSearch nrdfSearch = new RecursiveDepthFirstSearch();
		nrdfSearch.visit( Node.createSampleGraph(), new Visitor<String>() );
	}
	
		
}
