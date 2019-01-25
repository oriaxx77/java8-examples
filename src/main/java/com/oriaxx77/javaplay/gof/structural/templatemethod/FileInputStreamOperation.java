package com.oriaxx77.javaplay.gof.structural.templatemethod;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class FileInputStreamOperation 
{
	protected String path;
	protected FileInputStream fis;
	
	public FileInputStreamOperation( String path )
	{
		this.path = path;
	}
	
	public void execute() throws IOException
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream( path );
			doFileOperation();
		}
		finally
		{
			try { fis.close(); } catch( IOException ioex ){ /*Can do nothing*/ }
		}
	}

	protected abstract void doFileOperation() throws IOException;
	
	
	
	
}
