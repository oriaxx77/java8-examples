package com.oriaxx77.javaplay.java8features.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.oriaxx77.javaplay.Example;
import com.oriaxx77.javaplay.java8features.util.model.LineItem;
import com.oriaxx77.javaplay.java8features.util.model.Order;

@Example("Flatting multiple streams into one stream.")
public class FlatMap {
	
	public static class Node<TValue> {
		Node<TValue>[] children;
		Optional<TValue> value;
	}
	
	@Example("Stream#flatMap")
	public List<LineItem> getAllLineItems( List<Order> orders ){
		return orders
				.stream()
				.flatMap( order -> order.getLineItems().stream() )
				.collect( Collectors.toList() );
	}
	
	/**
	 * Flatten a tree structure
	 */
	private <TValue> Stream<Node<TValue>> flattenedNodes( Node<TValue> node ) {
		return Stream.concat( Stream.of( node ),
							  Arrays.stream( node.children ).filter( Objects::nonNull ).flatMap( this::flattenedNodes ));
    }
	

	public <TValue> Stream<TValue> flattenedValues( Node<TValue> node ){
		return flattenedNodes( node ).filter( n -> n.value.isPresent() ).map( n -> n.value.get() );				  
	}

}
