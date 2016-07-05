package com.oriaxx77.java8play.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.oriaxx77.java8play.ExampleFor;

/**
 * Example for lambdas.
 * Demonstrate:
 * 	- behavioural parameterization which is CONCISE and FLEXIBLE
 */
@ExampleFor( values={"java.lang.FunctionalInterface","Lambda","Method Reference"})
public class CarInventoryExample {

	private static class Car{
		private String color;
		String getColor(){ return color;}
		boolean isRed(){ return "red".equals( this.color ); }
	}

	@FunctionalInterface
	private static interface CarFilterPredicate {
		boolean test(Car car);
	}

	public Collection<Car> filterCars( Collection<Car> cars, CarFilterPredicate carFilterPredicate ){
		List<Car> selectedCars = new ArrayList<Car>();
		for ( Car car: cars ){
			if (carFilterPredicate.test(car) ){
				selectedCars.add( car );
			}
		}
		return selectedCars;
	}
	
	@ExampleFor( values={"Lambda"} )
	public Collection<Car> filterBlueCars( Collection<Car> cars ){
		return filterCars( cars, car -> "blue".equals(car.getColor()));
	}
	
	@ExampleFor( values={"Lambda","Method reference"} )
	public Collection<Car> filterRedCars( Collection<Car> cars ){
		return filterCars( cars, Car::isRed );
	}
	
	
	@ExampleFor( values={"Lambda"} )
	public Collection<Car>  filterYellowCars( Collection<Car> cars ){
		CarFilterPredicate yellowCars = car -> "yellow".equals(car.getColor());
		return filterCars( cars, yellowCars );		
	}
	
	@ExampleFor( values={"Lambda"} )
	public Collection<Car> filterBlackCars( Collection<Car> cars ){		
		CarFilterPredicate blackCars = (Car car) -> { return "black".equals(car.getColor()); };
		return filterCars(cars, blackCars );
	}
	
	@ExampleFor( values={"Lambda", "Stream", "java.util.function.Predicate", "java.util.stream.Collectors" })
	public Collection<Car>  filterWhiteCars( Collection<Car> cars ){
		return cars.stream().filter( Car::isRed ).collect( Collectors.toList() ) ;
	}
}

