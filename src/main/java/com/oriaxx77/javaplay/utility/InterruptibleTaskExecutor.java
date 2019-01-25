package com.oriaxx77.javaplay.utility;

public class InterruptibleTaskExecutor {
	
	public void exec( InterruptibleTask t )
	{
		try 
		{
			t.exec() ;
		} 
		catch(InterruptedException e ) 
		{
			Thread.currentThread().interrupt();
			throw new RuntimeException( e );
		}
	}
}


