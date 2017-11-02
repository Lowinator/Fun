package misc;

import misc.BinarySearch;

public class BitonicSearch {
	
	/**
	 * Finds the index of the largest element in the array.
	 * @param array	bitonic (ascending then descending) array with no duplicates which is being searched.
	 * @param {{@code e}	element which is being searched for.
	 * @param low lower bound index which partitions the array
	 * @param high upper bound index which partitions the array
	 * @return returns index of {@code e} in {@code array} if found, else {@code -1}
	 */
	private int findMax(int[] array, int low, int high) {
		int mid = low + (high-low)/2;
		
//		System.out.println("high: " + high + ",  low: " + low );
		
		if (high == low) return high;	// max has be found
		
		else if (array[mid] < array[mid+1]) {
			return findMax(array, ++mid, high);	// search in the right subarray
		} else {
			return findMax(array, low, mid);	// search in the left subarray
		}
	}
	
	/**
	 * Searches {@code array} for {@code e} in ~3lg(n).
	 * @param array is searched
	 * @param e element which the array is searched for
	 * @return index of {@code e} in {@code array} if found, else -1
	 */
	private int find(int[] array, int e) {
		
		int max = findMax(array, 0, array.length-1);
		BinarySearch binSearch = new BinarySearch();
		
		int leftResult = binSearch.doBinarySearch(array, e, 0, max);
		int rightResult = binSearch.doReverseBinarySearch(array, e, max+1, array.length-1);
		
		if (leftResult != -1)
			return leftResult;
		else
			return rightResult;
		
	}
	
	/**
	 * Tests the implementation of BitonicSearch.
	 * @param args
	 */
	public static void main(String[] args) {
		int[] myArray = {1,2,3,4,5,6,-1,-2,-4};
		BitonicSearch test = new BitonicSearch();
		
		for (int i = 1; i < 7; i++)
			System.out.println(test.find(myArray, i));
		
		System.out.println(test.find(myArray, -1));
		System.out.println(test.find(myArray, -2));
		System.out.println(test.find(myArray, -4));
		
	}

}
