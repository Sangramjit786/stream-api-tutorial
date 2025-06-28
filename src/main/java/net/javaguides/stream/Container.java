package net.javaguides.stream;

import java.util.List;

public class Container {
    String id;
    List<Integer> numbers;

    public Container(String id, List<Integer> numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
