package com.oriaxx77.java8play.lambda;

import java.util.function.Consumer;

import com.oriaxx77.java8play.Example;

/**
 * Example for lamdba expression context capturing.
 * Run this class and watch the output then inspect the code.
 */
@Example("Lamda Expression")	
public class LambdaContextCaptureExamples {
	
	private String string = " world!";
	
	@Example
	public Consumer<String> returnConsumer() {
	    return ((s) -> {System.out.println(s);});
	}

	@Example
	public Consumer<String> returnConsumerWithInstanceVariable() {
	    return ((s) -> {System.out.println(s + string);});
	}

	@Example
	public Consumer<String> returnConsumerWithLocalFinalVariable() {
	    final String foo = " you there!";
	    return ((s) -> {System.out.println(s + foo);});
	}

	
	public static void main(String[] args) {
		LambdaContextCaptureExamples examples = new LambdaContextCaptureExamples();
		examples.returnConsumer().accept("Hello");
		examples.returnConsumerWithInstanceVariable().accept("Hello");
		examples.returnConsumerWithLocalFinalVariable().accept("Hello");
	}	
	
}
