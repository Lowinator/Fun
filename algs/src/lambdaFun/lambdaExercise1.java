package lambdaFun;

import java.util.Arrays;
import java.util.Vector;

public class lambdaExercise1 {

    public static void main(String[] args) {

        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Condition greaterThan5 = x -> x > 5;

        System.out.println("Greater than 5: " + Arrays.toString(printConditionally(arr, greaterThan5)));

        Condition equalTo10 = x -> x == 10;

        System.out.println("Equal to 0: " + Arrays.toString(printConditionally(arr, equalTo10)));
    }

    // A method which takes in an array and a condition, it outputs elements of the array which satisfy the condition
    static Object[] printConditionally(Integer[] arr, Condition condition) {

        Vector<Integer> ret = new Vector<>();

        for (Integer i: arr) {
            if (condition.check(arr[i]))
                ret.addElement(i);
        }

        return ret.toArray();
    }
}

@FunctionalInterface
interface Condition {
    boolean check(Integer a);
}
