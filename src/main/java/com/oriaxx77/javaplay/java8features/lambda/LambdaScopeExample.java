package com.oriaxx77.javaplay.java8features.lambda;

import java.util.function.Consumer;

import com.oriaxx77.javaplay.Example;

/**
 * Example for lamdba expression context capturing.
 * Run this class and watch the output then inspect the code.
 */
@Example("Scopes with lambda")	
public class LambdaScopeExample {
	
	private String string = "LambdaScopeExample level string";
	
	private class Inner{
		
		private String string = "InnerClass string";
		
		void printString( String externalString ){
			
			String string = "Lamdba string";
			
			Consumer<String> consumer = ( stringToPrint ) -> {
				System.out.println( "externalString: " + externalString );
				System.out.println( "stringToPrint: " + stringToPrint );
				System.out.println( "string: " + string );
				System.out.println( "this.string: " + this.string );
				System.out.println( "Inner.this.string: " + Inner.this.string );
				System.out.println( "LambdaScopeExamples.this.string: " + LambdaScopeExample.this.string );
			};
			consumer.accept( externalString );
		}
	}
	
	
	public static void main(String[] args) {
		LambdaScopeExample example = new LambdaScopeExample();
		Inner outerClass = example.new Inner();
		outerClass.printString( "External string" );
	}	
	
}
