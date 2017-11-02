package misc;

public class BinarySearch {
		
	/**
	 * Finds the index of a given value {@code e} in log(n)
	 * @param array the array which will be searched for {@code e}
	 * @param e value which the array is searched for
	 * @return index of a given value {@code e} or {@code -1}
	 * if the given value couldn't be found.
	 */
	public int find(int[] array, int e) {
		return doBinarySearch(array, e, 0, array.length-1);
	}
	
	public int findDescending(int[] array, int e) {
		return doReverseBinarySearch(array, e, 0 , array.length-1);
	}
	
	public int doBinarySearch(int[] array, int e, int low, int high) {
		int mid = low + (high-low)/2;
		
		if (high < low) return -1;	// e couldn't be found
		
		if (array[mid] == e) return mid;	// e has been found
		else if (array[mid] < e) {
			return doBinarySearch(array, e, ++mid, high);	// search in the right subarray
		} else {
			return doBinarySearch(array, e, low, --mid);	// search in the left subarray
		}
		
	}
	/**
	 * Binary search in a descending array.
	 * @param array array which we search in
	 * @param e	element we search for
	 * @param low lower bound for searching
	 * @param high upper bound for searching
	 * @return index of {@code e} if found, else {@code -1}
	 */
	public int doReverseBinarySearch(int[] array, int e, int low, int high) {
		int mid = low + (high-low)/2;
		
		if (high < low) return -1;	// e couldn't be found
		
		if (array[mid] == e) return mid;	// e has been found
		else if (array[mid] > e) {
			return doReverseBinarySearch(array, e, ++mid, high);	// search in the left subarray
		} else {
			return doReverseBinarySearch(array, e, low, --mid);	// search in the left subarray
		}
	}

	/**
	 * Tests the Binary Search implementation
	 * @param args currently not used.
	 */
	public static void main(String[] args) {
		
	//	int[] myArray = {-2,0,1,4,6,9,14,19};
		int[] myArray = {0,1,2,3,4,5,6,7,8,9};
		int[] myArray2 = {9,8,7,6,5,4,3,2,1,0};
		BinarySearch test = new BinarySearch();
		
		// should print ints from 0 to 9
		for (int i = 0; i < 10; i++)
			System.out.print(test.find(myArray, i) + "  ");
		
		System.out.println();	// new line
		
		// should print ints from 0 to 9
		for (int i = 9; i >= 0; i--)
			System.out.print(test.findDescending(myArray2, i) + "  ");
		
	}

}
