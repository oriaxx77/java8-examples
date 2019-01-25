/**
 * 
 */
package com.oriaxx77.javaplay.exceptions.good;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * A simple Data Access Operation used by the {@link DaoTemplate#doInDb(DaoOperation)}
 * @author BagyuraI
 */
public interface DaoOperation
{
	/**
	 * A Simple DB call
	 * @param statement allow access to the DB.
	 * @throws SQLException Thrown when there are some kind of DB operation error. (SQL syntax etc...).
	 */
	public void call( Statement statement ) throws SQLException;
}
