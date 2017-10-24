package streamingAlgorithms;

/*
More information:

https://people.cs.umass.edu/~mcgregor/711S12/countmin.pdf

 */

import java.math.BigInteger;
import java.util.Random;

public class CountMinSketch {

    public static void main(String[] args) {

    }


    /**
     * Queries the sketch.
     *
     * The results of all parallel executions (rows in sketch) are being compared, the minimal entry in column j
     * will be returned as all of the approximations must be overestimates.
     * @param sketch Target sketch.
     * @param j Item whose frequency will be approximated.
     * @return Approximation of the frequency for the given item.
     */
    int querrySketch(int[][] sketch, int j) {

        int min = Integer.MAX_VALUE;

        // TODO hf accessing the hash functions... genius.

    }
    /**
     * Randomized algorithm which returns a sketch which can be queried for an approximation of
     * the frequency of each item in the given stream.
     * The assumes the strict turnstile model (c>0 for all items).
     *
     * The algorithm starts by randomly distributing (pairwise independent hash family) items of the given stream among k groups.
     * The frequency of an item j is approximated to be the sum of all frequencies within the group that j is
     * located in.
     * The described routine above is run t times in parallel, each row of the returned 2 dim array
     * represents one run, we obtain the best solution for a given item j by querying a column of the array
     * and returning the minimal entry of that column (since all approximations are always overestimates).
     *
     * For a given item j the algorithm provides
     * an approximation of it's frequency apr[j] such that opt[j] <= apr[j] <= opt[j] + eps*Total, where
     * opt[j] is the actual frequency of j and Total is the sum of frequencies of all items.
     *
     * Space complexity is sublinear: O((1/eps) log(1/delt) log m) bits of storage, where m is the
     * maximal frequency of any element in the stream.
     *
     * @param stream Target stream.
     * @param eps Determines the accuracy of the approximation (smaller eps -> returned solution is closer to optimal), must be >0.
     * @param delt Determines the probability with which the returned solution has the accuracy specified by eps.
     * @return A sketch which can be queried for frequency approximations.
     */
    int[][] computeSketch(Item[] stream, double eps, double delt) {

        int k = (int) Math.ceil(2/eps);

        int t = (int) Math.ceil(Math.log(1/delt) / Math.log(2));

        int[][] sketch = new int[t][k];


        HashF[] h = new HashF[t];;

        // initializes the family of pairwise independent hash functions
        for (int i = 0; i < h.length; i++)
            h[i] = new HashF(k);


        // process the stream
        for (Item j : stream)
            /* for every parallel execution i, hash the j-th item to the appropriate group,
            update the frequency of that group by adding the weight of the j-th element. */
            for (int i = 0; i < t; i++) {
                sketch[i][h[i].hash(j.ID)] += j.weight;
            }

        return sketch;
    }

    /* Used to generate and encapsulate pairwise independent hash functions.
    See see https://people.csail.mit.edu/ronitt/COURSE/S12/handouts/lec5.pdf , claim 5 for more information.
     */
    private static class HashF {

        private final int a;
        private final int b;
        private final int p;

        HashF() {
            Random rand = new Random();

            p = 1610612741; // prime
            this.a = rand.nextInt(p);
            this.b = rand.nextInt(p);

        }

        // rounds the given int to the next largest prime
        HashF(int p) {
            this.p = nextPrime(p);
            Random rand = new Random(this.p);

            this.a = rand.nextInt(this.p);
            this.b = rand.nextInt(this.p);
        }

        // hashes integers
        public int hash(int x) {
            return (a*x + b) % p;
        }

        // (hopefully) returns the next prime greater than n
        private int nextPrime(int n)
        {
            BigInteger b = new BigInteger(String.valueOf(n));
            return Integer.parseInt(b.nextProbablePrime().toString());
        }

    }

    // represents items in the stream
    private static class Item {
        int ID;
        int weight; // c
    }
}
