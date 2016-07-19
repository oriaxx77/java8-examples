package com.oriaxx77.java8play.stream;

/** Hello world stream example */
public class HelloWorld {
	void helloWorld(){
		"hello".chars().mapToObj( i -> (char)i )
					   .map( c -> Character.toUpperCase(c) )
					   .forEach( System.out::println );
			    
	}
	
	public static void main(String[] args) {
		new HelloWorld().helloWorld();
	}
}
