/**
 * 
 */
package com.oriaxx77.javaplay.gof.flyweight;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * Light conditions. It comes from the context so it is an extrinsic data
 * when we want to present animations and visuals.
 * @author BagyuraI
 */
@GofExample(pattern="Flyweight", stereotype="Extrinsic Data")
public enum LightCondition
{
	DAYLIGHT,
	DARKNESS,
}
