package DP;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence t = new LongestCommonSubsequence();

        String a = "abcdaf";
        String b = "acbcf";

        System.out.println(t.recursiveLCS(a, b));
    }

    // plain recursive solution, no DP
    int recursiveLCS(String a, String b) {

        if (a.isEmpty() || b.isEmpty())
            // base case
            return 0;
        else if (a.charAt(0) == b.charAt(0))
            // count the current match and solve the smaller LCS instance
            return 1 + recursiveLCS(a.substring(1), b.substring(1));
        else
            // solve both LCS instances and choose the one with the greater LCS
            return Math.max(recursiveLCS(a, b.substring(1)), recursiveLCS(a.substring(1), b));

    }

}
