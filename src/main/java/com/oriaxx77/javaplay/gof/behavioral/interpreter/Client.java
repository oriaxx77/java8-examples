/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * A Polish notation evaluation example for the Interpreter design pattern.
 * It uses a {@link MathExpressionEvaluator} to evaluate the 
 * following string "a b c + +" with the variables a = 1, b = 2, c = 13
 * @author BagyuraI
 */
@GofExample(pattern="Interpreter", stereotype="Client")
public class Client
{

	/**
	 * It runs the Polish notation evaluation example.
	 * It uses a {@link MathExpressionEvaluator} to evaluate the 
	 * following string "a b c + +" with the variables a = 1, b = 2, c = 13
	 * @param args
	 */
	public static void main(String[] args)
	{
		String expression = "a b c + +";
		PolishNotationEvaluator evaluator = new PolishNotationEvaluator( expression );
		Map<String,Expression> variables = new HashMap<String,Expression>();
		variables.put( "a", new Number(1) );
		variables.put( "b", new Number(2) );
		variables.put( "c", new Number(13) );
		System.out.println( evaluator.interpret( variables ));
	}

}
