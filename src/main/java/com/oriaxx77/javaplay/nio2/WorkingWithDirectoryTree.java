/**
 * 
 */
package com.oriaxx77.javaplay.nio2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Example that shows how to work with directory trees.
 * The {@link #listJavaSourceFiles(Path)} shows how to use a {@link Files#walkFileTree(Path, java.nio.file.FileVisitor)} to list all Java source files
 * with the usage of a {@link FileVisitor}.
 * @author BagyuraI
 */
public class WorkingWithDirectoryTree 
{

	/**
	 * Prints the java source files starting with their 
	 * absolute path starting from directory defined by the path parameter.
	 * @param path The root path for the search
	 * @throws IOException
	 */
	public static void listJavaSourceFiles( Path path ) throws IOException
	{
		
		Files.walkFileTree( path, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException 
			{
				if ( file.toString().endsWith( ".java") )
					System.out.println( file.toAbsolutePath().normalize() );
				return FileVisitResult.CONTINUE;
			}						
		});
	}
	
	/**
	 * Runs this example.
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		listJavaSourceFiles( Paths.get( "." ) );
	}

}
