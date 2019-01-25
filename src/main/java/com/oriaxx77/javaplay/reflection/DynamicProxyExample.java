package com.oriaxx77.javaplay.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Example for Java Dynamic Proxy. See {@link Proxy}
 */
public class DynamicProxyExample 
{
	/**
	 * Subject.
	 */
	private static interface Foo
	{
		public void doSomething();
	}
	
	/** 
	 * Real subject. 
	 * (The class to be proxied)
	 *
	 */
	private static class RealFoo implements Foo
	{
		@Override
		public void doSomething()
		{ 
			System.out.println( "Foo is doing something..." ); 
		}
	}

	/**
	 * Proxy that logs the method invocation. 
	 */
	private static class MethodLoggingInvocationHandler implements InvocationHandler
	{
		/** The proxied target */
		private Object target;
		
		/**
		 * Constructor
		 * @param target The proxied target
		 */
		public MethodLoggingInvocationHandler(Object target) 
		{
			super();
			this.target = target;
		}


		/** 
		 * Logs the method to the system out then calls the target's method.
		 * No exception handling. E.g. when method is not found.
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
		{
			System.out.println( "Invoking " + method + " on " + target );
			return method.invoke( target, args);
		}
		
	}
	
	/**
	 * Static entry point of this example.	
	 * @param args Command line args. Not used at this moment.
	 */
	public static void main(String[] args) 
	{
		Foo proxy = (Foo) Proxy.newProxyInstance( Foo.class.getClassLoader(),
                									  new Class<?>[] { Foo.class },
                									  new MethodLoggingInvocationHandler( new RealFoo() ) );
		
		proxy.doSomething();
	}
}
