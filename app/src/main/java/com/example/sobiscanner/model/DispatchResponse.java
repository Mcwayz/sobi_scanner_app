package com.example.sobiscanner.model;

import java.util.List;

public class DispatchResponse {
    private int product;
    private int inventory_id;
    private int product_quantity;
    private String product_last_update;
    private  String product_last_action;

    public DispatchResponse() {
    }

    public DispatchResponse(int product, int inventory_id, int product_quantity, String product_last_update, String product_last_action) {
        this.product = product;
        this.inventory_id = inventory_id;
        this.product_quantity = product_quantity;
        this.product_last_update = product_last_update;
        this.product_last_action = product_last_action;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_last_update() {
        return product_last_update;
    }

    public void setProduct_last_update(String product_last_update) {
        this.product_last_update = product_last_update;
    }

    public String getProduct_last_action() {
        return product_last_action;
    }

    public void setProduct_last_action(String product_last_action) {
        this.product_last_action = product_last_action;
    }

    @Override
    public String toString() {
        return "DispatchResponse{" +
                "product=" + product +
                ", inventory_id=" + inventory_id +
                ", product_quantity=" + product_quantity +
                ", product_last_update='" + product_last_update + '\'' +
                ", product_last_action='" + product_last_action + '\'' +
                '}';
    }
}
