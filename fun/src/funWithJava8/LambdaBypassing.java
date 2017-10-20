package funWithJava8;

// one way to pass behaviour around without using lambdas:
// 1. create interface with signature of the method which encapsulates the behaviour,
// 2. create class which implements the interface and encapsulates the behaviour you wish to pass,
// 3. code the receiving method against the interface.




public class LambdaBypassing {

    public static void main(String[] args) {


        Doer first = new Doer();
        Doer2 second = new Doer2();

        lookingForADoer(first);

        lookingForADoer(second);

    }

    // step 1, the interface
    interface Do {

        void performTheDo();
    }

    // step 2, the behaviour

    // behaviour
    private static class Doer implements Do {
        public void performTheDo() {
            System.out.println("Doer performed the do.");
        }
    }

    // behaviour
    private static class Doer2 implements Do {
        public void performTheDo() {
            System.out.println("Doer2 performed the do.");
        }
    }

    // step 3, the receiving method
    public static void lookingForADoer(Do doer) {
        doer.performTheDo();
    }


}
