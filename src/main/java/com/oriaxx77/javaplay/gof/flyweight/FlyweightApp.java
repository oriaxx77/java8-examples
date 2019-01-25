/**
 * 
 */
package com.oriaxx77.javaplay.gof.flyweight;

/**
 * It starts the demo app for the Flyweight demo.
 * It creates some weapon for an mmo. The weapon animations
 * are stored in model flyweights.
 * @author BagyuraI
 */
public class FlyweightApp
{
	
	/**
	 * It starts the demo app for the Flyweight demo.
     * It creates some weapon for an mmo. The weapon animations
     * are stored in model flyweights.
	 * @param args
	 */
	public static void main(String[] args)
	{
		ModelFactory modelFactory = new ModelFactory();
		Weapon grandiosePolearm = new Weapon( "Grandiose Polearm" , 
									 modelFactory.getModel( "halberd" ) );
		
		
		grandiosePolearm.attack( LightCondition.DARKNESS );
		grandiosePolearm.attack( LightCondition.DAYLIGHT );
		
		Weapon frostbittenSpear = new Weapon( "Frostbitten Spear" , 
				 modelFactory.getModel( "halberd" ) );


		frostbittenSpear.attack( LightCondition.DARKNESS );
		frostbittenSpear.attack( LightCondition.DAYLIGHT );

		
	}
}
