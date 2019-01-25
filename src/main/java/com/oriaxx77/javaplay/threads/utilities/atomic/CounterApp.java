package com.oriaxx77.javaplay.threads.utilities.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CounterApp {

	public static void main(String[] args) {
//		AtomicInteger number = new AtomicInteger( 0 );
//		IntStream.range(0, 25).forEach( i -> {
//			new Thread( () -> {
//				while( true ){
//					int currentNumber, newNumber;
//					do{						
//						currentNumber = number.get();
//						newNumber = currentNumber+1;
//						
//					}while( !number.compareAndSet( currentNumber , newNumber ));
//				}
//			} ).start();
//		} );	
		
		new IncrementerThreadFactory().createIncrementerThreads( 25 ).forEach( Thread::start );
	}
	
	private static class IncrementerThreadFactory{
		
		public Stream<Thread> createIncrementerThreads( int noOfThreads ){
			AtomicInteger integer = new AtomicInteger( 0 );
			return IntStream.range(0, noOfThreads).mapToObj( i -> new Thread( new Incrementer( integer ) ) ) ;
		}
		
	}
	
	private static class Incrementer implements Runnable {
		
		private AtomicInteger integer;
		
		public Incrementer( AtomicInteger integer ){
			this.integer = integer;
		}
		
		@Override
		public void run() {
			while ( true ) increment();
		}
		
		private void increment() {
			int currentNumber, newNumber;
			do{						
				currentNumber = integer.get();
				newNumber = currentNumber+1;		
			}while( !integer.compareAndSet( currentNumber , newNumber ));
		}
		
	}
	
	
	
}
