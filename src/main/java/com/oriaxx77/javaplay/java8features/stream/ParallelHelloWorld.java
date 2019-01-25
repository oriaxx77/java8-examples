package com.oriaxx77.javaplay.java8features.stream;

import com.oriaxx77.javaplay.Example;

/** Hello world in parallel fashion */
@Example( "Parallel stream processing")
public class ParallelHelloWorld {
	
	@Example
	void hello(){
		"hello".chars()
			   .parallel()
			   .mapToObj( i -> (char)i)
			   .map( c -> Character.toUpperCase(c) )
			   .forEach( this::printChar );
	}
	
	void printChar( char c ){
		System.out.println( Thread.currentThread().getName() + " - " + c );
	}
	
	
	
	public static void main(String[] args) {
		new ParallelHelloWorld().hello();	
	}
}
