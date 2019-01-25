package com.oriaxx77.javaplay.java8features.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.oriaxx77.javaplay.Example;

@Example( "Stream creation" )
public class StreamCreation {
	
	@Example
	void intStreamCreations(){
		IntStream.of( 1,2,3);
		IntStream.range(0, 10);
		IntStream.generate( () -> new Random().nextInt() );
	}
	
	@Example
	void stringToCharStream(){
		"hello".chars().mapToObj( i ->(char)i);
	}
	
	@Example
	void streamFromAnytihing(){
		Stream.of( new Date(), new Date() );
	}
	
	@Example
	void streamFromCollection(){
		new ArrayList<String>().stream();
	}
	
	@Example
	void streamFromArray(){
		Object[] array = new Object[]{};
		Arrays.stream( array );
	}
}
