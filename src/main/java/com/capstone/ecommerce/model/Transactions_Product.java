package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
public class Transactions_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;


    long quantity;


    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
