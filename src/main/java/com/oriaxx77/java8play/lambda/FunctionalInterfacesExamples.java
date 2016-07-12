package com.oriaxx77.java8play.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.oriaxx77.java8play.ExampleFor;
import com.oriaxx77.java8play.util.model.Car;

/**
 * Examples for the java.util.function package.
 */
@ExampleFor(values={"java.util.function", 
					"java.util.function.Producer", "java.util.function.Consumer", 
					"java.util.function.Predicate", "java.util.function.Function" })
public class FunctionalInterfacesExamples {

	@ExampleFor(values={"java.util.function.Function"})	
	public void function(){
		BiFunction<Integer, Integer, Integer> add = (op1,op2) -> op1 + op2;
		@SuppressWarnings("unused") Integer result = add.apply(5,4);
	}
	
	@ExampleFor(values={"java.util.function.Supplier","java.util.function.Consumer"})
	public void producerConsumer(){
		Supplier<Date> currentDateProducer = () -> new Date();
		Consumer<Date> datePrinter = ( date ) -> { System.out.println(date); };
		Date currentDate = currentDateProducer.get();		
		datePrinter.accept( currentDate );
	}
	
	@ExampleFor(values={"java.util.function.Predicate"})	
	public void predicate(){
		Predicate<Car> isRedCar = (car) -> { return car.isRed(); };
		List<Car> cars = new ArrayList<>();
		@SuppressWarnings("unused") boolean allRed = cars.stream().allMatch( isRedCar );
	}
	
	
	
}
