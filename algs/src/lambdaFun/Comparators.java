package lambdaFun;

import java.util.Random;
import java.util.LinkedList;
import java.util.List;

public class Comparators {

    public static void main(String[] args) {

        List<Pair> ls = new LinkedList<>();

        Random rn = new Random();

        // fills list with random pairs
        for (int i = 0; i < 10; i++) {
            ls.add(new Pair(rn.nextInt(11), rn.nextInt(11)));
        }

        // sorts by first element of the pairs
        ls.sort((a, b) -> Integer.compare(a.first, b.first));

        System.out.println("Sorted by first pairs: " + ls);

        ls.sort((a, b) -> Integer.compare(a.second, b.second));

        System.out.println("Sorted by second pairs: " + ls);

    }

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
}
