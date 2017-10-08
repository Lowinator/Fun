/**
 * A cache aware algorithm which, given two arrays X, Y and a 2 dim function f, computes the smallest
 * possible value f(X[i], Y[j]) by trying out all (i, j) pairs in an IO efficient manner.
 * Number of IOs is O(n^2/M*B) where M is the memory/cache size and B the block size.
 *
 * TODO A cache aware algorithm which, given two arrays X, Y and a 2 dim function, computes a pair (i, j)
 * such that f(X[i], Y[i]) is the smallest value f returns for all possible (i, j) pairs.
 *
 *
 */
public class findMin {

    public static void main(String[] args) {

        int[] X = {1, 2, 3, 4, 5};
        int[] Y = {6, 7, 8, 9, 10};

        // define f

        int minValue = findMin(X, Y, f);

        System.out.println("Minimum achievable value of f for all pairs is " + minValue);
    }

    // TODO get familiar with lambda expressions lol
    static int findMin(int[] X, int[] Y, f) {

        int currentMin = Integer.MAX_VALUE;
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < Y.length; j++) {
                currentMin = Integer.min(currentMin, f(X[i], Y[j]))
            }
        }

        return currentMin;
    }
}
