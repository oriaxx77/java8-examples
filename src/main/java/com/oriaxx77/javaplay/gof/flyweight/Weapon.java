/**
 * 
 */
package com.oriaxx77.javaplay.gof.flyweight;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * A Weapon that uses the flyweight {@link Model} objects.
 * Many weapon can have the same animations.
 * @author BagyuraI
 *
 */
@GofExample(pattern="Flyweight", stereotype="Client")
public class Weapon
{
	/**
	 * Name of the weapon.
	 */
	private String name;
	/**
	 * Model of the weapon. It will provide the visuals and animations for the weapon.
	 */
	private Model model;
	
	/**
	 * Creates a unique weapon.
	 * @param name Name of the weapon.
	 * @param model Model of the weapon. A weapon can share model with other weapons but 
	 * can have different statistics. The model will provide the visuals of the weapon.
	 */
	public Weapon( String name, Model model )
	{
		this.name = name;
		this.model = model;
	}
	
	/**
	 * Attack animation of the weapon. It will delegate it to the {@link Model#animate(LightCondition)}
	 * The animation depends on  the light conditions. This is coming from the context.
	 * @param lights Light conditions.
	 */
	public void attack( LightCondition lights )
	{
		System.out.println("Attacking with " + name );
		model.animate( lights );
	}
}
