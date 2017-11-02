package misc;

/*
 * Do a number of h-sort with a decreasing h. The array is being partially
 * sorted as h decreases. At the end there happens a 1-sort (h=1) which is
 * the same as insertion sort on a partially sorted array. 
 * 
 * Shellsort is insertion sort with a little prework (h-sorting)
 * which makes it faster (insertion sort on a partially sorted array).
 * This way each h-sort moves elements "just a little bit" instead, insertion
 * sort with less element movement.
 * 
 * The h-sorting algorithm is like insertion sort but instead of comparing the
 * i-th and the (i-1)-th element we compare the i-th and the (i-h)-th element.
 * 
 * Worst case runtime is O(n^(3/2)), average is much better than that but
 * there is no known model to devise it. The best sequence for h is an open question.
 */
public class ShellSort<T extends Comparable> {

	public void sort(T[] array) {
		
		int h = 1;
		while (h < array.length/3)	// h starts from largest number in the sequence
			h = 3*h + 1;	// 1, 4, 13, 40, 121, 364... Knuth's 3x+1 sequence
		
		
		while (h >= 1) {
			for (int i = h; i < array.length; i++) {
				
				for (int j = i; j >= h; j -= h) {
					if (isLess(array[j], array[j-h]))
						swap(array, j-h, j);
					else
						break;
				}
			}
			
			h = h/3;	// moves one number down in the sequence
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
		ShellSort<Integer> test = new ShellSort<>();
		Integer[] testArray = {0,9,8,7,6,5,4,3,2,1};
		
		ShellSort<String> test2 = new ShellSort<>();
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
