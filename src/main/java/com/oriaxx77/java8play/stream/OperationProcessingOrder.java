package com.oriaxx77.java8play.stream;

import java.util.Arrays;

import com.oriaxx77.java8play.Example;

/**
 * Example for operation processing order.
 * Each and every elements go through the pipeline independently.
 * Run the example then check the console. 
 */
@Example("Stream operation processing order")
public class OperationProcessingOrder {
	
	public static void main(String[] args) {
		Arrays.stream( new int[]{1,2,3,4,5} )
			  .map( i -> {
			  		System.out.println( "map: " + i);
			  		return i; 
			  })
			  .filter( i -> {
				  boolean filtered = i%2 == 0;
				  System.out.println( "filter: " + i + " - " + filtered);
				  return filtered;
			  })
			  .forEach( i-> System.out.println( "forEach: " + i) );
	}
	
}
