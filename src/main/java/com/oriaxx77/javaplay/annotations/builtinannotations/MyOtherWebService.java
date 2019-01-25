/**
 * 
 */
package com.oriaxx77.javaplay.annotations.builtinannotations;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * Example for using the {@link Resources} annotation.
 * @author BagyuraI
 */
@Resources ({
	 @Resource(name="myDB", type=javax.sql.DataSource.class),
	 @Resource(name="myMQ", type=javax.sql.DataSource.class)
	})
public class MyOtherWebService 
{

}
