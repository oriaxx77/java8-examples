/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

/**
 * @author BagyuraI
 *
 */
public class MyFirstClass extends MySuperClass implements MyInterface
{
	//////////////////////////////////////
	//////// Class level member variables
	//////////////////////////////////////
	private static String classVar = "Init value MyFirstClass";
	
	//////////////////////////////////////
	//////// Class level initialization
	//////////////////////////////////////
	static{
		classVar = "Type var.";
	}
	
	//////////////////////////////////////
	//////// Instance member variables
	//////////////////////////////////////
	private String var;

	//////////////////////////////////////
	//////// Constructor
	//////////////////////////////////////
	
	/**
	 * Constructor.
	 * @param var 
	 * @param superVar
	 */
	public MyFirstClass(String var, String superVar)
	{
		// 
		super( superVar );
		this.var = var;
		System.out.println( super.var );
		System.out.println( this.var );
	}
	
	//////////////////////////////////////
	//////// Instance member methods.
	//////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see com.oriaxx77.javaplay.basics.MyInterface#x(java.lang.String)
	 */
	@Override
	public String x(String s)
	{
		return this.var + " - " + s;
	}
	
	//////////////////////////////////////
	//////// Class methods.
	//////////////////////////////////////
	
	/**
	 * Static entry point of this app.
	 * @param args Not used
	 */
	public static void main(String[] args)
	{
		// Object declaration, instantiation and initialilzation
		MyFirstClass o = new MyFirstClass( "A", "B" );
		System.out.println( MyFirstClass.classVar );
		
		// Upcasting
		MySuperClass so = (MySuperClass)o;
		System.out.println( so.var );
	}


	
}
