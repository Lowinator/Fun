package misc;

/**
 * A cache aware algorithm which, given two arrays X, Y and a 2 dim function f, computes the smallest
 * possible value f(X[i], Y[j]) by trying out all (i, j) pairs in an IO efficient manner.
 * Number of IOs is O(n^2/M*B) where M is the memory/cache size and B the block size.
 */

public class findMin {

    public static void main(String[] args) {

        int[] X = {1, 2, 3, 4, 5};
        int[] Y = {6, 7, 8, 9, 10};

        // define f
        BinaryIntegerOperation f = (a, b) -> a + b;

        int minValue = findMin(X, Y, f);

        System.out.println("Minimum achievable value of f for all pairs is " + minValue);
    }

    static int findMin(int[] X, int[] Y, BinaryIntegerOperation f) {

        int currentMin = Integer.MAX_VALUE;
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < Y.length; j++) {
                currentMin = Integer.min(currentMin, f.operation(X[i], Y[j]));
            }
        }

        return currentMin;
    }

}

@FunctionalInterface
interface BinaryIntegerOperation {
    int operation(int a, int b);
}
