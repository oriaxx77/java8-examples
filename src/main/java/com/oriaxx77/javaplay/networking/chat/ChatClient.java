package com.oriaxx77.javaplay.networking.chat;
/**
 * 
 */


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.oriaxx77.javaplay.annotations.basics.ExampleFor;

/**
 * Connects to this URL http://localhost:8081/JEEServletsPlay/chat with a keep-alive connection.
 * So it gets notifications from every msg that is sent to this address. The {@link PostClient}
 * can be used to send messages there.
 * @author BagyuraI
 */
public class ChatClient
{
	/**
	 * Runs this example.
	 * @param args Command line arguments. Starts this example.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		URL url = new URL( "http://localhost:8081/JEEServletsPlay/chat" );
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestProperty( "Connection", "keep-alive" );
		conn.setRequestMethod( "GET" );
		try( InputStream response = conn.getInputStream() )
		{
			int data;
			while ( (data = response.read()) != -1 )
				System.out.print( (char)data);
		}
	}
}
