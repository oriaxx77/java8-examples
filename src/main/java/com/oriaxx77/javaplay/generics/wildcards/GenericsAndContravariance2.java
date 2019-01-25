/**
 * 
 */
package com.oriaxx77.javaplay.generics.wildcards;

import java.util.Collection;

interface Sink<T>
{
	void flush( T t );
}




/**
 * Example code for generic covariance and contravariance.
 * @author BagyuraI
 */
public class GenericsAndContravariance2
{
	/**
	 * Puts all element in the <i>coll</i> into the <i>snk</i>.
	 * It is not very good because it will not work e.g. Collection<String> with Sink<Object>.
	 * @param coll elements to put into the sink
	 * @param snk the sink to flush the elements with.
	 * @return The last flushed element.
	 */
	public static <T> T writeAll( Collection<T> coll, Sink<T> snk)
	{
		T last = null;
		for ( T t : coll )
		{
			last = t;
			snk.flush( t );
		}
		return last;
	}
	
	/**
	 * Puts all element in the <i>coll</i> into the <i>snk</i>.
	 * It is not very good because though it works e.g. with Collection<String> with Sink<Object>
	 * but the return type is not correct (Object in that case and not String) so we have to cast.
	 * 
	 * @param coll elements to put into the sink
	 * @param snk the sink to flush the elements with.
	 * @return The last flushed element.
	 */
	public static <T> T writeAll2( Collection< ? extends T> coll, Sink<T> snk)
	{
		T last = null;
		for ( T t : coll )
		{
			last = t;
			snk.flush( t );
		}
		return last;
	}

	/**
	 * Puts all element in the <i>coll</i> into the <i>snk</i>.
	 * It is a good solution because it works e.g. with Collection<String> with Sink<Object>
	 * and the return type is also correct (String in that case).
	 * 
	 * @param coll elements to put into the sink
	 * @param snk the sink to flush the elements with.
	 * @return The last flushed element.
	 */
	public static <T> T writeAll3( Collection< T> coll, Sink<? super T> snk)
	{
		T last = null;
		for ( T t : coll )
		{
			last = t;
			snk.flush( t );
		}
		return last;
	}
	
	public static void main(String[] args)
	{
		Sink<Object> sink = null;
		Collection<String> coll = null;
		
		// 1. 
		// It doesn't work with 
		// public static <T> T writeAll( Collection<T> coll, Sink<T> snk)
		// Sink<String> is not Sink<Object>
		// String str = writeAll( coll, sink );
		
		// 2. 
		// It works with 
		// public static <T> T writeAll2( Collection<? extends T> coll, Sink<T> snk)
		// Sink<String> is not Sink<Object>
		// But there is a problem.
		// The return type is Object because T is object.
		// So we have to cast.
		String str = (String)writeAll2( coll, sink );
		
		// 3.
		// ? super T denotes an unknown type that is a
		// supertype of T
		// Use this to define the the write operations.
		str = writeAll3( coll, sink );
	}
	
}
