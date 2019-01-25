/**
 * 
 */
package com.oriaxx77.javaplay.strings;

import static com.oriaxx77.javaplay.utility.Print.print;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Example of sorting non-english words.
 * In the example the spanishSort() sorts according to the Spain alphabet using
 * the {@link Collator} class. Please do not forget to take a look at the {@link Collator#setStrength(int)}
 * method.
 * @author BagyuraI
 *
 */
public class SortingNonEnglishWords
{
	/**
	 * Some spanish words
	 */
	private String[] words = { "first", "mañana", "man", "many", "Next", "maxi", "next" };
	
	/**
	 * Default sort. It will use the compareTo() method of the type of the array (now it is String).
	 * Sort the array and print the result
	 */
	private void defaultSort()
	{
		List<String> wordList = Arrays.asList( words );
		Collections.sort( wordList );
		print( wordList );
	}
	
	/**
	 * spanish sort. It will use a {@link Collator} initialized with the es {@link Locale}.
	 * Sort the array and print the result.
	 * @param collationStrength Strength of the collaction. This specifies the minimum level of differencies in characters.
	 */
	private void spanishSort( int collationStrength )
	{
		List<String> wordList = Arrays.asList( words );
		Collator esCollator = Collator.getInstance( new Locale("es") );
		esCollator.setStrength( collationStrength );
		Collections.sort( wordList, esCollator );
		print( wordList );
	}
	
	/**
	 * Spanish sort with {@link Collator#PRIMARY} strength.
	 * Only the base letters are considered a primary difference. E.g. "a" vs "b"
	 */
	private void spanishPrimarySort()
	{
		spanishSort( Collator.PRIMARY );
	}
	
	/**
	 * Spanish sort with {@link Collator#SECONDARY} strength.
	 * Includes the PRIMARY differencies and the accent differencies. E.g. accented forms of the same base
	 * letter are treated the same.
	 */
	private void spanishSecondarySort()
	{
		spanishSort( Collator.SECONDARY );
	}
	
	/**
	 * Spanish sort with {@link Collator#TERTIARY} strength.
	 * Includes the SECONDARY and PRIMARY differencies plus case diferences. E.g. a and A is the same.
	 */
	private void spanishTerytarySort()
	{
		spanishSort( Collator.TERTIARY );
	}
	
	/**
	 * As the Primary diference plus it differentiates the white spaces.
	 * Spanish sort with {@link Collator#IDENTICAL} strength.
	 */
	private void spanishIdenticalSort()
	{
		spanishSort( Collator.IDENTICAL );
	}
	
	
	/**
	 * Entry point of the app.
	 * @param args Not used.
	 */
	public static void main(String[] args)
	{
		SortingNonEnglishWords sort = new SortingNonEnglishWords();
		sort.defaultSort();
		sort.spanishPrimarySort();
		sort.spanishSecondarySort();
		sort.spanishTerytarySort();
		sort.spanishIdenticalSort();
	}

}
