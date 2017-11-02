import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	
	private Node first = null;
	
	private class Node {
		T value;
		Node next;
		
	}
	
	/**
	 * Adds an element to the beginning of the list.
	 * @param elem element which is being added.
	 */
	public void addToFront(T elem) {
		Node newNode = new Node();
		newNode.value = elem;
		newNode.next = first;
		first = newNode;	
	}
	
	/**
	 * Adds an element to the end of the list.
	 * @param elem element which is being added.
	 */
	public void add(T elem) {
		Node newNode = new Node();
		newNode.value = elem;
		newNode.next = null;
		
		if (this.isEmpty()) {	// special case for adding to an empty list
			first = newNode;
		} else {
			Node temp = first;
			
			while (temp.next != null) {	// sets temp equal to last Node in list		
				temp = temp.next;
			}
			
			temp.next = newNode;	// last Node now points to the newly added node
		}
	}
	
	/**
	 * Removes the first element from the list that equals {@code elem}.
	 * @param elem the element which is being removed.
	 * @return {@code true} if {@code elem} removed successfully, {@code false} if not found.
	 */
	public boolean remove(T elem) {
		
		if (this.isEmpty())	// can't remove from empty list
			return false;
		
		Node temp = first;	// used to iterate through the list
		
		if (first.value.equals(elem))	// special case for deleting first element
			first = first.next;
		
		while (temp.next != null) {		// iterates through list and looks for elem
			if (temp.next.value.equals(elem)) {
				temp.next = temp.next.next;		// the node which has the value elem is not being referenced anymore
				return true;
			}
			
			temp = temp.next;
		}
		
		return false;	// elem not found		
	}
	
	/**
	 * Checks if the list is empty
	 * @return true if empty, else false
	 */
	public boolean isEmpty() {
		if (first == null) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Returns a string representation of the list.
	 */
	public String toString() {
		String output = "[";
		
		for (T nodeValue: this) {
			if (output.length() > 1)	// doesn't add a ", " at the beginning
				output += ", ";
			
			output += nodeValue;
		}
		
		return output += "]";
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T> {
		private Node current = first;	// entry which the iterator is currently on
		@Override
		public boolean hasNext() {			
			return current != null;
		}

		@Override
		public T next() {
			T returnValue = current.value;
			current = current.next;		// advance iterator to next entry in list
			return returnValue;
		}
		
	}
	public static void main(String[] args)  {
		LinkedList<Integer> test = new LinkedList<>();
		
		for (int i = 0; i < 10; i++)
			test.add(i);
		
		System.out.println(test);
		
		for (int i = 0; i < 10; i += 2) {
			test.remove(i);
			System.out.println(test);
		}
		
		
		LinkedList<String> test2 = new LinkedList<>();
		
		
		test2.add("Some ");
		System.out.println(test2);
		
		test2.add("strings ");
		System.out.println(test2);
		
		test2.add("which ");
		System.out.println(test2);
		
		test2.add("I ");
		System.out.println(test2);
		
		test2.add("added.");
		System.out.println(test2);
		
		test2.remove("I ");
		System.out.println(test2);
		
		test2.remove("strings ");
		System.out.println(test2);
		
		test2.add("removing strings works yay");
		System.out.println(test2);
	}

}
