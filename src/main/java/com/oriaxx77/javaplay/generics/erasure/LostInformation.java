/**
 * 
 */
package com.oriaxx77.javaplay.generics.erasure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oriaxx77.javaplay.utility.Print;

/**
 * Example of type erasure. The actual value of a T parameter cannot be obtained at runtime.
 * This information is lost runtime.
 * @author BagyuraI
 */

class Frob{}
class Fnorkle{}
class Quark<Q>{}
class Particle<POSITION,MOMENTUM>{}

public class LostInformation
{
	public static void main(String[] args)
	{
		List<Frob> list = new ArrayList<Frob>();
		Map<Frob,Fnorkle> map = new HashMap<Frob,Fnorkle>();
		Quark<Fnorkle> quark = new Quark<Fnorkle>();
		Particle<Long,Double> p = new Particle<Long,Double>();
		
		Print.print( Arrays.toString( list.getClass().getTypeParameters() ) );
		Print.print( Arrays.toString( map.getClass().getTypeParameters() ) );
		Print.print( Arrays.toString( quark.getClass().getTypeParameters() ) );
		Print.print( Arrays.toString( p.getClass().getTypeParameters() ) );
		
	}
}
