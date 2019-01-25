/**
 * 
 */
package com.oriaxx77.javaplay.strings;

import java.util.Scanner;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Example program that shows how to read and write to the console 
 * in Java.
 * @author BagyuraI
 */
public class ReadFromConsole
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner stdin = new Scanner( System.in );
		Print.print( "What is your name?" );
		String name = stdin.nextLine();
		Print.print( "Hello: " + name );
		Print.print( "How old are you?" );
		int age = stdin.nextInt();
		Print.print( age );
		stdin.close();
	}

}
