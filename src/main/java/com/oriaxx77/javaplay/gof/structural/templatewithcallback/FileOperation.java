package com.oriaxx77.javaplay.gof.structural.templatewithcallback;

import java.io.FileInputStream;
import java.io.IOException;

public interface FileOperation 
{
	void execute( FileInputStream fis ) throws IOException; 
}
