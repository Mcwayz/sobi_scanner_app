package com.example.sobiscanner.model;

public class DispatchRequest {
        private String outer_product_case_barcode;
        private String product_last_action;
        private int product_quantity;

        public DispatchRequest() {
        }

        public DispatchRequest(String outer_product_case_barcode, String product_last_action, int product_quantity) {
                this.outer_product_case_barcode = outer_product_case_barcode;
                this.product_last_action = product_last_action;
                this.product_quantity = product_quantity;
        }

        public String getOuter_product_case_barcode() {
                return outer_product_case_barcode;
        }

        public void setOuter_product_case_barcode(String outer_product_case_barcode) {
                this.outer_product_case_barcode = outer_product_case_barcode;
        }

        public String getProduct_last_action() {
                return product_last_action;
        }

        public void setProduct_last_action(String product_last_action) {
                this.product_last_action = product_last_action;
        }

        public int getProduct_quantity() {
                return product_quantity;
        }

        public void setProduct_quantity(int product_quantity) {
                this.product_quantity = product_quantity;
        }

        @Override
        public String toString() {
                return "DispatchRequest{" +
                        "outer_product_case_barcode='" + outer_product_case_barcode + '\'' +
                        ", product_last_action='" + product_last_action + '\'' +
                        ", product_quantity=" + product_quantity +
                        '}';
        }
}
