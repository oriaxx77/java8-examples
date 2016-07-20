package com.oriaxx77.java8play.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.oriaxx77.java8play.Example;
import com.oriaxx77.java8play.util.model.Account;
import com.oriaxx77.java8play.util.model.Car;

@Example( "Stream reduction" )
public class StreamReduction {
	
	/**
	 * Get the Car with max price.
	 * (Reduce the stream of cars to the one with max price.
	 */
	@Example
	Optional<Car> getMaxPriceCar(){
		return getCars()
				.stream()
				.reduce((car1, car2) -> car1.getPrice() > car2.getPrice() ? car1 : car2);	    
	}
	
	/**
	 * Merge the stream of accounts into a new Account object.
	 * The balance of the new Account object is the sum of the Account objects balance on the stream.
	 */
	@Example
	Account mergeAccounts(){
		return getAccounts()
				.stream()
				.reduce( new Account(), // Identity, type is the same as the type of the objects on the stream 
						(account1, account2) -> { account1.addAmount( account2.getBalance() ); return account1; } // accumulator
						);
	}
	
	/**
	 * Sum the balance of the accounts on the stream
	 */
	@Example
	Long sumBalance(){
		return getAccounts()
				.stream()
				.reduce( 0L, // Identity. The type is different than the type of the objects on the stream
						(sum, account) ->{ return sum += account.getBalance();}, // Accumulator
						(sum1, sum2) -> { return sum1 +sum2; }// Combiner 
						);
	}
	
	
	
	
	private List<Car> getCars(){
		return Arrays.asList( new Car[]{ new Car( 5L ), new Car( 4L ), new Car( 3L ) } );
	}

	private List<Account> getAccounts(){
		return Arrays.asList( new Account[]{new Account( 5L ), new Account( 6L ), new Account( 7L ) } );
	}
	
	
}
