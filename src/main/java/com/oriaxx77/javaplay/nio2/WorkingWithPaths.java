/**
 * 
 */
package com.oriaxx77.javaplay.nio2;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that demonstrates ways of working with {@link Path} objects.
 * @author BagyuraI
 */
public class WorkingWithPaths
{
	/**
	 * Demonstrate ways to create {@link Path} objects.
	 */
	public static void createPath()
	{
		String uri =  "." ;
		
		// 1. Creating with the Paths class.
		@SuppressWarnings("unused")
		Path path = Paths.get( uri );
		// 2. Creating with the default FileSystem
		path = FileSystems.getDefault().getPath( uri );
	}
	
	/**
	 *Example for appending paths
	 */
	public static void appendPaths()
	{
		Path prefix = Paths.get( "\\uat" );
		String[] uris = { 	"C:\\dragon\\javacourse\\nio2\\readme.txt", // Resolved to C:\dragon\javacourse\nio2\readme.txt
							"", // Resolved to \ uat
							"nio2\\readme.txt" // Resolved to \ uat\nio2\readme.txt
						};
		
		for ( String uri : uris )
		{
			Path resolved = prefix.resolve( uri );
			System.out.println( "\nPrefix: " + prefix );
			System.out.println( "Uri: " + uri );
			System.out.println( "Resolved: " + resolved );
		}
	}
	
	/**
	 * Example for removing name and symlink redundancies.
	 */
	public static void removeRedundancies()
	{
		// 1) Remove name redundancies.
		//    Prints Normalized: C:\dragon\javacourse\nio2\readme.txt
		Path path = Paths.get( "C:\\dragon\\.\\javacourse\\nio2\\readme.txt" );
		System.out.println( "\n\nNormalized: " + path.normalize() ); 
		// 2) Get the absolute path.
		//    Prints Absolute: 
		//    D:\dragon\work\JavaCourse\workspace\JSEPlay\.\javacourse\nio2\readme.txt
		path = Paths.get( ".\\javacourse\\nio2\\readme.txt" );
		System.out.println( "Absolute: " + 
							path.toAbsolutePath() ); 
		// 3) Get the absolute path w/o name redundancies.
		//    Prints Absolute and normalized: 
		//    D:\dragon\work\JavaCourse\workspace\JSEPlay\javacourse\nio2\readme.txt
		
		System.out.println( "Absolute and normalized: " + 
									path.toAbsolutePath().normalize() ); 
	}
	
	/**
	 * Example for converting a {@link File} to a {@link Path}.
	 */
	public void fileToPath()
	{
		File file = new File( "." );
		@SuppressWarnings("unused")
		Path path = file.toPath();
	}

	/**
	 * Example for converting a {@link Path} to a {@link File}.
	 */
	public void pathToFile()
	{
		Path path = Paths.get( "." );
		@SuppressWarnings("unused")
		File file =  path.toFile();
	}
	
	/**
	 * Static entry point to run this example.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		appendPaths();
		
		removeRedundancies();
	}
	
	
}
