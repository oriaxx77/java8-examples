/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.interpreter;

import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * A Terminal expression evaluates to a number passed through the constructor.
 * It is a number operand.
 * @author BagyuraI
 *
 */
@GofExample(pattern="Interpreter", stereotype="TerminalExpression")
public class Number implements Expression
{
	/** Contains the number it evalueates to */
	private int number;
	
	/**
	 * Creates a number expression.
	 * @param number the number it evalueates to
	 */
	public Number( int number )
	{
		super();
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.gof.behavioral.interpreter.Expression#interpret(java.util.Map)
	 */
	@Override
	public int interpret(Map<String, Expression> variables)
	{
		return number;
	}

}
