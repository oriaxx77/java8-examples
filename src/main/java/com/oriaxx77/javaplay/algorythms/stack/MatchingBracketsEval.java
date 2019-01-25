/**
 * 
 */
package com.oriaxx77.javaplay.algorythms.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluate if a given string input has proper closing bracket for every opening bracket?
 * Use a stack. 
 * 1. Iterate through the input.
 * 	1.1 Push every ( into the stack
 *  1.2 For every ) p pull the last item from the stack. If it is empty or the item is not ) then return false
 * Return true.
 * See the {@link #eval(String)} method for the implementation.
 * The question is from: http://java-success.blogspot.com.au/2012/04/java-stack-data-structure-interview.html
 * @author BagyuraI
 */
public class MatchingBracketsEval
{
	/**
	 * Left parenthesis.
	 */
	public static final char LEFT_PARENTHESIS = '(';
	/**
	 * Right parenthesis.
	 */
	public static final char RIGHT_PARENTHESIS = ')';
	
	/**
	 * Constructor.
	 */
	public MatchingBracketsEval(  )
	{
		
	}
	
	/**
	 * Eval a string. It returns true if it has only matching ( and ) brackets.
	 * @param input String to evaluate.
	 * @return  returns true if it has only matching ( and ) brackets.
	 */
	public boolean eval( String input )
	{
		if ( input.length() == 0 )
			return true;
		
		Deque<Character> stack = new ArrayDeque<Character>();
		
		for ( int i = 0; i <  input.length(); i++ )
		{
			Character c = input.charAt( i );
			if ( c.equals( LEFT_PARENTHESIS ) )
			{
				stack.push( c );
			}
			else if ( c.equals( RIGHT_PARENTHESIS ))
			{
				if ( stack.isEmpty() || !stack.pollFirst().equals(LEFT_PARENTHESIS) ) 
					return false;
			}
		}
		
		return stack.isEmpty();
		
	}
	
	/**
	 * Runs this example. It tries to evaluate the following strings: "a(b(c()));", "a(b(c())));", "ab(c()));"
	 * If the algorythm is good then it should return true, false, false.
	 * @param args
	 */
	public static void main(String[] args)
	{
		MatchingBracketsEval eval = new MatchingBracketsEval();
		String inputs[] = { "a(b(c()));", "a((b(c());", "ab(c()));"}; 
		for ( String input : inputs )
			System.out.println( "input - " + eval.eval( input )  );
		
		
	}
}
