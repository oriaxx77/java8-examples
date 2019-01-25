/**
 * 
 */
package com.oriaxx77.javaplay.nio;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Example for writing and reading a file with NIO. 
 * It uses {@link Channel} and {@link ByteBuffer}.
 * Notice the usage of {@link ByteBuffer#flip()} and {@link ByteBuffer#clear()}.
 * @author BagyuraI
 */
public class WriteAndReadFiles 
{
	/**
	 * Write a file with NIO. Notice the usage of {@link ByteBuffer#flip()}
	 * @throws IOException
	 */
	public static void writeFile() throws IOException
	{
		
		try ( FileOutputStream fileOutputStream = new FileOutputStream( "./tmp/WriteAndReadFiles.txt" ) )
		{
			// 0) Get a channel to write
			FileChannel channel = fileOutputStream.getChannel();
			
			// 1) Create the data
			ByteBuffer data = ByteBuffer.allocate( 1024 );
			data.put( "ÁrvíztűrőTükörfúrógép".getBytes( "UTF-8" ) );
			
			// 2) Prepare for reading the buffer.
			data.flip();
			
			// 4) Write the data
			channel.write( data ); 
		} 
		
	}
	
	/**
	 * Read a file with nio. Notice the usage of {@link ByteBuffer#clear()}
	 * @throws IOException
	 */
	public static void readFile() throws IOException
	{
		try ( FileInputStream fileInputStream = new FileInputStream( "./tmp/WriteAndReadFiles.txt" );
			  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();)
		{
			// 0) Get the channels.
			FileChannel in = fileInputStream.getChannel();
			WritableByteChannel out = Channels.newChannel( byteArrayOutputStream );
			
			// 1) Create the buffer
			ByteBuffer buffer = ByteBuffer.allocate( 3 );
			
			
			// 2) Read the data into the buffer
			
			while (  in.read( buffer ) > 0 )
			{
				// 3) Read the content from the buffer.
				buffer.flip();
				out.write( buffer );
				
				// 3) Prepare for the next read
				buffer.clear();
			}
			System.out.println( new String( byteArrayOutputStream.toByteArray(), "UTF-8" ) );
		}
	}
	
	/**
	 * Starts this example.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException 
	{
		writeFile();
		readFile();
	}
}
