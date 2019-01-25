/**
 * 
 */
package com.oriaxx77.javaplay.gof.behavioral.interpreter;

import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * A variable expression. It search the context by it's {@link #name}.
 * The retieved object is an {@link Expression} that is evaluated.
 * @author BagyuraI
 *
 */
@GofExample(pattern="Interpreter", stereotype="TerminalExpression")
public class Variable implements Expression
{
	/**
	 * Name of the expression in the context.
	 * (The expression contains the 
	 */
	private String name;
	
	
	/**
	 * Creates a variable expression.
	 * @param name Name of the expression in the context.
	 */
	public Variable( String name )
	{
		super();
		this.name = name;
	}





	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.gof.behavioral.interpreter.Expression#interpret(java.util.Map)
	 */
	@Override
	public int interpret(Map<String, Expression> variables)
	{
		if ( name == null || !variables.containsKey( name ))
			return 0;
		return variables.get( name ).interpret( variables );
	}

}
