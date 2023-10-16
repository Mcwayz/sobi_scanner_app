package com.example.sobiscanner.model;

public class ScannerRequest {

    // Sending

    private int Section;
    private String product_name;
    private String product_desc;
    private String product_unit_barcode_no;
    private String outer_product_case_barcode;

    public ScannerRequest() {
    }

    public ScannerRequest(int section, String product_name, String product_desc, String product_unit_barcode_no, String outer_product_case_barcode) {
        Section = section;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_unit_barcode_no = product_unit_barcode_no;
        this.outer_product_case_barcode = outer_product_case_barcode;
    }

    public int getSection() {
        return Section;
    }

    public void setSection(int section) {
        Section = section;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_unit_barcode_no() {
        return product_unit_barcode_no;
    }

    public void setProduct_unit_barcode_no(String product_unit_barcode_no) {
        this.product_unit_barcode_no = product_unit_barcode_no;
    }

    public String getOuter_product_case_barcode() {
        return outer_product_case_barcode;
    }

    public void setOuter_product_case_barcode(String outer_product_case_barcode) {
        this.outer_product_case_barcode = outer_product_case_barcode;
    }

    @Override
    public String toString() {
        return "ScannerRequest{" +
                "Section=" + Section +
                ", product_name='" + product_name + '\'' +
                ", product_desc='" + product_desc + '\'' +
                ", product_unit_barcode_no='" + product_unit_barcode_no + '\'' +
                ", outer_product_case_barcode='" + outer_product_case_barcode + '\'' +
                '}';
    }
}
