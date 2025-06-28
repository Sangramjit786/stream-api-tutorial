package net.javaguides.stream;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int orderId;
    private List<LineItem> lineItems;
    private LocalDate orderDate;
    private double amount;

    public Order(LocalDate orderDate, double amount) {
        this.orderDate = orderDate;
        this.amount = amount;
    }

    public Order(int orderId, List<LineItem> lineItems) {
        this.orderId = orderId;
        this.lineItems = lineItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
