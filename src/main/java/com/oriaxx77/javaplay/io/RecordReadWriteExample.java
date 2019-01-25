/**
 * 
 */
package com.oriaxx77.javaplay.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 * An example that uses {@link DataInputStream} and {@link DataOutputStream}
 * to read and write a record (simple variables) from/to a file.
 * Run the {@link #main(String[]) to run this example. It uses the {@link #readFile()} and {@link #writeFile()}
 * to read and write primitive data to the tmp/RecordReadWriteExample.bin file.
 * @author BagyuraI
 *
 */
public class RecordReadWriteExample
{
	
	/** 
	 * Default file path to read and write. 
	 */
	private static final String FILE_PATH = "tmp/RecordReadWriteExample.bin";
	
	/**
	 * Creates a RecordReadWriteExample object.
	 */
	public RecordReadWriteExample()
	{
		
	}
	
	/**
	 * Writes primitive data from the tmp/RecordReadWriteExample.bin
	 * @throws IOException Some IO related erro occured.
	 */
	public void writeFile() throws IOException 
	{
		DataOutputStream dos = null;
		try
		{
			
			dos = new DataOutputStream( new FileOutputStream( FILE_PATH ) );
			for ( int i = 0; i < 3; i++ )
			{
				System.out.println( "Writing int: 5" );
				dos.writeInt( 5 );
				System.out.println( "Writing string: Hi There" );
				dos.writeUTF( "Hi There" );
				System.out.println( "Writing boolean: true" );
				dos.writeBoolean( true );
			}
		}
		finally
		{
			try
			{
				if ( dos != null )
					dos.close();
			}
			catch( IOException ioex )
			{
				ioex.printStackTrace();
			}
		}
	}
	
	/**
	 * Read primitive data from the tmp/RecordReadWriteExample.bin
	 * @throws IOException Some IO related error occured.
	 */
	public void readFile() throws IOException
	{
		DataInputStream dis = null;
		try
		{
			
			dis = new DataInputStream( new FileInputStream( FILE_PATH ));
			while ( true ) // NOTE: reading until EOFExeption.
			{
				System.out.println( "Reading int: " + dis.readInt() );
				System.out.println( "Reading String: " + dis.readUTF() );
				System.out.println( "Reading boolean: " + dis.readBoolean() );
			}
		}
		catch( EOFException eofex )
		{
			System.out.println( "End of file reached." );
		}
		finally
		{
			try
			{
				if ( dis != null )
					dis.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * It starts this app and writes some primitive variables on tmp/RecordReadWriteExample.bin,
	 * then reads the content back and writes it onto the default output.
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		RecordReadWriteExample readerWriter = new RecordReadWriteExample();
		readerWriter.writeFile();
		readerWriter.readFile();

	}

}
