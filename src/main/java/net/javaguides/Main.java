package net.javaguides;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> System.out.println("Hello"));

        MathOperation sumOperation = (a, b) ->  a + b;
        MathOperation subtractOperation = (a, b) -> a - b;

        int res = sumOperation.operate(1,2);
        System.out.println(res);

        // Predicate -> functional interface (Boolean valued function)
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4));
        Predicate<String> isWordStartingWithA = x -> x.toLowerCase().startsWith("a");
        Predicate<String> isWordEndingWithT = x -> x.toLowerCase().endsWith("t");
        Predicate<String> and = isWordStartingWithA.and(isWordEndingWithT);
        System.out.println(and.test("Akshay"));

        // Function --> work for you
        Function<Integer, Integer> doubleIt = x -> 2 * x;
        Function<Integer, Integer> tripleIt = x -> 3 * x;

        System.out.println(doubleIt.andThen(tripleIt).apply(20));
        System.out.println(tripleIt.andThen(doubleIt).apply(20)); //Same Output
        System.out.println(doubleIt.compose(tripleIt).apply(20)); //Same Output
        System.out.println(doubleIt.apply(100));

        Function<Integer, Integer> identity = Function.identity();
        Integer res2 = identity.apply(5);
        System.out.println(res2);

        //Consumer
        Consumer<Integer> print = x -> System.out.println(x);
        print.accept(51);

        List<Integer> list = Arrays.asList(1, 2, 3);
        Consumer<List<Integer>> printList = x -> {
            for(int i : x) {
                System.out.println(i);
            }
        };

        printList.accept(list);

        // Supplier
        Supplier<String> givenHelloWorld = () -> "Hello World";
        System.out.println(givenHelloWorld.get());

        //Combined Example
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer = x -> System.out.println(x);
        Supplier<Integer> supplier = () -> 100;

        if(predicate.test(supplier.get())) {
            consumer.accept(function.apply(supplier.get()));
        }

        // BiPredicate, BiConsumer, BiFunction

        BiPredicate<Integer, Integer> isSumEven = (x, y) -> (x + y) % 2 == 0;
        System.out.println(isSumEven.test(5, 5));
        BiConsumer<Integer, String> biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };
        biConsumer.accept(4,"5");

        BiFunction<String, String, Integer> biFunction = (x, y) -> (x + y).length();
        System.out.println(biFunction.apply("a", "bc"));

        // Unary Operator, BinaryOperator
        //Function<Integer, Integer> a = x -> x * x;
        UnaryOperator<Integer> a = x -> 2 * x;
        System.out.println(a.apply(50));

        BinaryOperator<Integer> b = (x, y) -> x + y;
        System.out.println(b.apply(6, 7));

    }
}

/*class Sumoperation implements MathOperation {

    @Override
    public int operate(int a, int b) {
        return a + b;
    }
}*/

interface MathOperation {
    int operate(int a, int b);
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello");
    }
}