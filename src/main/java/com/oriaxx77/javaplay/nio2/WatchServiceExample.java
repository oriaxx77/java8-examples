/**
 * 
 */
package com.oriaxx77.javaplay.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Demonstrate how to watch file related events.
 * @author BagyuraI
 */
public class WatchServiceExample 
{

	/**
	 * Runs this example
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// Create a WatchService
		WatchService watcher = FileSystems.getDefault().newWatchService();
		
		// Register the watcher
		Path file = Paths.get( "./tmp" );
		file.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
							   StandardWatchEventKinds.ENTRY_DELETE, 
							   StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);
		
		// Get the notifications
		while ( true )
		{
			// Get the key
			WatchKey watchKey = watcher.take();
			
			// Process the events
			for ( WatchEvent<?> event: watchKey.pollEvents() )
			{
				System.out.println( event.kind() );
				if ( event.context() != null )
				{
					// Get the context. It is a Path in case of certain events.
					System.out.println( event.context() + "\n\n");
				}				
			}
			
			// Reset the key
			watchKey.reset();
		}
	}

}
