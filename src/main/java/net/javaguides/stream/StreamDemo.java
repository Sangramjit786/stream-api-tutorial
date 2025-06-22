package net.javaguides.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        // feature introduced in java 8
        // process collection of data in a functional and declarative manner
        // Simplify Data Processing
        // Embrace Functional Programming
        // Improve Readability and Maintainability
        // Enable Easy Parallelism

        // What is stream?
        // a sequence of elements supporting functional and declarative programming

        // How to Use Streams?
        // Source, intermediate operations & terminal operation

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); // Source

        // Find count of even numbers
        //traditional way
        int count = 0;
        for (int i : numbers) {
            if ((i & 1) == 0) {
                count++;
            }
        }

        System.out.println("traditional way: " + count);

        // Using Stream
        System.out.println("Stream way: " + numbers.stream().filter(x -> (x & 1) == 0).count());


        //// Creating Streams -> different ways, from collections and infinite streams
        // 1. From Collections(it is applicable for any collection, for example we have taken 'List')
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();
        // 2. From Arrays
        // For String
        String[] array = {"a", "b", "c"};
        Stream<String> stream1 = Arrays.stream(array);
        // For Integer
        int[] numArray = {1, 3, 5, 2};
        Stream<Integer> stream3 = Arrays.stream(numArray).boxed();
        // 3. Using Stream.of()
        Stream<String> stream2 = Stream.of("a", "b");
        // 4. Infinite streams(we use limit(), for finite size)
        Stream<Integer> gnerate = Stream.generate(() -> 1);
        Stream.iterate(1, x -> x + 1);

        List<Integer> collect = Stream.iterate(1, x -> x + 1).limit(100).collect(Collectors.toList());
        System.out.println(collect);

    }
}
