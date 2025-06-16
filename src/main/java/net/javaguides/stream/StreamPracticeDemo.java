package net.javaguides.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPracticeDemo {
    public static void main(String[] args) {

        // 1. Write Java Program to count of occurrence of each character in a string using stream api

        String word = "jfhadnenjri";

        Map<Character, Integer> hashMap = word.chars().mapToObj(c -> (char)c) /* Converting String to Chracter Stream */
                .collect(Collectors.toMap(k -> k, v -> 1, (x, y) -> x + y));

        System.out.println(hashMap);

        /*
         2. Let say you are given salary of employees, and you are given n arrays, where array contains employee objects. Where each
        employee objects contains salary. So, now you have to find average salary of employees using stream API.
         */
        Employee[] employees = {
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000),
                new Employee(2, "Ram", "Ghosh", "ram@gamil.com", "Finance", 60000),
                new Employee(3, "Rina", "Dutta", "rina@outlook.com", "HR", 20000)
        };

        System.out.println(Arrays.stream(employees).collect(Collectors.averagingInt(Employee::getSalary)));

        /*
         3. Using stream API merge two unsorted arrays into a single sorted array.
         */

        Integer[] arr1 = {5, 1, 2};
        Integer[] arr2 = {3, 2, 5};

        Integer[] result = Stream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .sorted()
                .toArray(Integer[]::new);

        System.out.println(Arrays.toString(result));

        /*
         4. Using java stream API write program to find sum of all digits of a number.
         */

        Integer nums = 12345;

        Integer sum = String.valueOf(nums)
                .chars() /* Converting to character stream */
                .map(c -> c - '0')
                .sum();
        System.out.println(sum);

        /*
         5. write a java program using stream API to sort a list of String, based on there length.
         */

        List<String> list = Arrays.asList("Employee", "FirstName", "LastName", "Id", "Department");
        System.out.println(list.stream().sorted((a, b) -> Integer.compare(a.length(), b.length())).collect(Collectors.toList()));


    }
}
