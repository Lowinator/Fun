package DP;
/*
Determine the order in which to multiply matrices such that the number of operations is minimal.
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        MatrixChainMultiplication t = new MatrixChainMultiplication();

        int[] m = {10, 20, 30};

        System.out.println(t.plainRecursive(m, 1, m.length-1));
    }

    // exponential time complexity, no DP
    int plainRecursive(int[] m, int i, int j) {

        // base case, 0 operations when only 1 matrix
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        // split problem subproblems, all possible splits into 2
        for (int k = i; k < j; k++) {

            int temp = plainRecursive(m, i, k) +    // left part of split
                       plainRecursive(m,k+1, j) +   // right part of split
                       m[i-1]*m[k]*m[j];    // number of operations for current split

            // checks if number of operations for current cut is better than current optimum
            if (temp < min)
                min = temp;
        }
        return min;
    }
}
