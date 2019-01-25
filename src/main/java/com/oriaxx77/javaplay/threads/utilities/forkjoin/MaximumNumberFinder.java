/**
 * 
 */
package com.oriaxx77.javaplay.threads.utilities.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * An example to find the highest number in a list of numbers
 * with the usage of {@link RecursiveTask} and {@link ForkJoinPool}.
 * 
 * @author BagyuraI
 * 
 */
public class MaximumNumberFinder extends RecursiveTask<Integer>
{

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sequential treshold. If the provided {@link #numbers} array's length is 
	 * lower than that it will use sequential computing with the {@link #useParalellComputing()} method.
	 * Otherwise it will use parallel computing with the {@link #useParalellComputing()} method.
	 */
	private static final int SEQUENTIAL_THRESHOLD = 5;

	/**
	 * Numbers to search for the maximum.
	 */
	private final Integer[] numbers;
	/**
	 * Index of the first element in the {@link #numbers} array that 
	 * should take into account (inclusive).
	 */
	private final int start;
	/**
	 * Index of the Last element in the {@link #numbers} array that 
	 * should take into account (exclusive).
	 */
	private final int end;

	/**
	 * Constructor.
	 * @param numbers  Numbers to search for the maximum.
	 * @param start	Index of the first element in the {@link #numbers} array that 
	 *				 should take into account (inclusive).
	 * @param end	Index of the Last element in the {@link #numbers} array that 
	 * 				should take into account (exclusive).
	 */
	private MaximumNumberFinder(Integer[] numbers, int start, int end)
	{
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	/**
	 * Constructor. The {@link #start} is 0 and the {@link #end} is initialized with the 
	 * provided numbers.length.
	 * @param numbers  Numbers to search for the maximum.
	 */
	public MaximumNumberFinder(Integer[] numbers)
	{
		this(numbers, 0, numbers.length);
	}

	/**
	 * Get the maximum in the {@link #numbers} array.
	 * If the provided {@link #numbers} array's length is 
	 * lower than that it will use sequential computing with the {@link #useParalellComputing()} method.
	 * Otherwise it will use parallel computing with the {@link #useParalellComputing()} method.
	 */
	@Override
	protected Integer compute()
	{
		if ( useParalellComputing() )
		{
			return computeSequentially();
		}
		else
		{
			return computeParallel();
		}
	}

	/**
	 * Returns {@code TRUE} is {@link #start} - {@link #end} is lower then the {@value SEQUENTIAL_THRESHOLD}.
	 * @return {@code TRUE} is {@link #start} - {@link #end} is lower then the {@value SEQUENTIAL_THRESHOLD}
	 */
	private boolean useParalellComputing()
	{
		return (end - start) < SEQUENTIAL_THRESHOLD;
	}
	
	
	/**
	 * Calculation that is distributed between multiple threads.
	 * @return The result of the computation.
	 */
	private Integer computeParallel()
	{
// SOLUTION 1:
		
//		int length = end - start;
//		int split = length / 2;
//		// NOTE: create the left then call fork();
//		MaximumNumberFinder left = new MaximumNumberFinder(numbers, start, start + split);
//		left.fork();
//		// NOTE: create the right and call compute
//		MaximumNumberFinder right = new MaximumNumberFinder(numbers, start + split, end);
//		Integer rightResult = right.compute();
//		// NOTE: call left join
//		Integer leftResult = left.join();
//		return Math.max( rightResult, leftResult);
//		
		
// SOLUTION 2: 
		
		try
		{
			int length = end - start;
			int split = length / 2;
			MaximumNumberFinder left = new MaximumNumberFinder(numbers, start, start + split);
			MaximumNumberFinder right = new MaximumNumberFinder(numbers, start + split, end);
			invokeAll( left, right );
			return Math.max( right.get(), left.get() );
		}
		catch( Exception e )
		{
			throw new RuntimeException( e );
		}
		
		
	}

	/**
	 * Sequential computation.
	 * @return Result of the sequential computation.
	 */
	private Integer computeSequentially()
	{
		int max = Integer.MIN_VALUE;
		for (int i = start; i < end; i++)
		{
			if (numbers[i] > max)
			{
				max = numbers[i];
			}
		}
		return max;
	}

	/**
	 * Static entry point to run this example.
	 * @param args Command line arguments. It is not used at this moment.
	 */
	public static void main(String[] args)
	{
		// Create a random set of Integers.
		Random random = new Random();
		Integer[] numbers = new Integer[ random.nextInt( 1000 ) ];
		for (int i = 0; i < numbers.length; i++)
		{
			numbers[i] = random.nextInt( 500 );
		}

		// Create a ForkJoinPool and get the maximum 
		// of the previously created random array.
		final ForkJoinPool pool = new ForkJoinPool( 4 );
		final MaximumNumberFinder finder = new MaximumNumberFinder( numbers );
		System.out.println( "Maximum: " + pool.invoke(finder) );
	}
} 