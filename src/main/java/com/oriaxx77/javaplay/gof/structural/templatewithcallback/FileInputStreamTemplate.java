package com.oriaxx77.javaplay.gof.structural.templatewithcallback;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTemplate 
{
	private String path;
	
	public FileInputStreamTemplate( String path )
	{
		this.path = path;
	}
	
	public void execute( FileOperation op ) throws IOException
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream( path );
			op.execute( fis );
		}
		finally
		{
			try { fis.close(); } catch( IOException ioex ){ /*Can do nothing*/ }
		}
	}

	

	public static void main(String[] args) throws IOException
	{
		new FileInputStreamTemplate( "some_file_path" ).execute( new FileOperation() {
			
			@Override
			public void execute(FileInputStream fis) throws IOException {
				// Do some file operation
				fis.read();
			}
		} ); 
	}
	
}

