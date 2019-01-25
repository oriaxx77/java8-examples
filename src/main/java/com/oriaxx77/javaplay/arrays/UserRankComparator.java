package com.oriaxx77.javaplay.arrays;

import java.util.Comparator;

/**
 * Comparator to compare {@link User} by their rank.
 * @author BagyuraI
 */
public class UserRankComparator implements Comparator<User>
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(User u1, User u2)
	{
		int res;
		if ( u1.getRank() > u2.getRank())
			res =  1;
		else if ( u1.getRank() == u2.getRank() )
			res = 0;
		else
			res = -1;
		return res;
	}

}
