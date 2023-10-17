package com.example.sobiscanner.model;

public class ScannerRequest {

    // Sending
    private int product;
    private int product_quantity;
    private String product_last_action;

    public ScannerRequest() {
    }

    public ScannerRequest(int product, int product_quantity, String product_last_action) {
        this.product = product;
        this.product_quantity = product_quantity;
        this.product_last_action = product_last_action;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_last_action() {
        return product_last_action;
    }

    public void setProduct_last_action(String product_last_action) {
        this.product_last_action = product_last_action;
    }

    @Override
    public String toString() {
        return "ScannerRequest{" +
                "product=" + product +
                ", product_quantity=" + product_quantity +
                ", product_last_action='" + product_last_action + '\'' +
                '}';
    }
}
