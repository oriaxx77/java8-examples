/**
 * 
 */
package com.oriaxx77.javaplay.annotations.builtinannotations;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * This class contains examples for the usage of the {@link Resource}, 
 * {@link PostConstruct}, {@link PreDestroy} annotations.
 * @author BagyuraI
 *
 */
@Resource(
		name="JNDI of the resource",
		mappedName="Not portable cotext specific name. do not use this.",
		shareable=true,
		lookup="It can be a link to another JNDI resource",
		type=DataSource.class /* This can be an interface */)
public class MyWebServcie 
{
	// An injected instance member variable.
	@Resource
	private DataSource myDB;
	
	@SuppressWarnings("unused")
	private DataSource myOtherDB;
	
	private Connection conn;
	
	@Resource(name="customerDB")
	private void setMyOtherDB( DataSource ds )
	{
		this.myOtherDB = ds;
	}
	
	@PostConstruct
	private void init()
	{
		try 
		{
			conn = myDB.getConnection();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException( e );
		}
	}
	
	@PreDestroy
	private void cleanUp()
	{
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException( e );
		}
	}
	
	
}
