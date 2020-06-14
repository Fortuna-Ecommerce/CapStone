package com.capstone.ecommerce.model;

public class Item {
    private String description;
    private int quantity;
    private int priceperunit;
    private int subtotal;

    public Item(String description, int quantity, int priceperunit, int subtotal) {
        this.description = description;
        this.quantity = quantity;
        this.priceperunit = priceperunit;
        this.subtotal = subtotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(int priceperunit) {
        this.priceperunit = priceperunit;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }


}
