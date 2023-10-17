package com.example.sobiscanner.model;

public class ProductRequest {
    private int outer_product_case_barcode;

    public ProductRequest() {
    }
    public ProductRequest(int outer_product_case_barcode) {
        this.outer_product_case_barcode = outer_product_case_barcode;
    }

    public int getOuter_product_case_barcode() {
        return outer_product_case_barcode;
    }

    public void setOuter_product_case_barcode(int outer_product_case_barcode) {
        this.outer_product_case_barcode = outer_product_case_barcode;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "outer_product_case_barcode=" + outer_product_case_barcode +
                '}';
    }
}
