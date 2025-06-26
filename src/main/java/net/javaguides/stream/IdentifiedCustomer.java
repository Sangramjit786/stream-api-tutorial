package net.javaguides.stream;

public class IdentifiedCustomer {

    private int id;
    private String name;

    public IdentifiedCustomer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IdentifiedCustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
