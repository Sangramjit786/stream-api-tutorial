package net.javaguides.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerminalOps {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        // 1. collect
        list.stream().skip(1).collect(Collectors.toList()); // for old java version
        list.stream().skip(1).toList(); // for new java version


        /* In above Examples we have used 'toList()'. you can also use 'toSet()' and 'toMap().
        * mainly list, set and map can be used*/

        // 2. forEach
        list.stream().forEach(x -> System.out.println(x));

        list.stream().forEach(System.out::println); // same thing dine using method reference

        // 3. reduce : Combines elements to produce a single result
        Optional<Integer> optionalInteger = list.stream().reduce((x, y) -> x + y);
        System.out.println(optionalInteger.get());

        Optional<Integer> optionalInteger1 = list.stream().reduce(Integer::sum); // Using method reference
        System.out.println(optionalInteger1.get());

        // 4. count
        long res = list.stream().filter(x -> (x & 1) == 0).count();
        // Here we have used terminal operation 'count()'.
        System.out.println(res);

        // 5. anyMatch, allMatch, noneMatch

        boolean b = list.stream().anyMatch(x -> x % 2 == 0);
        System.out.println(b);

        boolean b1 = list.stream().allMatch(x -> x > 0);
        System.out.println(b1);

        boolean b2 = list.stream().noneMatch(x -> x < 0);
        System.out.println(b2);

        // 6. findFirst, findAny
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // Examples: Filtering and Collecting Names
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");

        System.out.println(names.stream().filter(x -> x.length() > 3).collect(Collectors.toList()));

        // Examples: Squaring and Sorting Numbers
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 6);
        System.out.println(numbers.stream().map(x -> x * x).sorted().toList());

        // Examples: Summing Values
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.stream().reduce(Integer::sum).get());

        // Example: Counting Occurrences of a Character
        String sentence = "Hello world";
        System.out.println(sentence.chars().filter(x -> x == 'l').count());

        // stateful & stateless


    }
}
