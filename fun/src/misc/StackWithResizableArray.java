import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a stack with a resizable array. 
 * Every operation takes constant amortized time.
 * Uses less memory and it's cheap operations are faster than that of the list alternative.
 * It offers push and pop in O(1) most of the time, a resize costs O(n).
 * The drawback is the resizing which happens every now and then, this operation will slow the program down for a bit.
 * Use the array implementation if the overall speed is important and the slowdowns during the resize don't matter much.
 * @author divad_000
 *
 */
public class StackWithResizableArray<T> implements Iterable<T>{

	private int size = 1;	// size of the stack
	private int tos = 0;	// top of stack
	@SuppressWarnings("unchecked")
	private T[] stack = (T[]) new Object[size];	// array representing the stack
	
	/**
	 * Sets the stack size.
	 * @param size the new stack size.
	 */
	private void resize(int size) {
		System.out.println("Resizing from " + stack.length + " to " + size);
		T[] newArray = Arrays.copyOfRange(stack, 0, size);
		this.size = size;
		stack = newArray;
	}
	
	/**
	 * Push {@code elem} on top of the stack and adjusts size if necessary.
	 * @param elem the element which is being pushed on the stack.
	 */
	public void push(T elem) {
		if (tos == size) 	// doubles size of underlying array if stack is full and needs more space
			resize(size*2);			
		
		stack[tos++] = elem;	// adds element to top of stack and advances tos
	}
	
	/**
	 * Pops element of the stack and adjusts size if necessary.
	 * @return element which has been popped or null if pop couldn't be done.
	 */
	public T pop() {
		if (tos > 0) {		// can't pop an empty stack
			if (tos-1 <= size/4) 		// the array size halves if only a quarter of it is used
				resize(size/2);
		
			T returnValue = stack[tos-1];
			stack[--tos] = null;	// avoids loitering
			return returnValue;
			
		} else return null;
	}

	
	/**
	 * Returns a string representation of the stack.
	 */
	public String toString() {
		String output = "[";
		
		for (int i = 0; i < tos; i++) {
			output += stack[i];
			
			if (i+1 < tos)
				output += ", ";
		}
		
		return output+"]";
	}
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<T> {

		int current = tos;	// we iterate the array from back to front because of stack semantics
		
		@Override
		public boolean hasNext() {
			return current > 0;
		}

		@Override
		public T next() {
			if (tos <= 0)			// can't retrieve elements from empty stack
				throw new NoSuchElementException();
				
			T returnValue = stack[--tos];
			
			stack[tos] = null;	// to avoid loitering
			
			return returnValue;
		}
		
	}
	

	/**
	 * Tests the implementation.
	 * @param args is currently not in use.
	 */
	public static void main(String[] args) {

		StackWithResizableArray<Integer> test = new StackWithResizableArray<>();
		
		for (int i = 0; i < 10; i++) {
			test.push(i);
			System.out.println(test.toString());
		}
	
		for (int i = 0; i < 10; i++) {
			System.out.println("Removed element: " + test.pop());
			System.out.println(test.toString());
		}

	}

}
