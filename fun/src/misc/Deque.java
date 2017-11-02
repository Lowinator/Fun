package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T>{
	
	private Node first = null;	// reference to first node
	private Node last = null;	// reference to last node
	
	private int size = 0;	// stores the number of elements in the Deque
	
	
	private class Node {
		T value;
		Node next;
		Node previous;
	}

	/**
	 * Tests the Deque implementation.
	 * @param args currently not in use.
	 */
	public static void main(String[] args) {
		Deque<Integer> test = new Deque<>();
	
		boolean front = true;
		
		for (int i = 0; i < 10; i++) {	// alternates between adding to the front and back
			if (front) {
				test.addFirst(i);
				front = false;
			} else {
				test.addLast(i);
				front = true;
			}
			
			System.out.println(test);
		}

		for (int i = 0; i < 10; i++) {	// alternates between adding to the front and back
			if (front) {
				test.removeFirst();
				front = false;
			} else {
				test.removeLast();
				front = true;
			}
			
			System.out.println(test);
		}
	}	

	/**
	 * Checks if the Deque is empty.
	 * @return true if empty, else false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Checks the size of the Deque.
	 * @return number of elements in the Deque.
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the beginning of the Deque.
	 * @param elem the Element which is being added.
	 */
	public void addFirst(T elem) {
		if (elem == null)	// null elements arent allowed
			throw new IllegalArgumentException();
		
		Node newFirst = new Node();
		newFirst.value = elem;
		newFirst.next = first;
		
		if (first == null) 	// special case for adding the first element
			first = last = newFirst;
		else {
			first.previous = newFirst;
			first = newFirst;
		}
		
		size++;
	}

	/**
	 * Adds an element to the end of the Deque.
	 * @param elem the Element which is being added.
	 */
	public void addLast(T elem) {
		if (elem == null)	// null elements arent allowed
			throw new IllegalArgumentException();
		
		Node newLast = new Node();
		newLast.value = elem;
		newLast.previous = last;
		
		if (last == null)	// special case for adding the first element
			last = first = newLast;
		else {
			last.next = newLast;
			last = newLast;
		}
		size++;
	}

	/**
	 * Removes an element from the beginning of the Deque.
	 * @return the removed element.
	 */
	public T removeFirst() {
		if (isEmpty())	// can't remove element from empty Deque
			throw new NoSuchElementException();
		
		T returnValue = first.value;
		
		if (first.next == null)	// special case for removing the last element
			first = last = null;
		else {
			first.next.previous = null;
			first = first.next;
		}
		
		size--;
		return returnValue;
	}

	/**
	 * Removes an element from the end of the Deque.
	 * @return the removed element.
	 */
	public T removeLast() {
		if (isEmpty())	// can't remove element from empty Deque
			throw new NoSuchElementException();
		
		T returnValue = last.value;
		
		
		if (last.previous == null)	// special case for removing the last element
			last = first = null;
		else {
			last.previous.next = null;
			last = last.previous;
		}
		
		size--;
		return returnValue;
	}

	
	public Iterator<T> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<T> {

		Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) 	// there is no next element
				throw new NoSuchElementException();
			
			T returnValue = current.value;
			
			current = current.next;
			
			return returnValue;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public String toString() {
		String returnValue = "[";
		
		boolean firstElem = true;
		
		for (T current: this) {
			if (!firstElem) 
				returnValue += ", ";
			else
				firstElem = false;
			
			returnValue += current;
		}
		
		return returnValue + "]";
		
	}
	
	
	

}
