package net.javaguides.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPracticeDemo {
    public static void main(String[] args) {

        /*
         Question: 5 advanced Java Stream API coding interview questions for experienced developers.
         */

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

        /*
         6. How can you convert a List<String> into a single comma-separated String using Stream API?
         */

        List<String> names = Arrays.asList("Ram", "Oliver", "Jhon", "Ajay");
        System.out.println(names.stream().collect(Collectors.joining(", ")));

        /*
         7. Using Stream API, how would you find the second highest number in a list of integers?
         */

        List<Integer> numbers = Arrays.asList(1, 5, 6, 2, 4);

        // My solution
        /*System.out.println(numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .get(1)); */

        System.out.println(numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst()
                .get());


        /*
         8. How do you group a list of objects by a specific field (e.g., department name) using Stream API?
         */

        Employee[] employeeList = {
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000),
                new Employee(2, "Ram", "Ghosh", "ram@gamil.com", "Finance", 60000),
                new Employee(3, "Rina", "Dutta", "rina@outlook.com", "HR", 20000),
                new Employee(4, "Amit", "Das", "amit@outlook.com", "Finance", 55000),
                new Employee(5, "Amrita", "Biswas", "amrita@outlook.com", "HR", 15000),
                new Employee(6, "Sailayee", "Singh", "sailayee@outlook.com", "HR", 17000)
        };

        System.out.println(Arrays.stream(employeeList).collect(Collectors.groupingBy(Employee::getDepartmentName)));

        /*
         9. How would you count the frequency of each character in a String using Stream API?
        */

        String str = "adkdfnadafa";

        System.out.println(str
                .chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.toMap(k -> k, v -> 1, (x, y) -> x + y)));

        /*
         10. Given a list of integers, use Stream API to calculate the sum of all even numbers.
        */

        List<Integer> list1 = Arrays.asList(9, 4, 2, 2, 3, 1, 6);

        System.out.println(list1.stream().filter(x -> (x & 1) == 0).reduce(Integer::sum).get());

        /*
         11. Given a list of strings, use Stream API to sort them by their length in descending order.
        */
        List<String> vhicelList = Arrays.asList("Car", "Helicopter", "Tank", "Bike", "Plane", "Truck", "Boat");

        System.out.println(vhicelList
                .stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList()));

        /*
         12. Given a list of integers, use Stream API to find all elements that appear more than once.
        */
        List<Integer> listNumbers = Arrays.asList(9, 1, 2, 2, 3, 5, 3, 5, 6, 7, 7, 8);
        System.out.println(listNumbers
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet()));

        /*
         13. Given a list of employees with a salary field, find the top 3 highest salaries using Stream API.
        */
        System.out.println(Arrays.stream(employeeList)
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList()));

        /*
         14. Find First Non-Repeated Character in a String Using Streams. Write a Stream-based solution to find the first
             non-repeated character in a given string (case-sensitive, including spaces).
        */

        System.out.println(listNumbers
                .stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().get());

        /*
         15. Nested Object Flattening with Streams. Given a list of Department objects, where each department contains a list of
             Employee objects, write a Stream-based solution to flatten all employees into a single list.
        */
        List<Department> departments = Arrays.asList(
                new Department("IT", Arrays.asList(
                        new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000),
                        new Employee(3, "Amit", "Das", "amit@gamil.com", "IT", 30000),
                        new Employee(5, "Riya", "DebNath", "riya@gamil.com", "IT", 32000)
                )),
                new Department("HR", Arrays.asList(
                        new Employee(2, "Rina", "Singh", "rina@gamil.com", "HR", 25000),
                        new Employee(6, "Ajay", "Sardar", "ajay@gamil.com", "HR", 20000),
                        new Employee(7, "Priya", "DebNath", "priya@gamil.com", "HR", 22000)
                )),
                new Department("Finance", Arrays.asList(
                        new Employee(4, "Jay", "Das", "jay@gamil.com", "Finance", 55000),
                        new Employee(8, "Surojit", "Mondal", "surojit@gamil.com", "Finance", 50000),
                        new Employee(9, "Puja", "Mukherjee", "puja@gamil.com", "Finance", 52000)
                ))
        );

        System.out.println(departments.stream().flatMap(x -> x.getEmployees().stream()).collect(Collectors.toList()));

        /*
         16. Longest Consecutive Sequence in a List. Given a list of integers (unsorted), find the longest consecutive increasing
             subsequence using Stream operations only.
        */
        List<Integer> numsList = Arrays.asList(3, 2, 3, 4, 5, 1, 7, 6);

        Set<Integer> numSet = new HashSet<>(numsList);

        List<Integer> longestSeq = numSet.stream()
                .filter(n -> !numSet.contains(n - 1)) // Only start of sequences
                .map(n -> {
                    List<Integer> seq = new ArrayList<>();
                    int current = n;
                    while (numSet.contains(current)) {
                        seq.add(current);
                        current++;
                    }
                    return seq;
                })
                .max(Comparator.comparingInt(List::size)) // Find the longest one
                .orElse(Collections.emptyList());

        System.out.println(longestSeq);

        // For length only do this
        /*
            Set<Integer> numSet = new HashSet<>(numbers); // For O(1) lookups

        int maxLen = numSet.stream()
            .filter(n -> !numSet.contains(n - 1)) // Start of a sequence
            .mapToInt(n -> {
                int current = n;
                while (numSet.contains(current + 1)) {
                    current++;
                }
                return current - n + 1; // Sequence length
            })
            .max()
            .orElse(0);

        System.out.println("Longest consecutive sequence length: " + maxLen);
        */

        /*
         17. Custom Collector to Count Word Frequencies. Given a list of strings (sentences), write a Stream-based solution to
             count the frequency of each word (ignoring case and punctuation) using a custom collector.
        */
        String sentence = "This Dog Good Bad Dog This Good Cat";
        System.out.println(Arrays.stream(sentence.split(" ")).
                collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        /*

        Multi-Level Grouping:
            Given a list of Employee objects, perform a multi-level grouping:
                1) First group by departmentName
                2) Then group by gender
                3) Then compute average salary for each group
         */

        List<Employee> empList = Arrays.asList(
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000, 'M'),
                new Employee(3, "Amit", "Das", "amit@gamil.com", "IT", 30000, 'M'),
                new Employee(2, "Rina", "Singh", "rina@gamil.com", "HR", 25000,'F'),
                new Employee(4, "Jay", "Das", "jay@gamil.com", "Finance", 55000,'M'),
                new Employee(8, "Surojit", "Mondal", "surojit@gamil.com", "Finance", 50000, 'M'),
                new Employee(9, "Puja", "Mukherjee", "puja@gamil.com", "Finance", 52000, 'F')
        );

        System.out.println(empList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName, // 1st level group by department
                        Collectors.groupingBy(Employee::getGender,          // 2nd level group by gender
                                Collectors.averagingDouble(Employee::getSalary)))));    // compute average salary

    }
}
