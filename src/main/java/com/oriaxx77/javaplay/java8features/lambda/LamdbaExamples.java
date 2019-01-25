package com.oriaxx77.javaplay.java8features.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.oriaxx77.javaplay.Example;
import com.oriaxx77.javaplay.java8features.util.model.Car;

/**
 * Example for lambdas.
 * Demonstrate:
 * 	- behavioural parameterization which is CONCISE and FLEXIBLE
 */
@Example( values={"java.lang.FunctionalInterface","Lambda","Method Reference"})
public class LamdbaExamples {

	

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
	
	@Example( "Lambda" )
	public Collection<Car> filterBlueCars( Collection<Car> cars ){
		return filterCars( cars, car -> "blue".equals(car.getColor()));
	}
	
	@Example( "Method reference" )
	public Collection<Car> filterRedCars( Collection<Car> cars ){
		return filterCars( cars, Car::isRed );
	}
	
	
	@Example( "Lambda" )
	public Collection<Car>  filterYellowCars( Collection<Car> cars ){
		CarFilterPredicate yellowCars = car -> "yellow".equals(car.getColor());
		return filterCars( cars, yellowCars );		
	}
	
	@Example( "Lambda" )
	public Collection<Car> filterBlackCars( Collection<Car> cars ){		
		CarFilterPredicate blackCars = (Car car) -> { return "black".equals(car.getColor()); };
		return filterCars(cars, blackCars );
	}
	
	@Example( "java.util.function.Predicate" )
	public Collection<Car>  filterWhiteCars( Collection<Car> cars ){
		return cars.stream().filter( Car::isRed ).collect( Collectors.toList() ) ;
	}
}

