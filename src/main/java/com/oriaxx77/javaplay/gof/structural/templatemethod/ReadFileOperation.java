package com.oriaxx77.javaplay.gof.structural.templatemethod;

import java.io.IOException;

public class ReadFileOperation extends FileInputStreamOperation 
{

	public ReadFileOperation(String path) 
	{
		super(path);
	}

	@Override
	protected void doFileOperation() throws IOException
	{
		// read the file into a byte[]
		fis.read();
	}

}
