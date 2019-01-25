package com.oriaxx77.javaplay.basics.classes;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Example for dynamic binding in Java.
 * It defines a {@link Vehicle} and a sublclass of it {@link Car}.
 * There is a {@link Vehicle#start()} that starts a vehicle. This is overridden by the {@link Car} with {@link Car#start()}.
 * The class {@link C#startEngine(Vehicle) can start a vehicle.
 * The #main(String[]) method demonstrates that a {@link Car} aliased to {@link Vehicle} will use the {@link Car#start()}.
 * 
 * @author BagyuraI
 */
public class DynamicBindingExample 
{
	/** A simple Vehicle */
	private static class Vehicle
	{
		/** Starts this vehicle */
		public void start(){ Print.print( "Vehicle.start()" ); }
	}
	
	/** A specialized vehicle: Car */
	private static class Car extends Vehicle
	{

		/** Starts this car */
		@Override
		public void start() { Print.print( "Car.start()" );}
		
	}
	
	/** A class that can start a vehicle */
	private static class Driver
	{
		/** 
		 * Starts the specified vehicle
		 * @param vehicle vehicle to start 
		 */
		public void startEngine( Vehicle vehicle ) { vehicle.start(); } 
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Vehicle v = new Car(); // NOTE: static type is Vehicle, dynamic type is Car.
		new Driver().startEngine( v ); // NOTE: C.startEngine calls vehicle.start(). The runtime type is Car so it will call Car.start().
	}

	

}
