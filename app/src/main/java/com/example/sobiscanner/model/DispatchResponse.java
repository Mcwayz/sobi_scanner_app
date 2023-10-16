package com.example.sobiscanner.model;

import java.util.List;

public class DispatchResponse {

    private List<Inventory> inventory;

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }
}
