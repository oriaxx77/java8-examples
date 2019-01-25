package com.oriaxx77.javaplay.threads.utilities.synctools.lotr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fellowship of the ring.
 * Contains the fellowship members.
 * @author BagyuraI
 */
public class Fellowship
{
	/**
	 * Fellowship members.
	 */
	private List<FellowshipMember> members = new ArrayList<FellowshipMember>();
	
	/**
	 * Creates a fellowship.
	 */
	public Fellowship()
	{
		String[] names = { "Gandalf", "Frodo", "Boromir" };
		for ( String name: names )
			members.add( new FellowshipMember( name ));
	}
	
	/**
	 * Returns {@code TRUE} if there is at least one fellowship member alive.	 * 
	 * @return {@code TRUE} if there is at least one fellowship member alive.	 *
	 */
	public boolean isLive()
	{
		for ( FellowshipMember member: members )
		{
			if ( member.isAlive() )
				return true;
		}
		return false;
	}
	
	/**
	 * Returns with the members of the fellowship.
	 * @return The members of the fellowship.
	 */
	public List<FellowshipMember> getMembers()
	{
		return Collections.unmodifiableList( members );
	}
	
}
