/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.good;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A Data Access Object template.
 * Use the {@linkplain #doInDb(DaoOperation)} template method to write DB operations.
 * @author BagyuraI
 */
public class DaoTemplate
{
	/**
	 * Execute a DB operations.
	 * Please note that it doesn't throw any checked exceptions.
	 * @param dbOperation DB operation to execute.
	 */
	public void doInDb( DaoOperation dbOperation )
	{
		// Check if the DAO layer is enabled.
		// We do not have a real DB so enable this layer
		// if you wanna get DB related exceptions.
		// Disable this layer if you wanna demostrate checked exception usage.
		if ( DaoLayer.disabled() )
			return;
		
		Connection connection = null;
		Statement statement = null;
		try
		{
			// Do some DB operations.
			// DB operations can throw SQLException. This is checked but this is an application
			// fault. Not part of the business operation.

			connection = DriverManager.getConnection( "My Fake URL" );
			statement = connection.createStatement();
			dbOperation.call( statement );
			
		} 
		catch (SQLException e)
		{
			// 1) Log the error message properly.
			//    Developers needs this to find out/investiagte/fix this bug.
			e.printStackTrace();
			// 2) Convert it to an unchecked Exception.
			throw new RuntimeException( "SQL error", e );
		}
		finally
		{
			// 3) Gracefully exit (in case or success and in case of error) .
			try
			{
				
				if ( statement != null )
					statement.close();
				
				if ( connection != null )
					connection.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
