/**
 * 
 */
package com.oriaxx77.javaplay.gof.flyweight;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * WeaponModel with a unique animation.
 * It can be applied to weapons.
 * @author BagyuraI
 */
@GofExample(pattern="Flyweight", stereotype="Concrete Flyweight")
public class WeaponModel implements Model
{
	/**
	 * Name of the weapon model.
	 */
	private String name;
	
	
	/**
	 * Creates a WeaponModel.
	 * @param name Name of the 
	 */
	public WeaponModel(String name)
	{
		super();
		this.name = name;
	}


	/**
	 * Draw the animation of the model according to the light conditions.
	 * NOTE: lightCondition is the extrinsic data.
	 * @param lightCondition light conditions.
	 */
	@Override
	public void animate(LightCondition lightCondition)
	{
		System.out.println( name + " model animation for light condition " + lightCondition );
		
	}
	
}
