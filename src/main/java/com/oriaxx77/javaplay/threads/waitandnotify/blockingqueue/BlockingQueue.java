/**
 * 
 */
package com.oriaxx77.javaplay.threads.waitandnotify.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A blocking queue. The {@link #put(Object)} allows to 
 * put an item into the queue. If the queue is full it will wait
 * till there is an empty space. The {@link #take()} will get the 
 * last element that was put into the queue. If the queue is empty
 * it will wait till there is at least one element in the queue.
 * 
 * @author BagyuraI
 */
public class BlockingQueue<T>
{
	/** Container for the items in the queue. */
	private Queue<T> queue = new LinkedList<T>();
	/** Capacity for the queue */
	private int capacity;
	
	
	
	/**
	 * Creates a BlockingQueue.
	 * @param capacity Capacity for the queue
	 */
	public BlockingQueue(int capacity)
	{
		if ( capacity <= 0 )
			throw new IllegalArgumentException( "capacity must be higher than 0" );
		this.capacity = capacity;
	}

	/**
	 * Put an item into the queue.
	 * If the queue full it will wait until someone removes an element
	 * and there is free space.
	 * @param item An item to be put into the queue.
	 * @throws InterruptedException The waiting is interrupted.
	 */
	public synchronized void put(T item) throws InterruptedException
	{
		while (queue.size() == capacity)
		{
			wait();
		}
		
		queue.add( item );
		notifyAll(); // Do not use notify() here because it may only wake up a writing thread. It results in a deadlock.
	}

	/**
	 * Takes an item from the queue. 
	 * If the queue is empty it will wait until an item appears.
	 * @return The last item that was put into the queue.
	 * @throws InterruptedException The waiting is interrupted.
	 */
	public synchronized T take() throws InterruptedException
	{
		while ( queue.isEmpty() )
		{
			wait();
		}
		
		T item = queue.remove();
		notifyAll(); // Do not use notify() here because it may only wake up a reading thread. It results in a deadlock.
		return item;
	}
}
