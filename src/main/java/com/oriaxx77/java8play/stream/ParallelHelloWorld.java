package com.oriaxx77.java8play.stream;

/** Hello world in parallel fashion */
public class ParallelHelloWorld {
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
