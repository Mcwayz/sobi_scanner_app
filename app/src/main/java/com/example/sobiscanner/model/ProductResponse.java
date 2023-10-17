package com.example.sobiscanner.model;

public class ProductResponse {
    private int product_id;

    public ProductResponse() {
    }

    public ProductResponse(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "product_id=" + product_id +
                '}';
    }
}
