package com.oriaxx77.java8play.defaultmethod;

import com.oriaxx77.java8play.Example;
import com.oriaxx77.java8play.util.model.Car;

@Example("Default method")
public class QualityAssuranceExample {

	@Example("Interface with default method")
	private static interface QualityAssurance<T>{
		boolean assertQuality(T t);
		@SuppressWarnings("unused") // It is for the demo
		default boolean qa(T t){
			return true;
		}
	}
	

	@Example("Extending interface with default method - using the default impl.")
	private static interface CarQualityAssurance extends QualityAssurance<Car> {
		
	}
	
	@Example( "Extending interface with default method - Redeclare the default method, which makes it abstract." )
	private static interface CarQualityAssurance2 extends QualityAssurance<Car> {
		abstract boolean qa(Car c);
	}
	
	@Example( "Extending interface with default method - Redefine the default method, which overrides it.")
	private static interface CarQualityAssurance3 extends QualityAssurance<Car> {
		default boolean qa(Car c){
			return false;
		}
	}
}
