package funWithJava8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
A streaming pipeline consists of a source (collection), intermediate operations (keep the stream open, filter, utility)
and a terminal operation (final operation which consumes the stream, the actual modification).

Intermediate operations are only executed when a terminal operation is present (lazily executed).

Operations are chained to form a pipeline (SourceStream.intermediateOperations.terminalOperation;)

Parallelism is easy, the Streams API will internally decompose your query to leverage the multiple cores on your computer.

Specialized (I love this language) streams for primitive types (IntStream, DoubleStream, LongStream),
they support additional out of the box operations.

More information:
http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 */
public class StreamsBasics {

    // examples of some commonly used methods
    public static void main(String[] args) {

        // Suppliers return some result of the specified type <T>
        Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> ls = streamSupplier.get().collect(Collectors.toList());

        System.out.println("Sum of all elements less than 5: " + ls.stream().filter(x -> x<5).reduce((x, y) -> x+y));

        // map() applies a function to each element in the stream and stores the resulting values into a new stream
        Stream<Integer> str1 = ls.stream().map(x -> x+1);

        // reduce() works like a fold in functional languages, apply some function over and over again, accumulate and return the resulting value
        System.out.println("Sums all elements: " + str1.reduce(Integer::sum));

        // IntStream provides additional operations
        System.out.println("Sums all elements using an IntStream: " + streamSupplier.get().mapToInt(Integer::intValue).sum());

        // note how str1 can't be reused since it has been consumed by the above reduce (terminal) operation
        System.out.println("The list incremented by 1: " + ls.stream().map(x -> x+1).collect(Collectors.toList()));


        // collect() can be used to store the stream into some data structure (list, map, set)
        ls = ls.stream().filter(x -> x%2 == 0).map(x -> x*2).collect(Collectors.toList());

        System.out.println("Only even numbers multiplied by 2: " + ls);

        System.out.println("Maximum element in ls: " + ls.stream().max((x, y) -> x.compareTo(y)).orElse(-1));

        // sorted sorts based on the passed (or default) comparator
        System.out.println("Sorted ls in decreasing order: " + ls.stream().sorted((x, y) -> y.compareTo(x)).collect(Collectors.toList()));

        // Stream.of can be used to turn values into streams
        List<String> ls2 = Stream.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine").limit(6).collect(Collectors.toList());
        Supplier<Stream<String>> streamSupplier2 = () -> Stream.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine").limit(6);

        // forEach can be used for iterating over the stream and producing some side effects
        System.out.print("\nContent of ls2: ");
        ls2.stream().forEach(s -> System.out.print(s + " "));

        // generate() and iterate() can be used for "infinite" streams (arbitrary size which the programmer defines later on)
        Stream<Integer> numbers = Stream.iterate(0, n -> n + 10);

        System.out.print("\nFinite subset from the infinite stream: ");
        numbers.limit(5).forEach(s -> System.out.print(s + " "));

        // flatMap() is map() but produces zero, one or more elements per element in the original stream.
        //streamSupplier2.get().flatMap();
    }



}
