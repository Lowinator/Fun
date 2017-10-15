package funWithJava8;

import java.util.function.Consumer;
/*
Replace lambdas whose only purpose is calling another method by method references.
 */
public class LambdasAndMethodReferences {

    public static void main(String[] args) {

        // Thread t = new Thread(() -> doSomething());  lambda alternative
        Thread t = new Thread(LambdasAndMethodReferences::doSomething);
        t.start();

        // passMeDoSomething2(1, (Integer i) -> doSomething2(i));  lambda alternative
        passMeDoSomething2(1, LambdasAndMethodReferences::doSomething2);




    }

    public static void doSomething() {
        System.out.println("Did something.");
    }

    public static void doSomething2(int i) {
        System.out.println(i++);
    }

    public static void passMeDoSomething2(int i, Consumer<Integer> c) {
        c.accept(i);
    }



}
