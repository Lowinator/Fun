package funWithJava8;

import java.util.function.Predicate;

// the JRE "freezes" values and encapsulates a function and it's environment
// ("frozen values") into a closure which can then be passed.
public class LambdasAndClosures {

    public static void main(String[] args) {

        int a = 10;
        int b = 2;

        // magic
        System.out.println(worksForA(a, x -> x%b == 0));



    }

    static boolean worksForA(int a, Predicate<Integer> condition) {
        if (condition.test(a))
            return true;
        else
            return false;



    }
}
