
package com.oriaxx77.javaplay.basics.equality;






/**
 * 
 * Example to override the {@link #equals(Object)} and {@link #hashCode()} methods.
 * 
 * The theory (for the language lawyers and the mathematically inclined):
 *
 * equals()  must define an equality relation (it must be reflexive, symmetric, and transitive). In addition, it must be consistent 
 * (if the objects are not modified, then it must keep returning the same value). Furthermore, o.equals(null) must always return false.
 * hashCode() (javadoc) must also be consistent (if the object is not modified in terms of equals(), it must keep returning the same value).
 *
 * The relation between the two methods is:
 *
 * Whenever a.equals(b), then a.hashCode() must be same as b.hashCode().
 *
 * In practice:
 * 
 * If you override one, then you should override the other.
 *
 * Use the same set of fields that you use to compute equals() to compute hashCode().
 *
 * In hash map, equality is only tested within a hash bucket (The hash code returns the same).
 * 
 
 * @author BagyuraI
 */
public class Point
{
	/** X coord of the Point */
	private Integer x;
	/** Y coord of the Point */
	private Integer y;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		// They are referencing the same memory. They are equals.
		if ( this == obj ) return true;
		// Nothing can be equals to null. Return false.
		if ( obj == null ) return false;
		// They are not the same class. Do not use instanceof here because it is not symmetric.
		// It can cause problems with inheritance. E.g. B extends A. B instanceof A is true but A instanceof B is false.
		// Equals should be symmetric.
		if ( this.getClass() != obj.getClass() ) return false;
		// All non-transitive and non-static members are equals.
		Point other = (Point)obj;
		return x.equals( other.getX() ) && y.equals( other.getY() );
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		// If you override the equals() method you MUST override this.
		// In hash map, equality is only tested within a hash bucket (The hash code returns the same).
		// So if you return a constant value: E.g. 9. It's ok. Then the equals() will be used.
		
		// return 9; // It is perfectly ok. equals() will be used.
		
		// Or return the same value based on the attributes
		// This is faster than the constant value if you search in hashed containers.
		return  (x == null ? 31 : x.hashCode() )  ^ (y == null ? 37 : y.hashCode() );
	}

	/**
	 * @return the x
	 */
	public Integer getX()
	{
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Integer x)
	{
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Integer getY()
	{
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Integer y)
	{
		this.y = y;
	}
	
	

}



 