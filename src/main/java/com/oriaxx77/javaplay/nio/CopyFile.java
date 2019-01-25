/**
 * 
 */
package com.oriaxx77.javaplay.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * Copies a file with {@link Channel} and {@link ByteBuffer}.
 * 
 * @author BagyuraI
 */
public class CopyFile 
{
	
	
	/**
	 * Static entry point for the app.
	 * @param args Not used
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		// Open input and output stream
		try ( FileInputStream fileInputStream = new FileInputStream( "./tmp/CopyFile_orig.txt");
			  FileOutputStream fileOutputStream = new FileOutputStream( "./tmp/CopyFile_new.txt" ); )
		{
			
			// Get the channels.
			FileChannel in = fileInputStream.getChannel();
			FileChannel out = fileOutputStream.getChannel();  
			ByteBuffer buffer = ByteBuffer.allocate( 2 );
			while ( in.read( buffer ) != -1 )
			{			
				// Prepare for reading from the buffer.
				// Limit is set to current pos, position is set to 0.
				buffer.flip(); 
				out.write( buffer );

				// Prepare for writing into the buffer.
				// position is set to 0, limit is set to capacity
				buffer.clear(); 
			}
		}
	}
}
