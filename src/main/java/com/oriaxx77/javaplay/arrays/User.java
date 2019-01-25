/**
 * 
 */
package com.oriaxx77.javaplay.arrays;

/**
 * User object that implements the {@link Comparable}.
 * @author BagyuraI
 */
public class User implements Comparable<User>
{
	/**
	 * Name attribute.
	 */
	private String name;
	/**
	 * Rank of the user
	 */
	private int rank;
	
	
	/**
	 * Constructor
	 * @param name
	 * @param rank
	 */
	public User(String name, int rank)
	{
		super();
		this.name = name;
		this.rank = rank;
	}


	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	/**
	 * Getter of the name
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}




	/**
	 * Set the value of the name to the value of name
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}




	/**
	 * Getter of the rank
	 * @return the rank
	 */
	public int getRank()
	{
		return rank;
	}




	/**
	 * Set the value of the rank to the value of rank
	 * @param rank the rank to set
	 */
	public void setRank(int rank)
	{
		this.rank = rank;
	}




	@Override
	public int compareTo(User o)
	{
		return name.compareTo( o.getName() );
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[User: name: " + name + ",rank: " + rank + "]";
	}
	
	

}
