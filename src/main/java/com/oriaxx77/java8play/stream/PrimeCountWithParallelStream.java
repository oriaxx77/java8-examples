package com.oriaxx77.java8play.stream;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oriaxx77.java8play.Example;

@Example("Parallel processing with streams")
public class PrimeCountWithParallelStream {

	@Example
	public Integer getPrimeCount( int treshold ) throws InterruptedException, ExecutionException {
		final List<Integer> candidates = IntStream.range(2, treshold).boxed().collect(Collectors.toList());
		final ForkJoinPool forkJoinPool = new ForkJoinPool(5);
		final List<Integer> primeNumbers = forkJoinPool.submit(() -> candidates.parallelStream().filter( this::isPrime).
		                                                    collect(Collectors.toList())).get();
		return primeNumbers.size();
	}
	
	private boolean isPrime( int candidate ){
		for (int i = 2; i * i <= candidate; i++) {
			if (candidate % i == 0) {
			    return false;
			}
		}
		return true;
	}
	
}
