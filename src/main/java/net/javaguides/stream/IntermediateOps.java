package net.javaguides.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOps {
    public static void main(String[] args) {
        // Intermediate operations transform a stream into another stream
        // they are lazy, meaning don't execute until a terminal operation is invoked.

        // 1. filter
        List<String> list = Arrays.asList("Akshit", "Ram", "Shyam", "Ghanshyam", "Akshit");
        Stream<String> filteredStream = list.stream().filter(x -> x.startsWith("A"));
        // At this point no filtering happened. Because, we haven't used any terminal operation yet.

        long res = list.stream().filter(x -> x.startsWith("A")).count();
        // Here we have used terminal operation 'count()'. So, filtering happend.
        System.out.println(res);

        // 2.map
        Stream<String> stringStream = list.stream().map(x -> x.toUpperCase());
        // At this point nothing happened. Because, we haven't used any terminal operation yet.

        Stream<String> stringStream1 = list.stream().map(String::toUpperCase);
        // here we have used method reference, since 'toUpperCase()' is method

        // 3.sorted
        Stream<String> sortedStream = list.stream().sorted();
        Stream<String> sortedStreamUsingComparator = list.stream().sorted((a, b) -> a.length() - b.length());

        // 4.distinct
        System.out.println(list.stream().filter(x -> x.startsWith("A")).distinct().count());

        // 5.limit
        System.out.println(Stream.iterate(1, x -> x + 1).limit(100).count());

        //6. skip
        System.out.println(Stream.iterate(1, x -> x + 1).skip(10).limit(100).count());
        /* Starting from 11, to 100 elements it will contain. And first 1 to 10 elements will be skiped */


    }
}
