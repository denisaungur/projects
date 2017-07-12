package com.shop.denisa.shop.domain;

import java.io.Serializable;

/**
 * Created by Denisa on 06.02.2017.
 */

public class ShoppingItem implements Serializable {
    private Integer id;
    private String name;
    private Integer quantity;
    private String status;

    public ShoppingItem(Integer id, String name, Integer quantity, String status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.status = status;
    }

    public ShoppingItem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return id + " " + name + " quantity:" + quantity + " status:" + status;
    }
}
