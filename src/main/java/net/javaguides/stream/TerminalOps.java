package net.javaguides.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOps {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        // 1. collect
        System.out.println("Collecting: ");
        list.stream().skip(1).collect(Collectors.toList()); // for old java version
        list.stream().skip(1).toList(); // for new java version


        /* In above Examples we have used 'toList()'. you can also use 'toSet()' and 'toMap().
        * mainly list, set and map can be used*/

        // 2. forEach
        System.out.println("forEach: ");
        list.stream().forEach(x -> System.out.println(x));

        list.stream().forEach(System.out::println); // same thing dine using method reference

        // 3. reduce : Combines elements to produce a single result
        System.out.println("reduce: ");
        Optional<Integer> optionalInteger = list.stream().reduce((x, y) -> x + y);
        System.out.println(optionalInteger.get());

        Optional<Integer> optionalInteger1 = list.stream().reduce(Integer::sum); // Using method reference
        System.out.println(optionalInteger1.get());

        // 4. count
        System.out.println("count: ");
        long res = list.stream().filter(x -> (x & 1) == 0).count();
        // Here we have used terminal operation 'count()'.
        System.out.println(res);

        // 5. anyMatch, allMatch, noneMatch
        System.out.println("anyMatch, allMatch, noneMatch: ");
        boolean b = list.stream().anyMatch(x -> x % 2 == 0);
        System.out.println(b);

        boolean b1 = list.stream().allMatch(x -> x > 0);
        System.out.println(b1);

        boolean b2 = list.stream().noneMatch(x -> x < 0);
        System.out.println(b2);

        // 6. findFirst, findAny
        System.out.println("findFirst, findAny: ");
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // 7. toArray()
        System.out.println("toArray: ");
        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min / max
        System.out.println("min / max: ");
        System.out.println("max: " + Stream.of(2, 44, 69).max(Comparator.naturalOrder()).get());

        System.out.println("max: " + Stream.of(2, 44, 69).max((o1, o2) -> o1 -o2).get());
        /*here we are using comparator */

        System.out.println("min: " + Stream.of(2, 44, 69).min(Comparator.naturalOrder()).get());

        // 9. forEachOrdered
        System.out.println("forEachOrdered: ");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Using forEach with parallel stream:");
        numbers.parallelStream().forEach(System.out::println);
        System.out.println("Using forEachOrdered with parallel stream:");
        numbers.parallelStream().forEachOrdered(System.out::println);

        // Examples: Filtering and Collecting Names
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");

        System.out.println(names.stream().filter(x -> x.length() > 3).collect(Collectors.toList()));

        // Examples: Squaring and Sorting Numbers
        List<Integer> numbers1 = Arrays.asList(5, 2, 9, 1, 6);
        System.out.println(numbers1.stream().map(x -> x * x).sorted().toList());

        // Examples: Summing Values
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.stream().reduce(Integer::sum).get());

        // Example: Counting Occurrences of a Character
        String sentence = "Hello world";
        System.out.println(sentence.chars().filter(x -> x == 'l').count());


        // Example
        // Streams cannot be reused after a terminal operation has been called
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println);
//        List<String> list1 = stream.map(String::toUpperCase).toList(); //exception

        // stateful & stateless
    }
}
