/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.good;

import java.sql.SQLException;
import java.sql.Statement;


/**
 * A Data Access object to save entities in DB.
 * Please note that it just uses unchecked exceptions.
 * Applications cannot recover if e.g. the connection doesn't work, the SQL incorrect etc...
 * @author BagyuraI
 */
public class BankDao extends DaoTemplate
{

	public void save(final Account account)
	{
		
		doInDb( new DaoOperation()
		{
			@Override
			public void call(Statement statement) throws SQLException
			{
				// Create an insert or update sql from the Account here.
				// it is not detailed.
				String sql = account.toString();
				statement.execute( sql );
				
			}
		});
		
		
	}

}
