package net.javaguides.stream;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000.0),
                new Employee(2, "Ram", "Ghosh", "ram@gamil.com", "Finance", 60000.0),
                new Employee(3, "Rina", "Dutta", "rina@outlook.com", "HR", 20000.0)
        };

        System.out.println(Arrays.stream(employees).collect(Collectors.averagingDouble(Employee::getSalary)));

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
         7. Using Stream API, how would you find the second-highest number in a list of integers?
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
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000.0),
                new Employee(2, "Ram", "Ghosh", "ram@gamil.com", "Finance", 60000.0),
                new Employee(3, "Rina", "Dutta", "rina@outlook.com", "HR", 20000.0),
                new Employee(4, "Amit", "Das", "amit@outlook.com", "Finance", 55000.0),
                new Employee(5, "Amrita", "Biswas", "amrita@outlook.com", "HR", 15000.0),
                new Employee(6, "Sailayee", "Singh", "sailayee@outlook.com", "HR", 17000.0)
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
        List<Integer> listNumbers = Arrays.asList(9, 9, 1, 2, 2, 3, 5, 3, 5, 6, 7, 7, 8);
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
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
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
                        new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000.0),
                        new Employee(3, "Amit", "Das", "amit@gamil.com", "IT", 30000.0),
                        new Employee(5, "Riya", "DebNath", "riya@gamil.com", "IT", 32000.0)
                )),
                new Department("HR", Arrays.asList(
                        new Employee(2, "Rina", "Singh", "rina@gamil.com", "HR", 25000.0),
                        new Employee(6, "Ajay", "Sardar", "ajay@gamil.com", "HR", 20000.0),
                        new Employee(7, "Priya", "DebNath", "priya@gamil.com", "HR", 22000.0)
                )),
                new Department("Finance", Arrays.asList(
                        new Employee(4, "Jay", "Das", "jay@gamil.com", "Finance", 55000.0),
                        new Employee(8, "Surojit", "Mondal", "surojit@gamil.com", "Finance", 50000.0),
                        new Employee(9, "Puja", "Mukherjee", "puja@gamil.com", "Finance", 52000.0)
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

         18 .Multi-Level Grouping:
            Given a list of Employee objects, perform a multi-level grouping:
                1) First group by departmentName
                2) Then group by gender
                3) Then compute average salary for each group
         */

        List<Employee> empList = Arrays.asList(
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000.0, 'M'),
                new Employee(3, "Amit", "Das", "amit@gamil.com", "IT", 30000.0, 'M'),
                new Employee(2, "Rina", "Singh", "rina@gamil.com", "HR", 25000.0,'F'),
                new Employee(4, "Jay", "Das", "jay@gamil.com", "Finance", 55000.0,'M'),
                new Employee(8, "Surojit", "Mondal", "surojit@gamil.com", "Finance", 50000.0, 'M'),
                new Employee(9, "Puja", "Mukherjee", "puja@gamil.com", "Finance", 52000.0, 'F')
        );

        System.out.println(empList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName, // 1st level group by department
                        Collectors.groupingBy(Employee::getGender,          // 2nd level group by gender
                                Collectors.averagingDouble(Employee::getSalary)))));    // compute average salary

        /*
         19. Sliding Window Maximum Using Streams
                Problem:
                    Given a list of integers and a window size k, find the maximum of each sliding window using Java Streams.
        */

        List<Integer> numberList = Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7);
        int k = 3;

        System.out.println(IntStream.rangeClosed(0, numberList.size() - k)
                .mapToObj(i -> numberList.subList(i, i + k))                  // create sublist for each window
                .map(window -> Collections.max(window))              //  get max from each window
                .collect(Collectors.toList()));


        /*
         20. Stream-Based Joins Between Two Lists
                Problem:
                    Given two lists — one of Employee objects and one of Department objects — join them using departmentId
                    (like SQL inner join) using Stream API.
        */
        List<Employee> employees1 = Arrays.asList(
                new Employee(1, "Riya", "HR", 101),
                new Employee(2, "Amit", "IT", 102),
                new Employee(3, "Jay", "Finance", 103)
        );

        List<Department> departments1 = Arrays.asList(
                new Department(101, "HR"),
                new Department(102, "IT"),
                new Department(103, "Finance")
        );

        List<EmployeeDepartmentDTO> joined = employees1.stream()
                .flatMap(emp -> departments1.stream()
                        .filter(dept -> dept.getId() == emp.getDepartmentId())
                        .map(dept -> new EmployeeDepartmentDTO(emp.getFirstName(), dept.getName()))
                )
                .collect(Collectors.toList());

        System.out.println(joined);

        /*
         21. Group Employees by Joining Date’s Year and Then by Department
                Problem:
                    Given a list of employees with joining dates, group them by year of joining and within each year, by department
                    name.
        */

        List<Employee> empLists = Arrays.asList(
                new Employee(1, "Rohit", "Nath", "rohit@gamil.com", "IT", 35000.0, 'M', LocalDate.of(2023, 2, 22)),
                new Employee(3, "Amit", "Das", "amit@gamil.com", "IT", 30000.0, 'M', LocalDate.of(2025, 3, 20)),
                new Employee(2, "Rina", "Singh", "rina@gamil.com", "HR", 25000.0,'F', LocalDate.of(2025, 2, 10)),
                new Employee(4, "Jay", "Das", "jay@gamil.com", "Finance", 55000.0,'M', LocalDate.of(2024, 1, 19)),
                new Employee(8, "Surojit", "Mondal", "surojit@gamil.com", "Finance", 50000.0, 'M', LocalDate.of(2023, 4, 22)),
                new Employee(9, "Puja", "Mukherjee", "puja@gamil.com", "Finance", 52000.0, 'F', LocalDate.of(2022, 3, 25))
        );

        System.out.println(empLists.stream().collect(Collectors.groupingBy(x -> x.getDate().getYear(), Collectors.groupingBy(x -> x.getDepartmentName()))));

        /*
         22. Custom Collector to Partition and Sort
                    Problem:
                        Given a list of numbers, partition them into even and odd, then return each list sorted in descending order.
        */

        Map<Boolean, List<Integer>> partitionedSorted = numsList.stream()
                .collect(Collectors.partitioningBy(
                        n -> n % 2 == 0, // Partition by even (true) or odd (false)
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                eachList -> eachList.stream()
                                        .sorted(Comparator.reverseOrder())
                                        .collect(Collectors.toList())
                        )
                ));

        System.out.println("Even (Sorted Desc): " + partitionedSorted.get(true));
        System.out.println("Odd  (Sorted Desc): " + partitionedSorted.get(false));

        /*
         23. Calculate Weighted Average by Category
                Problem:
                    Given a list of Transaction objects (with category, amount, and weight), calculate the weighted average amount
                    per category.
        */

        List<Transaction> transactions = Arrays.asList(
                new Transaction("Food", 100, 0.5),
                new Transaction("Food", 200, 1.0),
                new Transaction("Travel", 300, 0.3),
                new Transaction("Travel", 400, 0.7),
                new Transaction("Entertainment", 150, 1.0)
        );

        Map<String, Double> weightedAvgByCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collector.of(
                                () -> new double[2], // [0] = weighted sum, [1] = total weight
                                (a, t) -> {
                                    a[0] += t.getAmount() * t.getWeight();
                                    a[1] += t.getWeight();
                                },
                                (a, b) -> {
                                    a[0] += b[0];
                                    a[1] += b[1];
                                    return a;
                                },
                                a -> a[1] == 0 ? 0.0 : a[0] / a[1]
                        )
                ));


        weightedAvgByCategory.forEach((category, avg) ->
                System.out.println("Category: " + category + ", Weighted Average: " + avg));

        /*
         24. remove all vowels from a given string
        */

        String inputStr = "Rohit";

        System.out.println(inputStr.chars().mapToObj(c -> (char)c)
                .filter(c -> !"aeiouAEIOU".contains(c.toString()))
                .map(c -> String.valueOf(c))
                .collect(Collectors.joining()));

        /*
         25. Conver a list of string to uppercase
        */

        System.out.println(vhicelList.stream().map(c -> c.toUpperCase()).collect(Collectors.toList()));

        /*
         26. Count of frequency of words in a list
        */

        List<String> langList = Arrays.asList("Java", "C", "C++", "Python", "Java", "C++", "java", "python");
        // First Way
        System.out.println(langList.stream().collect(Collectors.toMap(a -> a, v -> 1, (x, y) -> (x + y))));
        // Second Way
        System.out.println(langList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        /*
         27. Count of frequency of words in a String
        */

        String words = "Java C C++ Python Java C++ Java Python";

        System.out.println(Arrays.stream(words.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        /*
         28. Count of occurrence of each character in a String
        */
        System.out.println(words.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        /*
         29. Length of each word in a string
        */

        System.out.println(Arrays.stream(words.split(" ")).distinct().collect(Collectors.toMap(Function.identity(), String::length)));

        /*
         30. Find First Repeated Character in a String Using Streams. Write a Stream-based solution to find the first
             non-repeated character in a given string (case-sensitive, including spaces).
        */

        System.out.println(listNumbers
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey )
                .findFirst().get());

        /*
         31. Write a program to find second-largest element
        */

        int[] numArray = {3, 2, 3, 4, 5, 1, 7, 6};

        System.out.println(Arrays
                .stream(numArray).boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get());

        /*
         32. Longest String in an Array of String
        */

        System.out.println(Arrays.stream(words.split(" ")).sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList()).getFirst());

        /*
         33. Find total number of count of a particular word
        */
        List<String> sentenceList = Arrays.asList("java python java html", "java java css html", "hadoop spark java");

        // First Solution
        System.out.println(sentenceList
                .stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toMap(a -> a, v -> 1, (x, y) -> (x + y)))
                .get("java"));

        // Second Solution
        System.out.println(sentenceList
                .stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(word1 -> word1.equalsIgnoreCase("java"))
                .count());

        /*
        34. Convert upper to lower and lower to upper in a given string
        */

        String toggleStr = "SwIsS123@";

        System.out.println(toggleStr.chars()
                .mapToObj(c -> {
                    if (Character.isUpperCase(c)) {
                        return Character.toLowerCase((char) c);
                    } else if (Character.isLowerCase(c)) {
                        return Character.toUpperCase((char) c);
                    } else {
                        return (char) c; // leave digits and symbols unchanged
                    }
                })
                .map(String::valueOf)
                .collect(Collectors.joining()));



        /*
         35. How would you group a list of employees first by department, then by designation, and compute the total salary and also average solution for each
         subgroup using Stream API?
        */

        List<Employee> employeeLists = Arrays.asList(
                new Employee(1, "Rohit", "IT", 35000.0, "C1"),
                new Employee(3, "Amit", "IT", 30000.0, "C2"),
                new Employee(2, "Rina", "HR", 25000.0,"Y"),
                new Employee(4, "Jay", "Finance", 55000.0,"C2"),
                new Employee(8, "Surojit", "Finance", 50000.0, "C2"),
                new Employee(9, "Puja", "Finance", 52000.0, "C1")
        );

        System.out.println(employeeLists
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName,
                        Collectors.groupingBy(Employee::getDesignation, Collectors.summingDouble(Employee::getSalary)))));

        System.out.println(employeeLists
                .stream()
                .collect(Collectors
                        .groupingBy(Employee::getDepartmentName,
                                Collectors
                                        .groupingBy(Employee::getDesignation,
                                                Collectors
                                                        .averagingDouble(Employee::getSalary)))));

        /*
         36. Using Java Streams, how do you find the second-highest unique number from a list of integers?
        */

        System.out.println(listNumbers.stream().sorted(Comparator.reverseOrder()).distinct().skip(1).findFirst().get());

        /*
         37. How can you implement a custom collector that concatenates a stream of strings into a single comma-separated
             uppercase string?
        */

        System.out.println(vhicelList.stream().map(String::toUpperCase).collect(Collectors.joining(",")));

        /*
         38. What potential issues can arise when using peek() inside a parallel stream, especially when performing logging
             or debugging operations?
        */

        /*
            Problem Explanation:
                Using .peek() inside a parallel stream for side effects like logging or debugging can cause unexpected, incorrect,
                or jumbled output due to the non-deterministic and concurrent execution of stream elements.
        */
        List<String> characters = Arrays.asList("A", "B", "C", "D");

        characters.parallelStream()
                .peek(name -> System.out.println(Thread.currentThread().getName() + " - " + name))
                .forEach(System.out::println);

        /*
         39. Given a list of orders where each order contains multiple line items, how would you extract a flattened list of
             unique product names using Stream API?
        */

        List<Order> orderList = Arrays.asList(
                new Order(1, Arrays.asList(new LineItem("SUZUKI"), new LineItem("TATA"), new LineItem("AUDI"))),
                new Order(2, Arrays.asList(new LineItem("TOMATO"), new LineItem("POTATO"), new LineItem("SUGAR"))),
                new Order(3, Arrays.asList(new LineItem("CAULIFLOWER"), new LineItem("ONION"), new LineItem("SUGAR"))),
                new Order(3, Arrays.asList(new LineItem("Protine"), new LineItem("Amino Acid"), new LineItem("Lysine"))),
                new Order(3, Arrays.asList(new LineItem("Progress "), new LineItem("Process"), new LineItem("Proceed"))),
                new Order(3, Arrays.asList(new LineItem("Proactive "), new LineItem("Professional"), new LineItem("Proceed")))
        );

        System.out.println(orderList.stream()
                .flatMap(x -> x.getLineItems().stream())
                .map(x -> x.getProductName())
                .distinct()
                .collect(Collectors.toList()));


        /*
         40. Given a List<Product> with fields name, category, and price, find the top 3 most expensive products in each category
             using the Stream API.
        */

        List<Product> products = Arrays.asList(
                new Product("Tomato", "vegetable", 45.0),
                new Product("Potato", "vegetable", 30.0),
                new Product("Nails", "hardware", 50.0),
                new Product("Toy Bike", "toy", 60.0),
                new Product("Pipe", "hardware", 180.0),
                new Product("Teddy Bear", "toy", 400.0),
                new Product("Onion", "vegetable", 49.0),
                new Product("Garlic", "vegetable", 54.0),
                new Product("Maruti", "vhicle", 600000.0)
        );

        Map<String, List<Product>> top3ByCategory = products
                .stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                prodList -> prodList
                                        .stream()
                                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                        .limit(3)
                                        .collect(Collectors.toList())
                        )));

        top3ByCategory.forEach((category, topProducts) -> {
            System.out.println("\nCategory: " + category);
            topProducts.forEach(x -> System.out.println("Product Name: " + x.getName() + ", Price: " + x.getPrice()));
        });


        /*
          When we will use : 'collectingAndThen()' =>

            1) When you want to transform the collected result further.
            2) In grouping scenarios (e.g., top N elements in a group).
        */

        /*
         41 . How would you implement a custom collector using Collector.of() that concatenates a stream of strings into a
              single comma-separated string, converting each to uppercase?
        */

        Stream<String> nameslist = Stream.of("rohit", "amit", "rina");

        String res = nameslist.collect(Collector.of(
                StringBuilder::new,                         // Supplier: creates new mutable container
                (sb, str1) -> sb.append(str1.toUpperCase()).append(","), // Accumulator
                StringBuilder::append,                      // Combiner: used in parallel streams
                sb -> sb.length() > 0                       // Finisher: remove trailing comma
                        ? sb.substring(0, sb.length() - 1)
                        : ""
        ));

        System.out.println(res);  // Output: ROHIT,AMIT,RINA


        /*
         42. Given a List<Order>, where each Order contains a List<LineItem>, write a Stream API solution to extract a list of
             all unique product names that contain the word "Pro".
        */

        System.out.println(orderList
                .stream()
                .flatMap(x -> x.getLineItems().stream())
                .map(x -> x.getProductName())
                .filter(productName -> productName.contains("Pro"))
                .distinct()
                .collect(Collectors.toList()));

        /*
         43. Given a list of strings, use the Stream API to build a Map<String, Long> that counts the frequency of each word,
             case-insensitively.
        */

        System.out.println(langList
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        /*
         44. Given a List<Order> where each Order has a List<LineItem>, flatten all line items and group them by product category,
             summing the total price for each category.
        */

        List<Order> orderLists = Arrays.asList(
                new Order(1, Arrays.asList(new LineItem("SUZUKI", "Car", 680000.0), new LineItem("TATA", "Car", 1080000.0), new LineItem("AUDI", "Car", 1580000.0))),
                new Order(2, Arrays.asList(new LineItem("TOMATO", "Vegetable", 40.0), new LineItem("POTATO", "Vegetable", 20.0), new LineItem("SUGAR", "Groceries", 40.0))),
                new Order(3, Arrays.asList(new LineItem("CAULIFLOWER", "Vegetable", 20.0), new LineItem("ONION", "Vegetable", 43.0), new LineItem("SUGAR", "Groceries", 40.0))),
                new Order(3, Arrays.asList(new LineItem("Protine", "biomolecules", 2000.0), new LineItem("Amino Acid", "biomolecules", 2300.0), new LineItem("Lysine", "biomolecules", 1500.0))),
                new Order(3, Arrays.asList(new LineItem("Progress ", "Improvement", 0.0), new LineItem("Process", "Improvement", 0.0), new LineItem("Proceed", "Improvement", 0.0))),
                new Order(3, Arrays.asList(new LineItem("Proactive ", "Improvement", 0.0), new LineItem("Professional", "Improvement", 0.0), new LineItem("Proceed", "Improvement", 0.0)))
        );

        System.out.println(orderLists.stream()
                .flatMap(x -> x.getLineItems().stream())
                .collect(Collectors.groupingBy(x -> x.getCategory(), Collectors.averagingDouble(x -> x.getPrice()))));


        /*
         45. Using Stream API, implement a transformation that assigns a unique incremental ID (e.g., starting from 1000)
             to each element of a stream of Customer objects.
        */

        List<Customer> customers = Arrays.asList(
                new Customer("Rohit"),
                new Customer("Puja"),
                new Customer("Jay"),
                new Customer("Amit")
        );

        AtomicInteger counter = new AtomicInteger(1000);

        List<IdentifiedCustomer> ans = customers.stream()
                .map(c -> new IdentifiedCustomer(counter.getAndIncrement(), c.getName()))
                .collect(Collectors.toList());

        ans.forEach(System.out::println);

        /*
         46. You're given a large List<Transaction> and asked to compute the top 5 customers by transaction volume using parallel
             streams. What pitfalls should you avoid, and how would you implement it efficiently.
        */
    
        List<Transaction> trans = List.of(
                new Transaction("C1", 5000),
                new Transaction("C2", 8000),
                new Transaction("C1", 4000),
                new Transaction("C3", 10000),
                new Transaction("C2", 2000),
                new Transaction("C4", 9000),
                new Transaction("C5", 3000),
                new Transaction("C6", 15000),
                new Transaction("C7", 4000)
        );

        Map<String, Double> totalByCustomer = trans
                .parallelStream()
                .collect(Collectors.groupingByConcurrent(
                        Transaction::getCustomerId,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        List<Map.Entry<String, Double>> top3 = totalByCustomer.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        top3.forEach(e ->
                System.out.println("Customer: " + e.getKey() + ", Total Volume: " + e.getValue())
        );

        /*
            Tips to Ensure Thread-Safety & Performance:

                Always use groupingByConcurrent with parallel streams for aggregation.
                Avoid shared mutable state (e.g., don't use a shared HashMap manually).
                Do sorting and limiting in a final sequential stream, not inside parallel blocks.
        */

        /*
         47. Given a list of Strings, group them by their first character and count the number of elements in each group.
        */

        List<String> wordLists = List.of("apple", "apricot", "banana", "blueberry", "cherry");

        System.out.println(wordLists
                .stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0), Collectors.counting())));

        /*
         48. Given a list of integers, return a map of elements that appear more than once along with their counts.
        */

        List<Integer> nos = List.of(1, 2, 3, 2, 3, 4, 5, 3);

        System.out.println(nos.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toList()));

        /*
         49. Given a List<List<String>>, flatten it and return all unique lowercase words sorted alphabetically.
        */

        List<List<String>> nested = List.of(
                List.of("Hello", "world"),
                List.of("HELLO", "java"),
                List.of("Streams", "JAVA")
        );

        System.out.println(nested
                .stream()
                .flatMap(x -> x.stream())
                .distinct()
                .sorted()
                .filter(x -> x.chars().noneMatch(Character::isUpperCase))
                .collect(Collectors.toList()));

        /*
         50. Given a list of Employee objects, return a list of employee names who:
                belong to the "Engineering" department
                have a salary > 80,000
                and sort them by salary descending.
        */

        List<Employee> emplist = Arrays.asList(
                new Employee("Rohit", "IT", 35000.0),
                new Employee("Amit", "IT", 90000.0),
                new Employee("Rina", "HR", 25000.0),
                new Employee("Jay", "Finance", 55000.0),
                new Employee("Surojit", "Finance", 50000.0),
                new Employee("Puja", "Finance", 52000.0)
        );

        System.out.println(emplist.stream().filter(e -> e.getDepartmentName().equals("IT"))
                .filter(e -> e.getSalary() > 80000)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getFirstName).collect(Collectors.toList()));

        /*
         51. Given a list of words, group them by their first character and find the longest word for each group.
        */

        List<String> wordslist = List.of("apple", "apricot", "banana", "blueberry", "cherry", "cranberry", "avocado");

        System.out.println(wordslist
                .stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0), Collectors.maxBy(Comparator.comparingInt(String::length)))));

        /*
         52. Given a list of Transaction objects, group them by customerId and compute the average transaction amount for each customer.
        */

        System.out.println(trans.stream()
                .collect(Collectors.groupingBy(x -> x.getCustomerId(),
                        Collectors.averagingDouble(x -> x.getAmount()))));

        /*
         53. Given a list of strings, return a new list where each string is uppercased and sorted by length descending,
             preserving order only if stream is sequential.
        */

        List<String> fruits = List.of("apple", "banana", "kiwi", "strawberry", "fig");
        System.out.println(fruits.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList()));

        /*
         54. Given a list of words, group them into lists of anagrams.
        */
        List<String> input = List.of("listen", "silent", "enlist", "google", "gooegl", "cat", "act");

        Collection<List<String>> groupedAnagrams = input.stream()
                .collect(Collectors.groupingBy(
                        eachWord -> sortCharacters(eachWord), // key: sorted letters
                        LinkedHashMap::new,            // preserves insertion order
                        Collectors.toList()            // collect anagrams
                ))
                .values(); // extract only the list groups

        // Print output
        groupedAnagrams.forEach(System.out::println);

        /*
         55. Given a list of integers with possible duplicates, find the 3rd highest distinct number.
        */

        System.out.println(numsList.stream().distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst().get());

        /*
         56. Given a list of Transaction objects with a LocalDate field, group them by month and return the total transaction
             amount per month.
        */

        List<Transaction> transDateList = List.of(
                new Transaction(5000, LocalDate.of(2023, 2, 22)),
                new Transaction(8000, LocalDate.of(2025, 3, 20)),
                new Transaction(4000, LocalDate.of(2025, 2, 10)),
                new Transaction(10000, LocalDate.of(2024, 1, 19)),
                new Transaction(2000, LocalDate.of(2023, 4, 22)),
                new Transaction(9000, LocalDate.of(2022, 3, 25)),
                new Transaction(3000, LocalDate.of(2023, 2, 22)),
                new Transaction(15000, LocalDate.of(2025, 3, 20)),
                new Transaction(4000, LocalDate.of(2025, 2, 10))
        );

        System.out.println(transDateList
                .stream()
                .collect(Collectors.groupingBy(
            x -> x.getDate().getMonth(),
                        Collectors.summingDouble(Transaction::getAmount))));


        /*
         57. Write a method that takes a String and returns the character with the highest frequency using the Stream API.
        */

        String letters = "functionalprogramming";

        System.out.println(letters.chars().mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(entry -> Math.toIntExact(entry.getValue())))
                .get()
                .getKey());

        /*
         58. Given a list of integers, use streams to partition them into prime and non-prime numbers.
        */

        List<Integer> numberlists = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(numberlists.stream().collect(Collectors.partitioningBy(x -> isPrime(x))));

        /*
         59. You have a list of objects, each containing a list of integers. Flatten all integers into a single sorted list.
        */

        List<Container> containers = List.of(
                new Container("A", List.of(1, 2, 3)),
                new Container("B", List.of(4, 5, 6)),
                new Container("C", List.of(7, 8, 9))
        );

        System.out.println(containers.stream()
                .flatMap(x -> x.getNumbers().stream())
                .sorted().collect(Collectors.toList()));

        /*
         60. Given a list of strings, use reduce() to find the length of the longest word.
        */

        System.out.println(fruits.stream()
                .reduce((x, y) -> x.length() > y.length() ? x : y).get());

        /*
         61. Given a list of Order objects with a LocalDate field, group them by year and count how many orders occurred each year.
        */

        List<Order> orders = List.of(
                new Order(LocalDate.of(2023, 2, 22), 5000),
                new Order(LocalDate.of(2024, 1, 19), 10000),
                new Order(LocalDate.of(2025, 3, 20), 8000),
                new Order(LocalDate.of(2025, 2, 10), 4000),
                new Order(LocalDate.of(2022, 3, 25), 9000),
                new Order(LocalDate.of(2023, 2, 22), 3000),
                new Order(LocalDate.of(2025, 3, 20), 15000),
                new Order(LocalDate.of(2025, 2, 10), 4000)
        );

        System.out.println(orders.stream()
                .collect(Collectors.groupingBy(x -> x.getOrderDate().getYear(), Collectors.counting())));

        /*
         62. Find the second highest salary of employees
        */
        List<Employee> employeesList = List.of(new Employee("Ram", 60000.0), new Employee("Shyam", 50000.0), new Employee("John", 70000.0), new Employee("Rohit", 80000.0));

        System.out.println("Second highest Salary: " + employeesList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().get().getSalary());

        /*
         63. Group employees by department and find the highest-paid employee in each department
        */

        List<Employee> empdetaillist = Arrays.asList(
                new Employee("Rohit", "IT", 35000.0),
                new Employee("Amit", "IT", 90000.0),
                new Employee("Rina", "HR", 25000.0),
                new Employee("Jay", "Finance", 55000.0),
                new Employee("Surojit", "Finance", 50000.0),
                new Employee("Puja", "Finance", 52000.0),
                new Employee("Papon", "HR", 27000.0)
        );


        empdetaillist
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))))
                .forEach((dept, opt) ->
                opt.ifPresent(emp ->
                        System.out.println("Departmet: " + dept + ", Highest Paid: " + emp.getFirstName())));

        /*
         64. Find the longest word in a sentence.
        */
        sentence = "Java 8 Stream API provides functional-style operations";
        System.out.println(Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).get());

        /*
         65. Flatten and find distinct elements from nested lists. Find all distinct numbers across all lists, in sorted order.
        */
        List<List<Integer>> numbersList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(2, 4, 6),
                Arrays.asList(3, 6, 9)
        );

        System.out.println(numbersList.stream().flatMap(List::stream).distinct().sorted().collect(Collectors.toList()));

        /*
         66. Find top 3 most frequent words in a paragraph
        */
        String text = "java stream api in java is powerful java api stream stream";
        Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach((key) ->
                        System.out.println(key.getKey()));

        /*
         67. From a list of employees, find the department that has the highest average salary.
        */

        empdetaillist = Arrays.asList(
                new Employee("Rohit", "IT", 35000.0),
                new Employee("Amit", "IT", 90000.0),
                new Employee("Rina", "HR", 25000.0),
                new Employee("Jay", "Finance", 55000.0),
                new Employee("Surojit", "Finance", 50000.0),
                new Employee("Puja", "Finance", 52000.0),
                new Employee("Papon", "HR", 27000.0)
        );

        System.out.println(empdetaillist
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName,
                        Collectors.averagingDouble(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey());

        /*
         68. Employee with nth highest salary in each department
        */

        List<Employee> employeeList1 = Arrays.asList(
                new Employee(1, "Riya", 35000.0, "HR"),
                new Employee(2, "Amit", 55000.0,"IT"),
                new Employee(3, "Jay", 60000.0, "Finance"),
                new Employee(4, "Rohit", 25000.0, "HR"),
                new Employee(5, "Ajay", 52000.0,"IT"),
                new Employee(6, "Puja", 55000.0, "Finance"),
                new Employee(7, "Taniya", 27000.0, "HR"),
                new Employee(8, "Roy", 57000.0,"IT"),
                new Employee(9, "Neha", 61000.0, "Finance")
        );

        int n = 2;

        employeeList1
                .stream()
                .collect(
                        Collectors.groupingBy(
                        Employee::getDepartmentName,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        empList1 -> empList1
                                .stream()
                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                .skip(n - 1)
                                .findFirst())))
                .forEach((dept, prods) -> {
                    System.out.println("Id: " + prods.get().getId() + ", Department: " + dept +", Employee :" + prods.get().getFirstName());
                }
                );

        /*
         69. Find top 3 longest strings grouped by first character
        */

        List<String> wordsList = Arrays.asList("apple", "application", "ant", "banana", "ball", "batman", "cat", "caterpillar", "an", "bat");

        wordsList
                .stream()
                .collect(Collectors.groupingBy(wrd -> wrd.charAt(0),
                Collectors.collectingAndThen(Collectors.toList(),
                        wrdList -> wrdList
                                .stream()
                                .sorted(Comparator.comparingInt(String::length).reversed())
                                .limit(3))))
                .forEach((ch, wrdlist) -> {
                    System.out.println("Character: " + ch + ", Words: " + wrdlist.toList());
                        }
                );

        /*
         70. Find common elements across multiple lists
        */

        List<Integer> list_1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list_2 = Arrays.asList(2, 3, 6, 7);
        List<Integer> list_3 = Arrays.asList(3, 8, 9, 2);

        System.out.println(
                Stream.of(list_1, list_2, list_3)
                        .map(HashSet::new)
                        .reduce((set1, set2) -> {
                            set1.retainAll(set2);
                            return set1;
                        })
                .orElse(new HashSet<>())
                .stream()
                .collect(Collectors.toList()));

        /*
         71. Transaction Logs: Find the top 3 customers by transaction amount
        */


        List<Transaction> transAmounts = List.of(
                new Transaction("C1", 5000),
                new Transaction("C2", 8000),
                new Transaction("C1", 4000),
                new Transaction("C3", 10000),
                new Transaction("C2", 2000),
                new Transaction("C4", 9000),
                new Transaction("C5", 3000),
                new Transaction("C6", 15000),
                new Transaction("C7", 4000)
        );

        System.out.println(transAmounts
                .stream()
                .collect(Collectors.groupingBy(
                        t1 -> t1.getCustomerId(),
                        Collectors.summingDouble(t2 -> t2.getAmount())))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList()));

        /*
         72. Strings: Group words by first letter and pick the longest word in each group
        */

        List<String> wordList = Arrays.asList("apple", "ant", "application", "banana", "ball", "batman", "cat", "caterpillar");

        System.out.println(wordsList
                .stream()
                .collect(
                        Collectors.groupingBy(x -> x.charAt(0),
                                Collectors.collectingAndThen(
                Collectors.toList(),
                l -> l
                        .stream()
                        .max(Comparator.comparingInt(String::length))
                        .get()
        ))));


        /*
            73. Nested List Flattening & Aggregation
        */

        List<List<Integer>> scores = Arrays.asList(
                Arrays.asList(70, 85, 90),
                Arrays.asList(60, 88),
                Arrays.asList(95, 80, 75, 99)
        );

        System.out.println(scores
                .stream()
                .flatMap(x -> x.stream())
                .max(Comparator.comparingInt(Integer::intValue))
                .get());

        /*
         74. Word Frequency Counter (Case-Insensitive)
        */

        text = "Java is great. Java Stream API is powerful. Stream API is amazing.";

        System.out.println(
                Arrays.stream(text.replace(".", "").split(" "))
                        .map(String::toLowerCase)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toList())
        );

        /*
         75. Find Common Elements Across Multiple Lists
        */

        list_1 = Arrays.asList(1, 2, 3, 4, 5);
        list_2 = Arrays.asList(3, 4, 5, 6, 7);
        list_3 = Arrays.asList(5, 7, 9, 3);

        System.out.println(
                Stream.of(list_1, list_2, list_3)
                        .map(HashSet::new)
                        .reduce((set1, set2) -> {
                            set1.retainAll(set2);
                            return set1;
                        })
                        .orElse(new HashSet<>())
                        .stream()
                        .collect(Collectors.toList())
        );

        /*
         76. Character Frequency from a List of Strings
        */
        List<String> wrds = Arrays.asList("apple", "banana", "mango");

        System.out.println(
                wrds.stream()
                        .reduce((s1, s2) -> s1 + s2)
                        .get()
                        .chars()
                        .mapToObj(ch -> (char)ch)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        /*
         77. Merge all lists, remove duplicates, and return them in descending order.
        */

        list_1 = Arrays.asList(10, 20, 30, 40);
        list_2 = Arrays.asList(30, 40, 50, 60);
        list_3 = Arrays.asList(20, 60, 70, 80);

        System.out.println(
                Stream.of(list_1, list_2, list_3)
                        .flatMap(List::stream)
                        .distinct()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList())
        );

        /*
         78. Find the top 3 most frequent words (ignore case and punctuation).
        */
        text = "Stream API in Java is powerful. Java developers love Stream API.";
        System.out.println(Arrays.stream(text
                        .toLowerCase()
                        .replace(".", "").split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList()));

        /*
         79. Group employees into age bands (<30, 30-40, >40) using Stream groupingBy, and within each band, count how many
             employees exist.
        */

        empdetaillist = Arrays.asList(
                new Employee(28, "IT", "Rohit"),
                new Employee(25, "IT", "Amit"),
                new Employee(40, "HR", "Rina"),
                new Employee(35, "Finance", "Jay"),
                new Employee(30, "Finance", "Surojit"),
                new Employee(41, "Finance", "Puja"),
                new Employee(44, "HR", "Papon")
        );

        System.out.println(empdetaillist.stream().collect(Collectors.groupingBy(emp -> {
            if (emp.getAge() < 30){
                return "Less then 30: ";
            } else if(emp.getAge() >= 30 && emp.getAge() <= 40) {
                return "Between 30 and 40: ";
            } else {
                return "Greater then 40: ";
            }
        }, Collectors.counting())));

        /*
         80. Most Repeated Element in a List
        */

        numbers = Arrays.asList(5, 3, 9, 5, 3, 5, 7, 3, 3, 9, 5, 5);

        numbers
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .forEach(e1 -> System.out.println("Most frequent number: " + e1.getKey()));

        /*
         81. Department-Wise Average Salary of Top 2 Earners.
        */

        empdetaillist = Arrays.asList(
                new Employee("Rohit", "IT", 35000.0),
                new Employee("Amit", "IT", 90000.0),
                new Employee("Rina", "HR", 25000.0),
                new Employee("Jay", "Finance", 55000.0),
                new Employee("Surojit", "Finance", 50000.0),
                new Employee("Puja", "Finance", 52000.0),
                new Employee("Papon", "HR", 27000.0),
                new Employee("Roy", "IT", 57000.0),
                new Employee("Taniya", "HR", 27000.0)
        );


        // My solution:
        System.out.println("My solution: ");
        empdetaillist
                .stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartmentName(),
                        Collectors.collectingAndThen(Collectors.toList(),
                                l1 -> l1
                                        .stream()
                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                .limit(2)
                                .collect(Collectors.toList()))))

                .forEach((key, value) -> {
                    System.out.println("Department: " + key + ", Average of Top 2 salary: " + value
                            .stream()
                            .mapToDouble(Employee::getSalary)
                            .average()
                            .getAsDouble());
                });


        //AI solution:
        System.out.println("AI solution: ");
        empdetaillist.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartmentName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l1 -> l1.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                        .limit(2)
                                        .collect(Collectors.averagingDouble(Employee::getSalary))
                        )
                ))
                .forEach((dept, avgSalary) ->
                        System.out.printf("Department: %s, Average of Top 2 salary: %.2f%n", dept, avgSalary)
                );


        /*
         82. find the longest word in the sentence. If there are multiple with the same length, return any one.
        */

        sentence = "Java Stream API makes functional-style programming easier";

        System.out.println(Arrays.stream(sentence.split(" "))
                .max(Comparator.comparingInt(String::length))
                .get());

        /*
         83. Find First Non-Repeating Character in a String
        */
        str = "swiss";

        str.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .ifPresent(entry -> System.out.println("First unique character: " + entry.getKey()));

        /*
         84. Find the top 3 most frequent words and their counts, sorted by frequency (descending).
        */

        List<String> words_list = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana",
                "apple", "mango", "banana", "grapes", "apple",
                "orange", "mango", "banana"
        );

        words_list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));


        /*
         85. Increase the salary by 10% for employees in "IT" department earning less than 50,000 and return a new List of
             updated Employees, keeping others unchanged.
        */

        empdetaillist = Arrays.asList(
                new Employee(1, "John", 45000.0, "IT"),
                new Employee(2, "Alice", 55000.0, "HR"),
                new Employee(3, "Bob", 60000.0, "IT"),
                new Employee(4, "David", 40000.0, "Finance"),
                new Employee(5, "Sophia", 30000.0, "IT")
        );

        empdetaillist.stream()
                .map(e -> {
                    if (e.getDepartmentName().equals("IT") && e.getSalary() < 50000.0) {
                        return new Employee(e.getId(), e.getFirstName(), e.getSalary() * 1.1, e.getDepartmentName());
                    }
                    return e;
                }).forEach(e -> {
                    System.out.println("Emp Id: " + e.getId() + ", Name: " + e.getFirstName() + ", Salary: " + e.getSalary() + ", Department: " + e.getDepartmentName());
                });

        /*
         86. Using streams, find the total successful transaction amount per user,
             but only for users who have made at least 3 successful transactions.
        */

        List<Transaction> transactionsList = Arrays.asList(
                new Transaction(101, 1, 200.0, "SUCCESS"),
                new Transaction(102, 1, 150.0, "FAILED"),
                new Transaction(103, 1, 300.0, "SUCCESS"),
                new Transaction(104, 1, 400.0, "SUCCESS"),
                new Transaction(105, 2, 500.0, "SUCCESS"),
                new Transaction(106, 2, 600.0, "FAILED"),
                new Transaction(107, 2, 700.0, "SUCCESS"),
                new Transaction(108, 3, 800.0, "SUCCESS"),
                new Transaction(109, 3, 900.0, "SUCCESS"),
                new Transaction(110, 3, 1000.0, "SUCCESS"),
                new Transaction(111, 3, 1200.0, "FAILED")
        );

        System.out.println(transactionsList.stream()
                .filter(txn -> "SUCCESS".equals(txn.getStatus()))
                .collect(Collectors.groupingBy(
                        txn -> txn.getUserId(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l1 -> {
                                    if (l1.size() >= 3) {
                                        return l1.stream()
                                                .mapToDouble(t -> t.getUserId())
                                                .sum();
                                    } else {
                                        return null; // will remove later
                                    }
                                }
                        )
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));


    }

        /**
         * Given a string, return a new string with characters sorted in ascending order.
         *
         * @param word the string to be sorted
         * @return a new string with sorted characters
         */
    private static String sortCharacters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int sqrt = (int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
