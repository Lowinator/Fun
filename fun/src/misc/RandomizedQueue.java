

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/* http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 * 
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure. 
 */
public class RandomizedQueue<T> implements Iterable<T> {
	
	private int queueEnd = 0;	/* index of the end in the queue,
								   also the number of elements in the queue. */
	
	@SuppressWarnings("unchecked")
	private T[] queue = (T[]) new Object[1];	// array representing the queue
	
	private Random rGen = new Random(); 	// used for generating uniformly random numbers
	
	/**
	 * Changes the queue size to the specified size.
	 * @param newSize the new queue size.
	 */
	private void resize(int newSize) {
		System.out.println("Resizing from " + queue.length + " to " + newSize);
		T[] newArray = Arrays.copyOfRange(queue, 0, newSize);
		queue = newArray;
	}
	
	
	public boolean isEmpty() {
		return queueEnd == 0;
	}
	
	public int size() {
		return queueEnd;
	}
	
	/**
	 * Adds an element to the queue.
	 * @param elem the new queue entry.
	 */
	public void enqueue(T elem) {
		if (elem == null)
			throw new NullPointerException();
		
		if (queueEnd == queue.length) 
			resize(queue.length*2);
		
		queue[queueEnd++] = elem;
	}

	/**
	 * Works in constant (amortized) time.
	 * @return uniformly random entry from the queue.
	 */
	public T dequeue() {	
		if (queueEnd == 0)	// can't remove element from empty queue
			throw new UnsupportedOperationException();
		
		if (queueEnd <= queue.length/4)		// adjusts the array size if less than a quarter of it is used
			resize(queue.length/2);
		
		int index = rGen.nextInt(queueEnd);		// selects a random index
		
		T returnValue = queue[index];	/* saves the element behind the randomly selected index 
											which will be returned later */
		
		queue[index] = queue[--queueEnd];	/* fills the hole (randomly selected index is being deleted) 
											   with the last element in the queue */
		queue[queueEnd] = null;			// avoids loitering
		
		return returnValue;
	}
	
	/**
	 * Returns the value of a random element in the queue, doesn't modify the queue.
	 * @return random entry of the queue.
	 */
	public T sample() {
		int index = rGen.nextInt(queueEnd);		// selects a random index
		return queue[index];
	}
	
	/*
	 * Every iteration will (should) return entries in a different order.
	 */
	private class RanQueueIterator implements Iterator<T> {
		
		private T[] shuffledArray;
		
		private int current = 0;
		
		public RanQueueIterator() {
			shuffledArray = queue.clone();
			shuffle(shuffledArray);
		}
		
		@Override
		public boolean hasNext() {
			return current < queue.length;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			return shuffledArray[current++];
		}
		

	    /**
	     * Rearranges an array of objects in uniformly random order
	     * (under the assumption that {@code Math.random()} generates independent
	     * and uniformly distributed numbers between 0 and 1).
	     * @param array the array to be shuffled
	     */
	    public void shuffle(T[] array) {
	        int n = array.length;
	        for (int i = 0; i < n; i++) {
	            // choose index uniformly in [i, n-1]
	            int r = i + (int) (Math.random() * (n - i));
	            T swap = array[r];
	            array[r] = array[i];
	            array[i] = swap;
	        }
	    }
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new RanQueueIterator();
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> test = new RandomizedQueue<>();
		
		// adding 10 elements
		for (int i = 0; i < 10; i++) {
			test.enqueue(i);
			System.out.println("Added element: " + i);
			System.out.println("Current number of elements in queue: " + test.size() + "\n");
			
		}
		
		
		System.out.print("\nIterator test:\n[");
		for (Integer elem: test)
			System.out.print(elem + " ");
		System.out.println("]\n");
		
		// removing 10 elements
		for (int i = 0; i < 10; i++) {
			System.out.println("Removed element: " + test.dequeue());
			System.out.println("Current number of elements in queue: " + test.size() + "\n");
		}		
		
	}	
}
