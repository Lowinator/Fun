package misc;

/*
 * Sorted/unsorted subarray, take first element from unsorted
 * array and move it one to the left in the sorted array until
 * it reaches a smaller element. Repeat this for all elements in the
 * unsorted subarray.
 * On average: (n^2)/4 comparisons and exchanges (intuition: observe table)
 * 
 * Order of the input affects the runtime, worst case is a descending
 * ordered array (~(n^2)/2 comparisons and exchanges), 
 * best case is an ordered array (n-1 comparisons, 0 exchanges).
 */
public class InsertionSort<T extends Comparable> {
	
	public void sort(T[] array) {
		for (int i = 0; i < array.length; i++) {
			
			for (int j = i; j > 0; j--) {
				if (isLess(array[j], array[j-1])) 
					swap(array, j, j-1);
				else 
					break;
			}
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
		InsertionSort<Integer> test = new InsertionSort<>();
		Integer[] testArray = {0,9,8,7,6,5,4,3,2,1};
		
		InsertionSort<String> test2 = new InsertionSort<>();
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
