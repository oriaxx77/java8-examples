/**
 * 
 */
package com.oriaxx77.javaplay.gof.flyweight;

import java.util.HashMap;
import java.util.Map;

import com.oriaxx77.javaplay.gof.GofExample;

/**
 * Factory to create and store models. It should be the only point to
 * get {@link Model} objects.
 * @author BagyuraI
 */
@GofExample(pattern="Flyweight", stereotype="FlyweightFactory")
public class ModelFactory
{
	/**
	 * Model repo
	 */
	private Map<String,Model> models = new HashMap<String,Model>();
	
	/**
	 * Creates a model factory with the available models.
	 */
	public ModelFactory( )
	{
		models.put( "halberd" , new WeaponModel( "halberd") );
		models.put( "scimitar" , new WeaponModel( "scimitar") );
	}
	
	/**
	 * Get a model from the repo. 
	 * @param modelKey Key of the model
	 * @return Model or null if there is no model under the provided key.
	 */
	public Model getModel( String modelKey )
	{
		return models.get( modelKey );
	}
}
