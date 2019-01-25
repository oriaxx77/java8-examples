/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.interpreter;

import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * A Plus expression. It evaluates to the addition of the {@link #leftOperand} and {@link #rightOperand}.
 * @author BagyuraI
 */
@GofExample(pattern="Interpreter", stereotype="NonterminalExpression")
public class Plus implements Expression
{
	/** 
	 * The left operand of the addition.
	 */
	private Expression leftOperand;
	/**
	 * The right operand of the addition.
	 */
	private Expression rightOperand;
	
	
	/**
	 * Creates an addition.
	 * @param leftOperand The left operand of the addition.
	 * @param rightOperand The right operand of the addition.
	 */
	public Plus(Expression leftOperand, Expression rightOperand)
	{
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}





	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.gof.behavioral.interpreter.Expression#interpret(java.util.Map)
	 */
	@Override
	public int interpret(Map<String, Expression> variables)
	{
		return leftOperand.interpret( variables ) + rightOperand.interpret( variables );
	}

}
