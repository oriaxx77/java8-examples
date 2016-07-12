package com.oriaxx77.java8play.defaultmethod;

import com.oriaxx77.java8play.ExampleFor;
import com.oriaxx77.java8play.util.model.Car;

@ExampleFor("Default method")
public class QualityAssuranceExample {

	@ExampleFor("Interface with default method")
	private static interface QualityAssurance<T>{
		boolean assertQuality(T t);
		@SuppressWarnings("unused") // It is for the demo
		default boolean qa(T t){
			return true;
		}
	}
	

	@ExampleFor("Extending interface with default method - using the default impl.")
	private static interface CarQualityAssurance extends QualityAssurance<Car> {
		
	}
	
	@ExampleFor( "Extending interface with default method - Redeclare the default method, which makes it abstract." )
	private static interface CarQualityAssurance2 extends QualityAssurance<Car> {
		abstract boolean qa(Car c);
	}
	
	@ExampleFor( "Extending interface with default method - Redefine the default method, which overrides it.")
	private static interface CarQualityAssurance3 extends QualityAssurance<Car> {
		default boolean qa(Car c){
			return false;
		}
	}
}
