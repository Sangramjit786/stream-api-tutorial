package net.javaguides.stream;

public class LineItem {

    private String productName;
    private String category;
    private double price;

    public LineItem(String productName, String category, double price) {
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public LineItem(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
