package com.oriaxx77.java8play.util.model;

public class Account {
	private long balance = 0;
	
	public Account(){
		this(0);
	}
	
	public Account( long balance ){
		this.balance = balance;
	}

	public long getBalance() {
		return balance;
	}

	public void addAmount( long amount ){
		this.balance += amount;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}
	
	
}
