package com.oriaxx77.javaplay.java8features.lambda;

import java.util.function.Consumer;

import com.oriaxx77.javaplay.Example;

/**
 * Example for lamdba expression context capturing.
 * Run this class and watch the output then inspect the code.
 */
@Example("Lamda Expression")	
public class LambdaContextCaptureExamples {
	
	private String string = " world!";
	
	@Example
	public Consumer<String> consumer() {
	    return ((s) -> {System.out.println(s);});
	}

	@Example
	public Consumer<String> consumerWithInstanceVariable() {
	    return ((s) -> {System.out.println(s + string);});
	}

	@Example
	public Consumer<String> consumerWithLocalFinalVariable() {
	    final String foo = " you there!";
	    return ((s) -> {System.out.println(s + foo);});
	}

	
	public static void main(String[] args) {
		LambdaContextCaptureExamples examples = new LambdaContextCaptureExamples();
		examples.consumer().accept("Hello");
		examples.consumerWithInstanceVariable().accept("Hello");
		examples.consumerWithLocalFinalVariable().accept("Hello");
	}	
	
}
