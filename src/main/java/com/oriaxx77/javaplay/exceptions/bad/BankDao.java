/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.bad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * A Data Access object to save entities in DB.
 * Please note that it uses checked {@link SQLException}.
 * Applications cannot recover if e.g. the connection doen't work, the SQL incorrect etc...
 * @author BagyuraI
 */
public class BankDao
{
	/**
	 * Saves an account
	 * @param account
	 * @throws SQLException Notice that it throws an SQLException.
	 */
	public void save(final Account account) throws SQLException
	{
		
		Connection connection = null;
		Statement statement = null;
		try
		{
			// Fake DB operations for demonstration.
			connection = DriverManager.getConnection( "My Fake URL" );
			statement = connection.createStatement();
			statement.execute( account.toString() );
		} 
		finally
		{
			
			if ( statement != null )
				statement.close();
				
			if ( connection != null )
				connection.close();
			
		}
		
		
	}

}
