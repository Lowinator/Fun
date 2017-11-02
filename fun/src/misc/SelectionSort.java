package misc;

/*
 * Takes (n-1)+(n-2)+...+2+1+0 = ~(n^2)/2 comparisons and n swaps.
 * 
 * Set i = 0, find the smallest element in the array, put it on the
 * index i, increment i, repeat till i exceeds the max array index. * 
 */
public class SelectionSort<T extends Comparable> {

	/**
	 * Sorts the given array in ascending order.
	 * @param array array which is to be sorted.
	 */
	public void sort(T[] array) {
		
		int min;
		
		for (int i = 0; i < array.length; i++) {	
			
			min = i;
			
			for (int j = i+1; j < array.length; j++) {	// checks if there exists a smaller element than array[min]
				if (isLess(array[j], array[min]))
					min = j;
			}
			
			swap(array, min, i);	// puts the min element on index i 
		}
		
		
	}
	
	/**
	 * Determines if a is less than b.
	 * @param a first element 
	 * @param b	second element
	 * @return true if a < b, otherwise false
	 */
	private boolean isLess(final T a, final T b) {
		if (a.compareTo(b) < 0) 
			return true;
		else 
			return false;
	}

	/**
	 * Swaps the places of two elements in an array.
	 * @param array	the array in which the swapping happens.
	 * @param index1 index of the first element.
	 * @param index2 index of the second element.
	 */
	private void swap(T[] array, int index1, int index2) {
		T temp = array[index1];
		
		array[index1] = array[index2];
		
		array[index2] = temp;		
	}
	
	
	public static void main(String[] args) {
		SelectionSort<Integer> test = new SelectionSort<>();
		Integer[] testArray = {0,9,8,7,6,5,4,3,2,1};
		
		SelectionSort<String> test2 = new SelectionSort<>();
		String[] testArray2 = {"c", "a", "b"};

		
				
		test.sort(testArray);
		
		for (int i = 0; i < testArray.length; i++)
			System.out.print(testArray[i] + " ");
		
		System.out.println();
		
		
		test2.sort(testArray2);
		
		for (int i = 0; i < testArray2.length; i++)
			System.out.print(testArray2[i] + " ");
	}

}
