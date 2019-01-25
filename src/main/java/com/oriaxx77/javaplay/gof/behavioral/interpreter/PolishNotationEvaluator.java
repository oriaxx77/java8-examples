/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * @author BagyuraI
 *
 */
@GofExample(pattern="Interpreter", stereotype="NonterminalExpression")
public class PolishNotationEvaluator implements Expression
{
	private Expression expressionTree;
	
	
	
	/**
	 * @param expression
	 */
	public PolishNotationEvaluator(String expressionSentence)
	{
		Deque<Expression> stack = new ArrayDeque<Expression>();
		
		for ( String token : expressionSentence.split( " " ))
		{
			switch( token )
			{
				case "+" :
					Expression plus = new Plus( stack.pop(), stack.pop() ); 
					stack.push( plus );
					break;
				default:
					stack.push( new Variable(token) );
					break;
			}
		}
		expressionTree = stack.pop();
		
	}



	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.gof.behavioral.interpreter.Expression#interpret(java.util.Map)
	 */
	@Override
	public int interpret(Map<String, Expression> variables)
	{
		return expressionTree.interpret( variables );
	} 
}
