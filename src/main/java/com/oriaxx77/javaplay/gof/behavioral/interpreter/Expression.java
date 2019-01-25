/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.interpreter;

import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * An Expression interface. It defines the {@link #interpret(Map)} to evaluate the expression.
 * @author BagyuraI
 */
@GofExample(pattern="Interpreter", stereotype="AbstractExpression")
public interface Expression
{
	/**
	 * Evaluates the expression to an int.
	 * @param variables The context map.
	 * @return The integer result the expression evaluates to.
	 */
	public int interpret( Map<String,Expression> variables );
}
