/**
 * 
 */
package com.oriaxx77.javaplay.nio2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchService;
import java.util.List;

/**
 * Contains examples for working with files with the usage of 
 * {@link Files} and {@link WatchService} classes:
 * <ul>
 * 	<li>Create, delete, move, copy, rename files.</li>
 *  <li>Reading and writing file attributes.</li>
 *  <li>Read / write file content.</li>
 *  <li>Dealing with symbolic links.</li>
 * 	<li>Getting file change notifications.</li>
 * </ul>
 * @author BagyuraI
 */
public class WorkingWithFiles 
{
	/**
	 * Root of all files this example operates on.
	 */
	private static Path ROOT = Paths.get( "./tmp" );

	/**
	 * Example for creating a file.
	 * @param file file under the ./tmp
	 * @throws IOException
	 */
	public static void createFile( String file ) throws IOException
	{
		Files.createFile( resolveToRoot( file ) );
	}
	
	/**
	 * Example for deleting a file
	 * @param file file under the ./tmp
	 * @throws IOException
	 */
	public static void deleteFile( String file ) throws IOException 
	{
		Files.deleteIfExists( resolveToRoot( file ) );
	}
	
	/**
	 * Example for moving a file
	 * @param source Source file under the ./tmp folder
	 * @param destination Destination file under the ./tmp folder
	 * @throws IOException
	 */
	public static void moveFile( String source, String destination ) throws IOException
	{
		Files.move( resolveToRoot( source ), resolveToRoot( destination), StandardCopyOption.REPLACE_EXISTING );
	}
	
	/**
	 * Writes the <i>Hi There string</i>
	 * @param file File to write the content into. A file under the ./tmp folder
	 * @throws IOException
	 */
	public static void writeSampleText( String file ) throws IOException
	{
		try (BufferedWriter writer = Files.newBufferedWriter( resolveToRoot( file ), 
				StandardCharsets.UTF_8, StandardOpenOption.WRITE ))	
		{
			writer.write( "Hi there!" );
		}
	}

	/**
	 * Example for reading textual content from a file
	 * @param file The to read the content from. It is a file under the ./tmp folder
	 * @throws IOException
	 */
	public static void readTextContent( String file ) throws IOException
	{
		List<String> lines = Files.readAllLines( resolveToRoot(file), StandardCharsets.UTF_8);
		for ( String line : lines )
			System.out.println( line );
	}
	
	
	/**
	 * Append the path to the {@link #ROOT}.
	 * @param entry path to append
	 * @return The entry under the {@link #ROOT}
	 */
	private static Path resolveToRoot( String entry )
	{
		return ROOT.resolve( entry );
	}
	
	/**
	 * Runs this example
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		
		createFile( "MyNewFile.txt" );
		deleteFile( "MyNewFile.txt" ); 
		createFile( "MyNewFile.txt" );
		moveFile( "MyNewFile.txt" , "CopyOfMyNewFile.txt" );
		writeSampleText( "CopyOfMyNewFile.txt" );
		readTextContent( "CopyOfMyNewFile.txt" );
	}

}
