package net.javaguides.stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*write me a program to reverse a string without using string string builder and string buffer and string properties.
    You can use stream api  and lambda expression and count size.*/

public class Test2 {

    public static String reverseString(String input) {
        char[] charArray = input.toCharArray();

        // Use IntStream to reverse by mapping indices
        char[] reversed = IntStream.range(0, charArray.length)
                .mapToObj(i -> String.valueOf(charArray[charArray.length - 1 - i]))
                .collect(Collectors.joining())
                .toCharArray();

        return new String(reversed);
    }

    public static void main(String[] args) {
        String input = "Hello World";
        String reversed = reverseString(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);


        String str = "SANGRAM";

        int length = (int) str.chars().count();

        IntStream.iterate(length - 1, i -> i - 1)
                .limit(length)
                .mapToObj(i -> (char) str.charAt(i))
                .forEach(System.out::print);


        String str2 = "HELLO";

        int len = (int) str2.chars().count();

        String reversed1 =
                IntStream.range(0, len)
                        .mapToObj(i -> String.valueOf(str2.charAt(len - 1 - i)))
                        .collect(Collectors.joining());

        System.out.println(reversed);


        String str1 = "JAVA";

        IntStream.rangeClosed(1, (int) str.chars().count())
                .map(i -> str1.length() - i)
                .forEach(i -> System.out.print(str1.charAt(i)));
    }

    /*import java.util.stream.IntStream;

public class ReverseString {
    public static void main(String[] args) {


    }
}

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseString {
    public static void main(String[] args) {


    }
}

import java.util.stream.IntStream;

public class ReverseString {
    public static void main(String[] args) {

        String str = "JAVA";

        IntStream.rangeClosed(1, (int) str.chars().count())
                 .map(i -> str.length() - i)
                 .forEach(i -> System.out.print(str.charAt(i)));
    }
}
     */

}
