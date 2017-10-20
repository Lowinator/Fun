package funWithJava8;

import java.util.Arrays;
import java.util.Vector;
import java.util.function.Predicate;

public class LambdasAndConditionPassing {

    public static void main(String[] args) {

        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Condition greaterThan5 = x -> x > 5;

        System.out.println("Greater than 5: " + Arrays.toString(filter(arr, greaterThan5)));

        Condition equalTo10 = x -> x == 10;

        System.out.println("Equal to 0: " + Arrays.toString(filter(arr, equalTo10)));


        // there are prepared interfaces for common usage patterns in java.util.function

        Predicate<Integer> lessThan5 = x -> x < 5;

        System.out.println("Less than 5: " + Arrays.toString(filter(arr, lessThan5)));

        // with such simple conditions you will do it like this

        System.out.println("Divisible by 2: " + Arrays.toString(filter(arr, (Predicate<Integer>) x -> x%2 == 0)));
    }

    // A method which takes in an array and a condition, it outputs elements of the array which satisfy the condition
    static Integer[] filter(Integer[] arr, Condition condition) {

        Vector<Integer> ret = new Vector<>();

        for (Integer i: arr) {
            if (condition.check(arr[i]))
                ret.addElement(i);
        }

        return ret.toArray(new Integer[ret.size()]);
    }

    // same method with an interface from java.util.function
    static Object[] filter(Integer[] arr, Predicate<Integer> condition) {

        Vector<Integer> ret = new Vector<>();

        for (Integer i: arr) {
            if (condition.test(arr[i]))
                ret.addElement(i);
        }

        return ret.toArray(new Integer[ret.size()]);
    }
}

@FunctionalInterface
interface Condition {
    boolean check(Integer a);
}
