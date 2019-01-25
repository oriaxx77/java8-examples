/**
 * 
 */
package com.oriaxx77.javaplay.strings;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.util.HashSet;
import java.util.Set;

/**
 * It contains some common usage / exercises of the String class.
 * @author BagyuraI
 */
public class Exercises 
{

	/**
	 * A method that 
	 * returns {@code TRUE} if the provided String is a palindrome.
	 * It uses the {@link StringBuilder} class.
	 * @param s String to examine.
	 * @return {@code TRUE} if the provided String is a palindrome.
	 */
	private static boolean isPalindrome( String s )
	{
		if ( s == null ) return false;
		
		return s.equals( new StringBuilder(s).reverse().toString() );
	}
	
	/**
	 * A method that 
	 * returns {@code TRUE} if the provided String is a palindrome.
	 * It uses only the methods of the {@link String} class.
	 * @param s String to examine.
	 * @return {@code TRUE} if the provided String is a palindrome.
	 */
	private static boolean isPalindromeWoStringBuilder( String s )
	{
		if ( s == null ) return false;
		
		for ( int i = 0 ; i < s.length() / 2; i++ )
		{
			if ( s.charAt( i ) != s.charAt( s.length() - i - 1 ) )
				return false;
		}
		
		return true;
	}
	
	/**
	 * Removes the occurence of a character in a string.
	 * @param source The string to remove the character from.
	 * @param removable The character to removew.
	 * @return The cleaned string.
	 */
	private static String removeChar( String source, char removable )
	{
		if ( source == null )
			return null;
		
		// NOTE: there is no empty character, so there is no char c = ''
		// There is an empty string though.
		return source.replace( Character.toString( removable ) , "" );
	}
	
	/**
	 * Return with all permutations of the provided string.
	 * @param str String to get the permutations of
	 * @return All the permutations.
	 */
	private static Set<String> permutations( String str )
	{
		Set<String> permutations = new HashSet<String>();
		permutations( "" , str, permutations);
		return permutations;
	}
	
	/**
	 * Helper method to get the permutations of a string
	 * @param prefix
	 * @param str
	 * @param permutations
	 */
	private static void permutations( String prefix, String str, Set<String> permutations )
	{
		if ( str.length() == 0 )
		{
			if ( !permutations.contains( prefix ) )
			{
				permutations.add( prefix );
				return ;
			}
		}
		
		for ( int i = 0; i < str.length(); i++ )
		{
			permutations( prefix + str.charAt(i), str.substring(0,i) + str.substring( i+1, str.length() ), permutations );
		}
		
	}
	
	/*
	
    */
	/**
	 * Find the path of the given matrix and prints the result.
	 * 
	 * Matrix:
	 * 	
	 * +-----------x
	 * | a b c d e
	 * | f g h i j
	 * | k l m n o
	 * | p q r s t
	 * | u v w x y
	 * | z
	 * y
	 *     
	 *     Returns: 
	 *     
	 *     R - right
	 *     L - left
	 *     D - down
	 *     U - up
	 *     O - ok
	 * 
	 * @param name - the strign to find in the matrix
	 */
	private static void findPath( String name )
	{
		/*char[][] matrix = new char[][] 
			    { {'a', 'b', 'c', 'd', 'e'},
			      {'f', 'g', 'h', 'i', 'j'},
			      {'k', 'l', 'm', 'n', 'o'},
			      {'p', 'q', 'r', 's', 't'},
			      {'u', 'v', 'w', 'x', 'y'},
			      {'z'}};*/
		
		int xStart = 0, yStart = 0, xNext = 0, yNext = 0;
		StringBuilder steps = new StringBuilder();
		for ( char c : name.toCharArray() )
		{
			// Get the position
			int poz = c - 'a';
			yNext = poz / 5;
			xNext = poz % 5;
					
			if (yNext > yStart)
            { 
                for(int i = yStart; i < yNext; i++)
                {
                	steps.append("D");
                }                    
            }

            if (yNext < yStart)
            {
                for (int i = yNext; i < yStart; i++)
                {
                	steps.append("U");
                }                    
            }

            if (xNext > xStart)
            {
                for (int i = xStart; i < xNext; i++)
                {
                	steps.append("R");
                }                    
            }

            if (xNext < xStart)
            {
                for (int i = xNext; i < xStart; i++)
                {
                	steps.append("L");
                }                    
            }
            
            steps.append("O");

            xStart = xNext;
            yStart = yNext;
			
		}
		
		print( steps );
		
	}
	
	/**
	 * Runs the {@link Exercises}.
	 * @param args Command line arguments. It is not used.
	 */
	public static void main(String[] args) 
	{
		final String abba = "aBBa"; 
		
		// 1) Palindrome (using StringBuilder)?
		print( "Is " + abba + " a palindrome? " + isPalindrome( abba ) );
		
		// 2) Palindrome ( w/o StringBuilder)?
		print( "Is " + abba + " a palindrome? " + isPalindromeWoStringBuilder( abba ) );
		
		// 3) Remove a given character form a string
		print( "Remove a give character from a string. Orig:  " + abba + ", remove a, result: " + removeChar( abba, 'a' ) );
		
		// 4) Uppercase, lowercase
		print( abba + " in lowercase: " + abba.toLowerCase() + ", in uppercase: " + abba.toUpperCase() );
		
		// 5) Print permutations
		print( "Permutations of " + abba + ": " + permutations( "abc" ));
		
		// 6) Find a path in a matrix.
		findPath( "coz" );
		
	}

}
