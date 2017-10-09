import java.util.Random;

/*
 * Generate a uniformly random number between 0 and i-1
 * or i and n-1 and swap it with the current index i.
 * 
 * Set i = 0, incerement i, generate random integer from 
 * 0 to i-1, swap them, increment i, generate, swap, inc,
 * gen, swap...
 */
public class KnuthShuffle<T extends Comparable> {
	
	public void shuffle(T[] array) {
		
		Random rGen = new Random();
		int index;
		
		for(int i = 0; i < array.length; i++) {
			
			index = rGen.nextInt() % i;
			
			swap(array, i, index);
			
		}
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
		KnuthShuffle<Integer> test = new KnuthShuffle<>();
		
		Integer[] testArray = {0,1,2,3,4,5,6,7,8,9};
		
		KnuthShuffle<String> test2 = new KnuthShuffle<>();
		
		String[] testArray2 = {"Ajmoo", "bottle", "plswork", "uGoKnuth", "aaaay", "macarenaa"};
		
	
		
		test.shuffle(testArray);
		
		for (int i = 0; i < testArray.length; i++)
			System.out.print(testArray[i] + " ");
		
		System.out.println();
		
		
		test2.shuffle(testArray2);
		
		for (int i = 0; i < testArray2.length; i++)
			System.out.print(testArray2[i] + " ");
		
		

	}

}
