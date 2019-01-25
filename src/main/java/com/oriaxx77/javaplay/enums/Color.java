/**
 * 
 */
package com.oriaxx77.javaplay.enums;

/**
 * Enum example with:
 * <ul>
 * 	<li>Constructor</i>
 *  <li>Custom attributes</i>
 *  <li>overridden {@link #compareTo(Color)}  and {@link #toString()} methods.
 *  	Actually compareTo is final so it cannot be overridden.
 *  </li>
 * @author BagyuraI
 */
public enum Color
{
	// Enums
	/** Blue color */
	BLUE( "#0000ff" ,"BLUE" ),
	/** Green color */
	GREEN( "#006400", "GREEN" );
	
	// Fields
	/** Hex value of the color */
	private final String hex;
	/** Name of the color */
	private final String name;
	
	// Constructor
	// Enum constructor is always private
	/**
	 * Creates a Color
	 * @param hex Hex value of  the color.
	 * @param name Name of the color.
	 */
	Color( String hex, String name)
	{
		this.hex = hex;
		this.name = name;
	}
	
	/**
	 * Get the hex attribute
	 * @return
	 */
	public String getHex(){ return this.hex; }
	/**
	 * Get the name attribute
	 * @return
	 */
	public String getName(){ return this.name; }

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return "Color: " + getName() + ", hex: " + getHex();
	}
	
	
	
	
}
