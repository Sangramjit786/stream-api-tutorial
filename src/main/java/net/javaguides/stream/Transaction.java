package net.javaguides.stream;

public class Transaction {
    private String category;
    private double amount;
    private double weight;

    public Transaction(String category, double amount, double weight) {
        this.category = category;
        this.amount = amount;
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
