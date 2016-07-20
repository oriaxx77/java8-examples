package com.oriaxx77.java8play.stream;

import java.util.List;
import java.util.stream.Collectors;

import com.oriaxx77.java8play.Example;
import com.oriaxx77.java8play.util.model.LineItem;
import com.oriaxx77.java8play.util.model.Order;

@Example("Flatting multiple streams into one stream.")
public class FlatMap {
	
	@Example("Stream#flatMap")
	public List<LineItem> getAllLineItems( List<Order> orders ){
		return orders
				.stream()
				.flatMap( order -> order.getLineItems().stream() )
				.collect( Collectors.toList() );
	}
}
