package com.oriaxx77.javaplay.java8features.methodreference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

import com.oriaxx77.javaplay.Example;
import com.oriaxx77.javaplay.java8features.util.model.Car;

@Example("Constructor Method Reference")
public class ConstructorReferenceExample {
	
	<T, SRC extends Collection<T>, DEST extends Collection<T>> 
		DEST copyElements( SRC source, Supplier<DEST> destinationFactory) 
	{
		
		DEST destination = destinationFactory.get();
		for ( T t : source ){
			destination.add( t );
		}
		return destination;	
	}
	
	@Example
	void useCopyWithLamdba(){
		copyElements( new ArrayList<Car>(), () -> new ArrayList<Car>() );
	}
	
	@Example
	void useCopyWithConstructorReference(){
		copyElements( new ArrayList<Car>(), ArrayList::new );
	}
	
	
}
