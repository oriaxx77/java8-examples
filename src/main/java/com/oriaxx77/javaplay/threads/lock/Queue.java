/**
 * 
 */
package com.oriaxx77.javaplay.threads.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * A queue that allows to put a value into the queue and wait until it is
 * removed from this. (So there is only a single value is there at a time)
 */
public class Queue
{

	/**
	 * Synchronization lock.
	 */
	private final Lock lock = new ReentrantLock();
	/**
	 * Not full lock condition. Indicates when the queue is not full.
	 * Use this instead of wait(),notify(),notifyAll().
	 */
	private final Condition notFull = lock.newCondition();
	/**
	 * Not empty lock condition. Indicates when the queue is not empty.
	 * Use this instead of wait(),notify(),notifyAll().
	 */
	private final Condition notEmpty = lock.newCondition();
	/**
	 * Items in the queue.
	 */
	private final Object[] items = new Object[10];
	/**
	 * Points to the subject of the put operation.
	 */
	private int putptr;
	/**
	 * Points to the subject of the take operation.
	 */
	private int takeptr; 
	/**
	 * Number of objects in the Queue.
	 */
	private int count;

	/**
	 * Put an object into the Queue if it is not full.
	 * When it is full it will wait until it is not full.
	 * 
	 * @param x
	 * @throws InterruptedException
	 */
	public void put(Object x) throws InterruptedException
	{
		// Synchronize the code with the lock.
		// Lock this code block.
		lock.lock();
		try
		{
			// If the queue is full, wait until it's not full.
			// (Someone can take objects.)
			// The notFull.await() will sleep this thread.
			while (count == items.length)
				notFull.await();
			
			// Put the object into the queue
			items[putptr] = x;
			System.out.println( "put: " + x );
			// Put pointer reached the maximum size.
			// Reset the putptr
			if (++putptr == items.length)
				putptr = 0;
			// Increase the count.
			++count;
			// Signal threads that wait for this object with invoking the take() method.
			// (Consumers).
			notEmpty.signal();
		} 
		finally
		{
			// End of synchronization.
			lock.unlock();
		}
	}

	/**
	 * Takes the top object from the queue.
	 * If it is empty it will wait until it is not empty.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public Object take() throws InterruptedException
	{
		// Lock this code execution from other threads. (Synchronize this.).
		lock.lock();
		try
		{
			// While the queue is empty sleep this thread.
			// We will sleep until it is not empty
			while (count == 0)
				notEmpty.await();
			
			// Get the top object
			Object x = items[takeptr];
			System.out.println( "take: " + x );
			// If the take pointer reached the maximum size reset this.
			if (++takeptr == items.length)
				takeptr = 0;
			// Decrease the count
			--count;
			// Wake up threads that wait for the not null queue.
			// Threads that wants to put something into the queue but it was full and they are sleeping.
			notFull.signal();
			// Return with the top object.
			return x;
		} 
		finally
		{
			lock.unlock();
		}
	}

}
