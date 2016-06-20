package com.oriaxx77.java8play.concurrent.util;

import java.util.Random;

public class CurrentThread {
	
	public static String sayHello() {
		return say( "Hello" );
	}	
	
	public static String say( String msg ){
		return msg + " from " +Thread.currentThread().getName() +" thread!";
	}
	
	public static void printHello() {
		System.out.println( sayHello() );
	}
	
	public static void print( String msg ) {
		 System.out.println( say(msg ) );
	}
	
	public static void sleep( int millis ) {
		try {
			Thread.sleep( millis );
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}
	
	public static void sleep(){
		sleep( new Random().nextInt(999)+1 );
	}
	
	public static void sleepAndPrintHello(){
		sleep();
		printHello();
	}
}
