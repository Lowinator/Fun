import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a stack with a linked list.
 * Every operation takes constant time in the worst case.
 * Uses more time (slower operations) and space than the array 
 * alternative because of the links.
 * Use the list implementation if the client needs a constant worst 
 * case runtime guarantee for every operation of the stack.
 * (when sudden slowdowns/hickups are not acceptable)
 * @author divad_000
 *
 */
public class StackWithLinkedList<T> implements Iterable<T> {
	
	private Node first = null;	// tos reference
	private int size = 0;	// size of the stack
	/**
	 * Represents the Nodes which make up the stack.
	 * @author divad_000
	 *
	 */
	private class Node {
		private T value;
		private Node next;
	}
	
	/**
	 * Pushes {@code elem} onto the stack.
	 * @param elem is being pushed on top of the stack.
	 */
	public void push(T elem) {
		Node newNode = new Node();
		
		newNode.value = elem;
		newNode.next = first;
		
		size++;
		first = newNode;
	}
	
	/**
	 * Pops the top element of the stack and returns it.
	 * @return popped element.
	 */
	public T pop() {
		if (first != null) {

			T returnValue = first.value;
			
			first = first.next;
			size--;
			
			return returnValue;
			
		} else throw new EmptyStackException();
		
	}
	
	/**
	 * Returns a String representation of the stack.
	 */
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
	
	/**
	 * Checks if the stack is empty.
	 * @return true if stack is empty, else false
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	public T peek() {
		if (this.isEmpty()) 
			throw new NoSuchElementException();
		
		return first.value;
	}
	
	
	/**
	 * Tests the stack implementation.
	 * @param args currently not in use.
	 */
	public static void main(String[] args) {
		StackWithLinkedList<Integer> test = new StackWithLinkedList<>();
		
		for (int i = 0; i < 10; i++) {
			test.push(i);
			System.out.println(test);
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println("Popped element: " + test.pop());
			System.out.println(test);
		}
		
		
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}
	
	/**
	 * Iterates through the stack from tos to bottom.
	 * @author divad_000
	 *
	 */
	private class StackIterator implements Iterator<T> {

		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext())		// there is no next element
				throw new NoSuchElementException();
			
			T returnValue = current.value;
			
			current = current.next;
			
			return returnValue;
		}
		
	}
	
	

}
