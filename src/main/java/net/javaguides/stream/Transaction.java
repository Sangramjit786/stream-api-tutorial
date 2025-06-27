package net.javaguides.stream;

import java.time.LocalDate;

public class Transaction {
    private String customerId;
    private String category;
    private double amount;
    private double weight;
    private LocalDate date;

    public Transaction(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Transaction(String customerId, double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public Transaction(String category, double amount, double weight) {
        this.category = category;
        this.amount = amount;
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
