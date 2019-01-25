package com.oriaxx77.javaplay.networking.chat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import com.oriaxx77.javaplay.networking.HttpClient;

/**
 * Post messages to the http://localhost:8081/JEEServletsPlay/chat url.
 * @author BagyuraI
 */
public class PostClient
{
	
	

	/**
	 * Runs this example.
	 * @param args Command line arguments. It is not used atm.
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException
	{
		String[] msgs = { "Hi There", "Hastala vista baby", "Menjuah-juah! Horas!" };
		while ( true )
		{
			HttpClient postClient = new HttpClient( "http://localhost:8081/JEEServletsPlay/chat" );
			Map<String,Object> params = new LinkedHashMap<>();
			String msg = msgs[ new Random().nextInt( msgs.length) ] + "\n";
			System.out.print( "Posting: " + msg );
	        params.put("msg", msg );
			postClient.post( params );
			Thread.sleep( 2000 );
		}
	}
}
