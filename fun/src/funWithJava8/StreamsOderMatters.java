package funWithJava8;

import java.util.stream.Stream;

/*
Operations within your pipeline can be executed vertically (filter, map) or horizontally (sorted).
The order in which operations are executed can influence the number of times an operation is executed.
 */
public class StreamsOderMatters {

    public static void main(String[] args) {

        System.out.println("The order of operations results in unnecessary many operations: ");
        unoptimizedExample();

        System.out.println("\nVersion of the above with fewer operations that accomplishes the same: ");
        optimizedExample();

    }

    static void unoptimizedExample() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                // sorted is executed "vertically" since its a stateful operation
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    static void optimizedExample() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }
}
