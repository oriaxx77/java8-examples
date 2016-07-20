package com.oriaxx77.java8play.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oriaxx77.java8play.Example;
import com.oriaxx77.java8play.concurrent.util.CurrentThread;
import com.oriaxx77.java8play.concurrent.util.Customer;
import com.oriaxx77.java8play.concurrent.util.Route;
import com.oriaxx77.java8play.concurrent.util.Shop;

/** 
 * DISCLAIMER: the code is not clear. This is intentional.
 * The point here is to see the implementation details. The how and not the what.
 * 
 * Examples for {@link CompletableFuture}. 
 * API Doc: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
 *
 * General guidelines:
 * acceptXYZ methods return a new CompletableFuture, so it is used as an intermediary operation
 * applyXYZ methods return a new ComplatableFuture<Void>, so it is used as a termination. It accepts an action for the new future.
 * runXYZ methods return a new CompletableFuture<Void>, so it is used as a termination. It accets a runnable for the new future.
 * 
 * accept/appy/run methods come in 3 forms
 * - accpet(), apply(), run() -> These are run in the same thread.
 * - acceptAsync(), applyAsync(), runAsync() -> These run in a new thread on the JDK's default async execution facility (and thread pool).
 * - acceptAsync( Executor ), applyAsync( Executor ), runAsync( Executor ) -> These run in a new thread using the provided executor. 
 * 
 */
@Example( "java.util.concurrent.CompletableFuture")
public class CompletableFutureExamples {
	
	
	/**
	 * We crate the classic 'Hello XYZ!' string with future chaining. 
	 * Each element of the chain works with the result of the previous one.
	 * 
	 * Intermediary stages:
	 * {@link CompletableFuture#thenApply(java.util.function.Function)} runs in the same thread as the previous future operation
	 * and {@link CompletableFuture#thenApplyAsync(java.util.function.Function)} runs in a separated thred.
	 * 
	 * Final stages:
	 * {@link CompletableFuture#thenRun(Runnable)}
	 * {@link CompletableFuture#thenRunAsync(Runnable)}
	 * {@link CompletableFuture#thenAccept(java.util.function.Consumer)}
	 * {@link CompletableFuture#thenAcceptAsync(java.util.function.Consumer)}
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Example( values={ "CompletableFuture#thenApply()",
						  "CompletableFuture#thenApplyAsync()",
						  "CompletableFuture#thenAccept()",
						  "CompletableFuture#thenAcceptAsync()"} )
	public void pipes() throws InterruptedException, ExecutionException {
		
		CompletableFuture.supplyAsync( () -> "Jon" )
						 .thenApply( name -> name.toUpperCase() ) // runs in the same thread as the prev operation.
																  // after the previous operation. 
																 // the input of this operation is the output of the previous operation
						 .thenApplyAsync( name -> "Hello " + name + "!" ) // runs in a separated thread after the previous opetation
																		 // the input of this operation is the output of the previous operation.
						 .thenAcceptAsync( hello -> System.out.println( hello )); // Async terminal
	}
	
	
	
	/**
	 * We create a {@link CompletableFuture} that represents a message in the future. 
	 * The point is we do not wait for a computation etc for now. 
	 * We will call the {@link CompletableFuture#complete(Object) somewhere in the future 
	 * (When the message is here).
	 * 
	 * We use an {@link ExecutorService} to start some tasks that are waiting for this future event.
	 * We wait for some secs in the main thread, then we arrive the message.
	 * 
	 * Watch the console log.
	 * 
	 * @throws InterruptedException 
	 */
	@Example(values={"CompletableFuture.complete()",
						"CompletableFuture.get())"})
	public void waitForFutureMessage() throws InterruptedException
	{
		// 1. The msg arrived future event 
		final CompletableFuture<String> futureMessage = new CompletableFuture<String>();
		
		// 2. Start threads that are waiting for the message.
		
		// Number of consumer threads
		final int numberOfConsumers = 3;		
		// ExecutorService to start new thread. These threads will wait for the 
		// msgArrivedFuture to be completed.
		final ExecutorService executorService = Executors.newFixedThreadPool( numberOfConsumers );
		// Start 3 consumer threads
		IntStream.range(0, numberOfConsumers )
				 .forEach( i -> { executorService.execute(() ->{
					 try {
						 String msg = futureMessage.get();
						 CurrentThread.print( msg );
					 }catch( InterruptedException | ExecutionException ex ) {
						 throw new RuntimeException();
					 }
				 });});
		
		// 3. Sleep for some a sec
		Thread.sleep( 10000 );
		
		// 4. Arrive the message
		futureMessage.complete( "Hi There" );	
	}
	
	@Example("CompletableFuture.thenAccept()")
	public void waitForAllTasksToComplete() throws InterruptedException, ExecutionException {
		// 1. Create some CompletableFuture that completes randomly at some future time and returns with a message.
		List<CompletableFuture<Void>> futureResults = IntStream.range(0, 7)
												 .mapToObj( i -> CompletableFuture.runAsync( CurrentThread::printHello ) )
												 .collect( Collectors.toList());
		
		// 2. Wait for all the above CompletableFutures to complete
		CompletableFuture.allOf( futureResults.toArray( new CompletableFuture[futureResults.size()] ) )
						 .thenAccept( v ->CurrentThread.print("All finished") ).get();
		
	}
	
	@Example("CompletableFuture#anyOf()")
	public void waitForOneOfTheTasksToComplete() throws InterruptedException, ExecutionException {
		List<CompletableFuture<Void>> futureResults = IntStream.range(0, 7)
													.mapToObj( i -> CompletableFuture.runAsync( CurrentThread::sleepAndPrintHello  ))
													.collect( Collectors.toList() );
		
		CompletableFuture.anyOf( futureResults.toArray( new CompletableFuture[futureResults.size()] ))
						 .thenAccept( v -> CurrentThread.print( "OneOfThemFinished" )).get();
	}
	
	
	/**
	 * Waiting for two futures to complete then acting on their results having both of them completed.
	 * 
	 * Methods:
	 * {@link CompletableFuture#thenAcceptBoth(java.util.concurrent.CompletionStage, java.util.function.BiConsumer)}
	 * {@link CompletableFuture#thenAcceptBothAsync(java.util.concurrent.CompletionStage, java.util.function.BiConsumer)
	 * {@link CompletableFuture#runAfterBoth(java.util.concurrent.CompletionStage, Runnable)}
	 * {@link CompletableFuture#runAfterBothAsync(java.util.concurrent.CompletionStage, Runnable)}
	 * {@link CompletableFuture#thenCombine(java.util.concurrent.CompletionStage, BiFunction)}
	 * {@link CompletableFuture#thenCombineAsync(java.util.concurrent.CompletionStage, BiFunction)}
	 */
	@Example("CompletableFuture#thenAcceptBoth()")
	public void waitingForBothToComplete() {
		
		CompletableFuture<Integer> firstPartCalculation = CompletableFuture.supplyAsync( () -> {
				CurrentThread.sleep(); 
				return new Integer(5);
		}) ;
		
		CompletableFuture<Integer> secondPartCalculation = CompletableFuture.supplyAsync( () -> {
			CurrentThread.sleep();
			return new Integer(6);
		});		
		 
		firstPartCalculation.thenAcceptBoth( secondPartCalculation, 
										     (calc1Result, calc2Result) -> 
											System.out.println( calc1Result + calc2Result ) );
		
	}
	
	/**
	 * Waiting for two futures to complete then acting on their results having both of them completed.
	 * 
	 * Methods:
	 * {@link CompletableFuture#thenAcceptBoth(java.util.concurrent.CompletionStage, java.util.function.BiConsumer)}
	 * {@link CompletableFuture#thenAcceptBothAsync(java.util.concurrent.CompletionStage, java.util.function.BiConsumer)
	 * {@link CompletableFuture#runAfterBoth(java.util.concurrent.CompletionStage, Runnable)}
	 * {@link CompletableFuture#runAfterBothAsync(java.util.concurrent.CompletionStage, Runnable)}
	 * {@link CompletableFuture#thenCombine(java.util.concurrent.CompletionStage, BiFunction)}
	 * {@link CompletableFuture#thenCombineAsync(java.util.concurrent.CompletionStage, BiFunction)}
	 */
	@Example( "CompletableFuture#thenAccept()" )
	public void combineValuesOfTwoFutures( BiFunction<Customer,Shop,Route> findRoute ) {
		CompletableFuture<Customer> customerFuture = CompletableFuture.supplyAsync( () -> new Customer() );
		CompletableFuture<Shop> closestShopFuture = CompletableFuture.supplyAsync( () -> new Shop() );
		customerFuture.thenCombine( closestShopFuture , findRoute  )
					  .thenAccept( System.out::println );
		
		
				
	}
	 
	/**
	 * Wait for the first future to complete and act immediately on the result.
	 * In this example we have two teams - harvard and stanford. They work on the same problem and we need 
	 * the result of the faster team.
	 * 
	 * Methods:
	 * {@link CompletableFuture#acceptEither(java.util.concurrent.CompletionStage, java.util.function.Consumer)}
	 * {@link CompletableFuture#acceptEitherAsync(java.util.concurrent.CompletionStage, java.util.function.Consumer)}
	 * {@link CompletableFuture#acceptEitherAsync(java.util.concurrent.CompletionStage, java.util.function.Consumer, java.util.concurrent.Executor)}
	 * {@link CompletableFuture#applyToEither(java.util.concurrent.CompletionStage, java.util.function.Function)}
	 * {@link CompletableFuture#applyToEitherAsync(java.util.concurrent.CompletionStage, java.util.function.Function)}
	 * {@link CompletableFuture#applyToEitherAsync(java.util.concurrent.CompletionStage, java.util.function.Function, java.util.concurrent.Executor)}
	 * {@link CompletableFuture#runAfterEither(java.util.concurrent.CompletionStage, Runnable)}
	 * {@link CompletableFuture#runAfterEitherAsync(java.util.concurrent.CompletionStage, Runnable)}
	 * {@link CompletableFuture#runAfterEitherAsync(java.util.concurrent.CompletionStage, Runnable, java.util.concurrent.Executor)}
	 */
	@Example( values={"ComletableFuture#acceptEither()"})
	public void waitingForFirstToComplete() {
		CompletableFuture<Integer> harvardCalculation = 
				CompletableFuture.supplyAsync( () -> { CurrentThread.sleep(); return new Integer(5); } );
		CompletableFuture<Integer> stanfordCalculation = 
				CompletableFuture.supplyAsync( () -> { CurrentThread.sleep(); return new Integer(6); } );
		
		harvardCalculation.acceptEither( stanfordCalculation, System.out::println );
	}
	
	

}
