/**
 * 
 */
package com.oriaxx77.javaplay.gof.flyweight;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * A character / item model in our mmo.
 * It contains the visuals of them, point to movie files etc.
 * The visuals depend on the actual light conditions
 * @author BagyuraI
 *
 */
@GofExample(pattern="Flyweight", stereotype="Flyweight")
public interface Model
{
	/**
	 * Visual animation of  the model.k
	 * @param lights The actual light conditions when the animation happens.
	 *         It can vary in time so it is extrinsic.
	 */
	void animate( LightCondition lights );
}
