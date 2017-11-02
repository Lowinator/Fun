import java.util.Arrays;

public class FixedSizeStackWithArray <T> {

	private T[] stack;
	private int tos;	// top of stack
	
	/**
	 * Initializes the stack with the given size.
	 * @param capacity size of the stack
	 */
	public FixedSizeStackWithArray(int capacity) {
		stack = (T[]) new Object[capacity];
		tos = 0;	
	}
	
	
	/*
	 * Pushes an element on top of the stack.
	 */
	public void push(T elem) {
		if (tos < stack.length)
			stack[tos++] = elem;
	}
	
	/**
	 * Pops an element of the top of the stack.
	 * @return the poped element, null if pop from an empty stack is attempted.
	 */
	@SuppressWarnings("unchecked")
	public T pop() {
		if (tos > 0) {
			T returnValue = (T) stack[tos-1];
			stack[--tos] = null;
			return returnValue;
		} else 
			return null;
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
	
	public static void main(String[] args) {
		int capacity = 10;
		FixedSizeStackWithArray<Integer> test = new FixedSizeStackWithArray<>(capacity);
		
		for (int i = 0; i < capacity/2; i++) {
			test.push(i);
			System.out.println(test);
		}
	
		for (int i = 0; i < capacity/2; i++) {
			System.out.println("Removed element: " + test.pop());
			System.out.println(test);
		}
		
		
		System.out.println("\nNew test: ");
		FixedSizeStackWithArray<String> test2 = new FixedSizeStackWithArray<>(capacity);
		
		test2.push("Some ");
		System.out.println(test2);
		
		test2.push("strings ");
		System.out.println(test2);
		
		test2.push("which ");
		System.out.println(test2);
		
		test2.push("I ");
		System.out.println(test2);
		
		test2.push("added.");
		System.out.println(test2);
		
		System.out.println("Removed element: " + test2.pop());
		System.out.println(test2);
		
		System.out.println("Removed element: " + test2.pop());
		System.out.println(test2);
		
		test2.push("removing strings works yay");
		System.out.println(test2);
		

	}

}
