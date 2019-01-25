/**
 * 
 */
package com.oriaxx77.javaplay.nio2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Example that shows how to work with directory entries.
 * The {@link #listJavaSourceFiles(Path)} shows how to use a {@link DirectoryStream} to filter files in a directory.
 * @author BagyuraI
 */
public class WorkingWithDirectoryEntries 
{

	/**
	 * Prints the java source files starting with their 
	 * absolute path starting from directory defined by the path parameter.
	 * @param path The root path for the search
	 * @throws IOException
	 */
	public static void listJavaSourceFiles( Path path ) throws IOException
	{
		
		try( DirectoryStream<Path> currentFiles = Files.newDirectoryStream( path, 
				new Filter<Path>() 
				{
						@Override
						public boolean accept(Path entry) throws IOException 
						{
							return ( entry.toFile().isDirectory() || 
									entry.toFile().getName().endsWith( ".java") ) ;
						}
					}
		
				) )
		{
			for ( Path entry: currentFiles )
			{
				if ( entry.toFile().isDirectory() )
				{
					listJavaSourceFiles( entry );
				}
				else
				{
					System.out.println( entry.toAbsolutePath().normalize() );
				}
				
			}
		}
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
