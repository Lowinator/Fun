package funWithJava8;

import java.util.function.*;

// Before passing a lambda to method X, pass it to a wrapper and pass the lambda returned
// by the wrapper to X. Do the exception handling within the wrapper.
public class LambdasAndExceptionHandling {

    public static void main(String[] args) {
        int [] someNumbers = { 1, 2, 3, 4 };
        int key = 1;

        process(someNumbers, key, wrapperLambda((v, k) -> System.out.println(v / k)));



    }

    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
        for (int i : someNumbers) {
            consumer.accept(i, key);
        }

    }


    private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
        return (v, k) ->  {
            try {
                consumer.accept(v, k);
            }
            catch (ArithmeticException e) {
                System.out.println("Exception caught in wrapper");
            }

        };
    }
}
