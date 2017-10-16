package funWithJava8;
/*
Interfaces now have the ability to provide default implementations, not all methods must be abstract anymore.
 */
public interface DefaultMethods {

    abstract void ordinaryAbstractMethod();

    default int nonAbstractMethod() {
        return 42;
    }

}
