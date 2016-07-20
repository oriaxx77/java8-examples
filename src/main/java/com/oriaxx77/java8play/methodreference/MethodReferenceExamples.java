package com.oriaxx77.java8play.methodreference;

import java.util.Comparator;

import com.oriaxx77.java8play.Example;
import com.oriaxx77.java8play.util.model.Car;

@Example("Method Reference")
public class MethodReferenceExamples {
	
	
	Car getTheBetterCar( Car car1, Car car2, Comparator<Car> comparator) {
		return comparator.compare(car1, car2) >= 0 ? car1 : car2 ;
	}
	
	/**
	 * Get the better car with a lambda. 
	 */
	@Example( "Lambda" )
	Car getTheBetterCarWithLamdba() {
		return getTheBetterCar( new Car(), 
							    new Car(),
							    (car1, car2)-> car1.getPrice().compareTo(car2.getPrice()));
	}
	
	/**
	 * Get the better car with instance method reference.
	 */
	@Example( "Instance method reference" )
	Car getTheBetterCarWithMethodReference() {
		return getTheBetterCar( new Car(), 
			    				new Car(),
			    				this::compareByPrice );
	}
	
	int compareByPrice( Car car1, Car car2 ) {
		return car1.getPrice().compareTo( car2.getPrice() );
	}
	
	@Example("Static method Reference")
	Car comparatorWithStaticMethodReference() {
		return getTheBetterCar( new Car(), 
				new Car(),
				MethodReferenceExamples::compareByColor );
	}
	
	static int compareByColor( Car car1, Car car2 ) {
		return car1.getColor().compareTo( car2.getColor() );
	}
	
	
	
}
