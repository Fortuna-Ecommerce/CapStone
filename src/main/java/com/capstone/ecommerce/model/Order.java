package com.capstone.ecommerce.model;

import java.util.Date;
import java.util.List;

public class Order {
    private String userName;
    private Date date;
    private int amountPaid;
    private int taxesPaid;
    private List<Item> items;

    public Order(String userName, Date date, int amountPaid, int taxesPaid, List<Item> items) {
        this.userName = userName;
        this.date = date;
        this.amountPaid = amountPaid;
        this.taxesPaid = taxesPaid;
        this.items = items;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getTaxesPaid() {
        return taxesPaid;
    }

    public void setTaxesPaid(int taxesPaid) {
        this.taxesPaid = taxesPaid;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
