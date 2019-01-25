/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

import java.io.IOException;

import javax.net.ssl.SSLException;

/**
 * An example abstract class with an abstract method.
 * @author BagyuraI
 */
public class AbstractExamples 
{
	/**
	 * An abstract class.
	 * @author BagyuraI
	 */
	public static abstract class Connector
	{
		/** Abstract method */
		public abstract Object connect() throws IOException;
	}
	
	/**
	 * A concrete class.
	 * @author BagyuraI
	 */
	public static class HttpConnector extends Connector
	{

		/**
		 * An overridden abstract method.
		 * Note the followings:
		 * 	- Return type covariance
		 *  - More restricted Exception
		 */
		@Override
		public String connect() throws  SSLException
		{
			// TODO implement it.
			return null;
		}
	}
}
