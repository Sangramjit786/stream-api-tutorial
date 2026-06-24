package net.javaguides.stream;

import java.util.Arrays;
import java.util.Comparator;

public class Test1 {
    public static void main(String[] args) {
        Integer[] numArray = {2, 3, 4, 6};
        System.out.println(Arrays.stream(numArray)
                .sorted(Comparator.reverseOrder()).distinct().skip(2).findFirst().get());
    }
}
