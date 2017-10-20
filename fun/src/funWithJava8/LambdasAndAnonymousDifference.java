package funWithJava8;

import java.util.function.UnaryOperator;

/*
A lambda expression is converted (binded dynamically) into a private method of the class by the java compiler.
 */
public class LambdasAndAnonymousDifference {

    void doSomething(int i, UnaryOperator<Integer> op) {
        System.out.println(this);
        System.out.println(op.apply(i));
    }

    public static void main(String[] args) {

        LambdasAndAnonymousDifference test = new LambdasAndAnonymousDifference();

        System.out.println("Anonymous class example: ");
        test.doSomething(1, new UnaryOperator<Integer>() {

            public Integer apply(Integer x) {
                System.out.println(this);   // works
                return ++x;
            }
        });

        System.out.println("Lambda example: ");
        test.doSomething(1, x -> {
            //System.out.println(this);  doesn't work
            return ++x;
        });

    }
}
