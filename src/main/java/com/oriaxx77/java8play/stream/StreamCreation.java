package com.oriaxx77.java8play.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreation {
	
	void intStreamCreations(){
		IntStream.of( 1,2,3);
		IntStream.range(0, 10);
		IntStream.generate( () -> new Random().nextInt() );
	}
	
	void stringToCharStream(){
		"hello".chars().mapToObj( i ->(char)i);
	}
	
	void streamFromAnytihing(){
		Stream.of( new Date(), new Date() );
	}
	
	void streamFromCollection(){
		new ArrayList<String>().stream();
	}
	
	void streamFromArray(){
		Object[] array = new Object[]{};
		Arrays.stream( array );
	}
}
