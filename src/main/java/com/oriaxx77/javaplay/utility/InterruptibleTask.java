package com.oriaxx77.javaplay.utility;

@FunctionalInterface
public interface InterruptibleTask 
{
	void exec() throws InterruptedException;	
}


