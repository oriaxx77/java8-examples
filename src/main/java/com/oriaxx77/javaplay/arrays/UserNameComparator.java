/**
 * 
 */
package com.oriaxx77.javaplay.arrays;

import java.util.Comparator;

/**
 * Comparator to compare {@link User} by their name.
 * @author BagyuraI
 */
public class UserNameComparator implements Comparator<User>
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(User u1, User u2)
	{
		return u1.getName().compareTo( u2.getName() );
	}

}
