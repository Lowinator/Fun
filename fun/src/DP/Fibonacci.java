package DP;

/*
Chosen starting point for the sequence in the examples below is 0:
0, 1, 1, 2, 3, 5, 8, 13, ...
 */
public class Fibonacci {

    public static void main(String[] args) {

        Fibonacci t = new Fibonacci();

        int n = 10;
        for (int i = 1; i <= n; i++)
            System.out.println(t.fibTopDown(i) + ", " + t.fibBottomUp(i) + ", " + t.fibRecursive(i));

    }

    // plain recursive solution, no DP
    public int fibRecursive(int n) {

        if (n <= 1)
            return n;
        else
            return fibRecursive(n-1) + fibRecursive(n-2);

    }

    // computes n-th fibonacci number in a top down fashion (memoization)
    public int fibTopDown(int n) {

        int[] mem = new int[n];

        // base cases
        if (n <= 1)
            return n;
        else {
            // check if solution has already been computed
            if (mem[n-1] != 0)
                return mem[n-1];
            else
                // compute and store in case the solution hasn't been computed before
                mem[n-1] = fibTopDown(n-1) + fibTopDown(n-2);
        }

        // n-th fibonacci number
        return mem[n-1];
    }

    // computes n-th fibonacci number in a bottom down fashion (tabulation)
    public int fibBottomUp(int n) {

        int[] mem = new int[n+1];

        // base cases
        mem[0] = 0;
        mem[1] = 1;

        for (int i = 2; i < n; i++)
            mem[i] = mem[i-1] + mem[i-2];

        // n-th fibonacci number
        return mem[n-1];
    }
}
