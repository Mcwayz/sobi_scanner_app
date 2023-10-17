package com.example.sobiscanner.model;

public class ProductRequest {
    private String outer_product_case_barcode;

    public ProductRequest() {
    }


    public ProductRequest(String outer_product_case_barcode) {
        this.outer_product_case_barcode = outer_product_case_barcode;
    }


    public String getOuter_product_case_barcode() {
        return outer_product_case_barcode;
    }

    public void setOuter_product_case_barcode(String outer_product_case_barcode) {
        this.outer_product_case_barcode = outer_product_case_barcode;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "outer_product_case_barcode='" + outer_product_case_barcode + '\'' +
                '}';
    }
}
