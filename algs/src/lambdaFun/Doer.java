package lambdaFun;

/**
 * Created by divad_000 on 09.10.2017.
 */
public class Doer implements Do {

    public void performTheDo() {
        System.out.println("Doer performed the do.");
    }

    public static void lookingForADoer(Do doer) {
        doer.performTheDo();
    }

    public static void main(String[] args) {

        Doer you = new Doer();
        Doer2 he = new Doer2();

        lookingForADoer(you);

        lookingForADoer(he);

    }
}
