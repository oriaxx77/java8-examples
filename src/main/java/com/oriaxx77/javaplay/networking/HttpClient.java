package com.oriaxx77.javaplay.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * A http client that can post data to a server and get the response.
 * Create the {@link HttpClient#HttpClient(String)} with passing the target URL. 
 * Then call the {@link #post(Map)} with key-value parameter pairs. The return value
 * is the answer from the server. It uses UTF-8 for character encoding.
 * @author BagyuraI
 */
public class HttpClient
{
	/** 
	 * URL to post data to.
	 */
	private URL url;
	
	
	/**
	 * Creates a Postclient.
	 * @param url URL to post data to
	 * @throws MalformedURLException The provided url is malformed.
	 */
	public HttpClient( String url ) throws MalformedURLException
	{
		super();
		this.url = new URL( url );
	}


	/**
	 * Post the params to the endpoint specified by the {@link #url}.
	 * @param params form data.
	 * @return string response from the endpoint.
	 * @throws IOException Something went wrong with the communication.
	 */
	public String post( Map<String,Object> params ) throws IOException
	{
		HttpURLConnection conn = null;
		try 
		{
			byte[] data = getRequestPayload( params );
			conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf( data.length ) );
	        conn.setDoOutput(true);
	        conn.getOutputStream().write( data );
	        return getResponseData( conn.getInputStream() );
		
		} 
		finally
		{
			if ( conn != null )
				conn.disconnect();
		}
	}
	
	/**
	 * Converts the params map into a byte[]. URLEncode(paramName)=URLEncode(paramValue)
	 * It uses UTF-8.
	 * @param params Params to convert into a byte[].
	 * @return binary represention of the params map. 
	 * @throws UnsupportedEncodingException UTF-8 is not supported.
	 */
	private byte[] getRequestPayload( Map<String,Object> params )  throws UnsupportedEncodingException
	{
		StringBuilder data = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (data.length() != 0) data.append('&');
            data.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            data.append('=');
            data.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return data.toString().getBytes("UTF-8");
	}
	
	/**
	 * Return with the response data as a string
	 * @param is InputStream from the endpoint
	 * @return response data as a string
	 * @throws IOException
	 */
	private String getResponseData( InputStream is ) throws IOException
	{
		try( BufferedReader in = new BufferedReader(new InputStreamReader( is, "UTF-8")) ) {
			StringBuilder sb = new StringBuilder();
	        for (String s; (s = in.readLine()) != null ; sb.append( s ));
	        return sb.toString();
		}
	    
	}
	

	
}
