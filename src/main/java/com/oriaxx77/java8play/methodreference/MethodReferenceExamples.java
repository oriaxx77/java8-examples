package com.oriaxx77.java8play.methodreference;

import java.util.Comparator;

import com.oriaxx77.java8play.ExampleFor;
import com.oriaxx77.java8play.util.model.Car;

@ExampleFor("Method Reference")
public class MethodReferenceExamples {
	
	
	@ExampleFor("Method Reference")
	Car getTheBetterCarWithLamdba() {
		return getTheBetterCar( new Car(), 
							    new Car(),
							    (car1, car2)-> car1.getPrice().compareTo(car2.getPrice()));
	}
	
	Car getTheBetterCar( Car car1, Car car2, Comparator<Car> comparator) {
		return comparator.compare(car1, car2) >= 0 ? car1 : car2 ;
	}
	
	@ExampleFor("Method Reference")
	Car comparatorWithInstanceMethod() {
		return getTheBetterCar( new Car(), 
			    				new Car(),
			    				this::compareByPrice );
	}
	
	int compareByPrice( Car car1, Car car2 ) {
		return car1.getPrice().compareTo( car2.getPrice() );
	}
	
	@ExampleFor("Method Reference")
	Car comparatorWithStaticMethod() {
		return getTheBetterCar( new Car(), 
				new Car(),
				MethodReferenceExamples::compareByColor );
	}
	
	static int compareByColor( Car car1, Car car2 ) {
		return car1.getColor().compareTo( car2.getColor() );
	}
	
	
	
}
