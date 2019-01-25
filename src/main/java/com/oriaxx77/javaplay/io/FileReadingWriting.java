/**
 * 
 */
package com.oriaxx77.javaplay.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Example for writing and reading file content.
 * It uses the tmp/FileReadingWriting.txt under this project.
 * It uses the default encoding.
 * @author BagyuraI
 */
public class FileReadingWriting 
{
	/** 
	 * Default file path to read and write. 
	 */
	public static final String FILE_PATH = "tmp/FileReadingWriting.txt";
	/**
	 * Default content to write.
	 */
	public static final String FILE_CONTENT = "Akarmi"; 
	
	/**
	 * Path of the file to read/write.
	 */
	private final String filePath;
	
	/**
	 * Constructor. 
	 * @param filePath Path of the file to read/write.
	 */
	public FileReadingWriting( String filePath ) 
	{
		this.filePath = filePath;
	}
	
	/**
	 * Writes the content into the {@link #filePath}
	 * @param content Content to write.
	 * @throws IOException Thrown when any IO error occures
	 */
	public void writeFile( String content ) throws IOException
	{
		FileWriter fw = null;
		try 
		{
			fw = new FileWriter( filePath );
			fw.append( content );	
		} 
		finally
		{
			if ( fw != null )
			{
				try
				{
					fw.close();
				}
				catch( Exception ex )
				{
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Reads the conent of the {@link #filePath}
	 * @return Returns with the read content.
	 * @throws IOException Thrown when any IO error occures
	 */
	public String readFile() throws IOException
	{
		FileReader reader = null;
		char buff[] = new char[1];
		int len = -1;
		StringBuilder content = new StringBuilder();
		try
		{
			reader = new FileReader( filePath );
			while ( (len = reader.read( buff )) != -1 )
			{
				content.append( buff, 0, len );
			}
			return content.toString();
		}
		finally
		{
			if ( reader != null )
			{
				try 
				{
					reader.close();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	/**
	 * Starts this file reading / writing example.
	 * It uses the {@link #FILE_PATH} to determine the file it reads and writes.
	 * @param args Command line args. It is not used.
	 */
	public static void main(String[] args) throws IOException
	{
		FileReadingWriting rw = new FileReadingWriting( FILE_PATH );
		rw.writeFile( FILE_CONTENT );
		System.out.println( rw.readFile() );
	}

}
