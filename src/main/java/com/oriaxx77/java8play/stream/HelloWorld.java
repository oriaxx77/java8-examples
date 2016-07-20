package com.oriaxx77.java8play.stream;

import com.oriaxx77.java8play.Example;

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
