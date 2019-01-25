package com.oriaxx77.javaplay.java8features.stream;

import java.util.stream.IntStream;

import com.oriaxx77.javaplay.Example;

/** Hello world stream example */
@Example("Stream")
public class HelloWorld {
	
	@Example("Stream")
	void helloWorld(){
		
		"hello".chars().mapToObj( i -> (char)i )
					   .map( c -> Character.toUpperCase(c) )
					   .forEach( System.out::println );
			    
	}
	
	public static void main(String[] args) {
		
		
		new HelloWorld().helloWorld();
		
	}
}
