package com.oriaxx77.java8play.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.oriaxx77.java8play.Example;

/**
 * Example for function chaining
 */
@Example("java.util.function")
public class FunctionChainingExample {
	
	@Example
	public void functionChaining() {
		add().andThen( minus5() ).apply(5, 6);
	}
	
	private BiFunction<Integer,Integer,Integer> add(){
		return (op1,op2) -> {return op1 + op2;};
	}
	
	private Function<Integer,Integer> minus5(){
		return (op1) -> op1-5;
	}
	
	
	
}
