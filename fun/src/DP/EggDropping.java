package DP;

import java.util.Arrays;

/*
More infos: https://brilliant.org/wiki/egg-dropping/
 */
public class EggDropping {

    public static void main(String[] args) {
        EggDropping t = new EggDropping();

        System.out.println(t.solve(2,6));
    }

    /**
     * Computes the minimal number of attempts necessary in the worst case to determine at which floor eggs start breaking.
     * @param n number of eggs.
     * @param k number of floors.
     * @return minimal number of attempts in the worst case for n eggs and k floors.
     */
    int solve(int n, int k) {

        // map[i][j] is the minimal number of attempts necessary in the worst case for i eggs and j floors
        int[][] map = new int[n+1][k+1];

        // 0 floors -> 0 attempts, 1 floor -> 1 attempt
        for (int i = 0; i <= n; i++) {
            map[i][0] = 0;
            map[i][1] = 1;
        }

        // with 1 egg and j floors we always need j trials
        for (int i = 0; i <= k; i++) {
            map[1][i] = i;
        }

        int temp;

        for (int i = 2; i <= n; i++)
            for (int j = 2; j <= k; j++) {

                map[i][j] = Integer.MAX_VALUE;

                for (int p = 1; p <= j; p++) {
                    temp = 1 + Math.max(map[i - 1][p-1], map[i][j-p]);

                    if (temp < map[i][j])
                        map[i][j] = temp;
                }
            }

        // prints map for testing purposes
        System.out.println(Arrays.deepToString(map).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        return map[n][k];
    }

}
