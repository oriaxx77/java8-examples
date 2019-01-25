/**
 * 
 */
package com.oriaxx77.javaplay.basics.classes;

/**
 * @author BagyuraI
 *
 */
public class Primitives
{
	// There are 8 primitives
	// - 4 integer types
	// - 2 floating point types
	// - 1 characher type
	// - 1 boolean type
	//
	// Primitives declared as instance variables
	// have default values. They do not have to be initialized.
	// Primitives, declared as local variables have to be initialized
	
	// Integral types

	//
	// default 0
	byte myByte; 
	
	//
	// default 0
	short myShort; //
	
	// 
	// default 0
	int myInt;
	
	//
	// default 0L
	long myLong;
	
	// Floating-point types
	
	//
	// default 0.0f
	float myFloat;
	
	//
	// default 0.0d
	double myDouble;
	
	// Character type
	// default 'u\0000'
	char myChar;
	
	// Boolean type.
	// default 0
	boolean myBoolean;
	
	@SuppressWarnings("unused") // It is for demonstration purposes
	private void localDeclaration()
	{
		// Integral types
		byte myByte = 26;  // 0x1a (hexa) 0b11010 (binary)
		short myShort = 0;
		int myInt = 0;
		long myLong = 0L;
		
		// Floating-point types
		float myFloat = 0.0f;
		double myDouble = 0.0d;
		
		// Character type
		char myChar = '\u0000';
		
		// Boolean type.
		boolean myBoolean = true;
			
	}
	
}
