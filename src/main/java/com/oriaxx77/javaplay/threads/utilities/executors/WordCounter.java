/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Example for asynchronously counting the words in a text.
 * It uses an {@link ExecutorService} to run the {@link WordCounterCallable} in a new thread.
 * It uses the {@link Future#get()} method to wait until the counting is done.
 * @author BagyuraI
 */
public class WordCounter
{
	/**
	 * A {@link Callable} implementation that is initialized with a {@link String}.
	 * It's {@link #call()} method counts the words in the provided text. It trims the text, so leading and 
	 * trailing whitespaces are omitted.
	 * @author BagyuraI
	 */
	static private class WordCounterCallable implements Callable<Integer>
	{
		/**
		 * Text in which we count the words.
		 */
		private String text;
		
		
		/**
		 * Constructor
		 * @param word Text in which we count the words. It cannot be null.
		 */
		public WordCounterCallable(String text)		
		{
			super();
			if ( text == null )
				throw new IllegalArgumentException( "The text cannot be null." );
			this.text = text;		
		}


		/**
		 * Returns with the words in the {@link #text} field.
		 * It calls the {@link String#trim()} method on the provided text then counts the words
		 * with the <i>\\s+</i> regex.
		 * 
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Integer call() throws Exception
		{
			if ( text.trim().isEmpty() )
				return 0 ;
			
			return text.trim().split( "\\s+" ).length;
		}

	};

	/**
	 * Runs this example. It counts the words in the sentence: 
	 * <i>Hello there! This is a java concurrency example for using callable and future objects.</i>
	 * @param args command line arguments. They are not used at this moment.
	 */
	public static void main(String[] args)
	{
		String sentence = "Hello there! This is a java concurrency example for using callable and future objects.";
		WordCounterCallable wordCounter = new WordCounterCallable( sentence );
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit( wordCounter );
		try
		{
			Integer wordCount = future.get(); // NOTE: this is blocking until the the result is available. 
			System.out.println( "Word Count: " + wordCount );
		}
		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
		executor.shutdown();
		
		
	}
}
