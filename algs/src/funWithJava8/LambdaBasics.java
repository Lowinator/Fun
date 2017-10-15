package funWithJava8;

public class LambdaBasics {

    public static void main(String[] args) {

        // basic lambda defintion
        Printable littleLambda = () -> {
            System.out.println("I'm a little lambda.");
        };

        // execute the lambda expression, note that it behaves like
        // an implementation of the corresponding interface
        littleLambda.print();

        // define lambda in one line
        UnaryIntegerOperation doubleFunction = (int a) -> a*2;

        System.out.println("One line lambda: " + doubleFunction.operation(5));

        // anonymous inner class which can be used in lots of places
        // where lambdas are used, lambdas are preferred.
        BinaryIntegerOperation subtraction = new BinaryIntegerOperation() {
            @Override
            public int operation(int a, int b) {
                return a - b;
            }
        };

        System.out.println("Anonymous inner class: " + subtraction.operation(2, 1));


        // example of multiple line lambda
        BinaryIntegerOperation safeDivision = (int a, int b) -> {
            if (b != 0)
                return a/b;
            else
                return 0;
        };

        System.out.println("Multiple line lambda: " + safeDivision.operation(5,2));

        // argument types can be omitted because of the interface
        BinaryIntegerOperation concatenate = (a, b) -> Integer.parseInt(("" + a + b));

        System.out.println("No argument types: " + concatenate.operation(1,2));

        // for lambdas with one argument you can omit the parantheses
        UnaryIntegerOperation triple = a -> a*3;

        System.out.println("One argument, no parentheses: " + triple.operation(1));

        // pass a lambda in line
        iApplyAFunctionOnAnArgument(x -> x+1, 3);
    }

    // method which expects a lambda (or an instance of the corresponding interface)
    public static void iApplyAFunctionOnAnArgument(UnaryIntegerOperation f, int a) {
        System.out.println("Passed lambda is applied on an integer: " + f.operation(a));
    }
}

 @FunctionalInterface
interface  UnaryIntegerOperation {
    int operation(int a);
}

@FunctionalInterface
interface BinaryIntegerOperation {
    int operation(int a, int b);
}

@FunctionalInterface
interface Printable {
    void print();
}
