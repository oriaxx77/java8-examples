package com.oriaxx77.java8play.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oriaxx77.java8play.ExampleFor;
import com.oriaxx77.java8play.concurrent.util.CurrentThread;

/** 
 * DISCLAIMER: the code is not clear. This is intentional.
 * The point here is to see the implementation details. The how and not the what.
 * 
 * Examples for {@link CompletableFuture}. 
 * API Doc: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
 * 
 */
@ExampleFor( "java.util.concurrent.CompletableFuture")
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
	@ExampleFor( values={ "CompletableFuture#thenApply()",
						  "CompletableFuture#thenApplyAsync()",
						  "CompletableFuture#thenAccept()",
						  "CompletableFuture#thenAcceptAsync()",
						  "CompletableFuture#thenRun",
						  "CompletableFuture#thenRunAsync()"} )
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
	@ExampleFor(values={"CompletableFuture.complete()",
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
	
	@ExampleFor("CompletableFuture.thenAccept()")
	private void waitForAllTasksToComplete() throws InterruptedException, ExecutionException {
		// 1. Create some CompletableFuture that completes randomly at some future time and returns with a message.
		List<CompletableFuture<Void>> futureResults = IntStream.range(0, 7)
												 .mapToObj( i -> CompletableFuture.runAsync( CurrentThread::printHello ) )
												 .collect( Collectors.toList());
		
		// 2. Wait for all the above CompletableFutures to complete
		CompletableFuture.allOf( futureResults.toArray( new CompletableFuture[futureResults.size()] ) )
						 .thenAccept( v ->CurrentThread.print("All finished") ).get();
		
	}
	
	@ExampleFor("CompletableFuture.anyOf()")
	private void waitForOneOfTheTasksToComplete() throws InterruptedException, ExecutionException {
		List<CompletableFuture<Void>> futureResults = IntStream.range(0, 7)
													.mapToObj( i -> CompletableFuture.runAsync( CurrentThread::sleepAndPrintHello  ))
													.collect( Collectors.toList() );
		
		CompletableFuture.anyOf( futureResults.toArray( new CompletableFuture[futureResults.size()] ))
						 .thenAccept( v -> CurrentThread.print( "OneOfThemFinished" )).get();
	}
	
	
	 
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// CompletableFutureExamples examples = new CompletableFutureExamples();
		// examples.waitForFutureMessage();
		// examples.waitForAllTasksToComplete();
		// examples.waitForOneOfTheTasksToComplete();
		// Thread.sleep(100000);
		// System.exit(1);
	}

}
