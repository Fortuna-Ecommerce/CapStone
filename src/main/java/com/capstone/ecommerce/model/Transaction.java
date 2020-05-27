package com.capstone.ecommerce.model;

import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions_details")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String transactionState;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private String dateTimeCreation;

    @Column(nullable = true, columnDefinition = "DATETIME")
    private String dateTimeModification;

    @Column(nullable = false)
    private String stripeTransToken;

    @OneToOne
    @JoinColumn(name = "final_purchase", referencedColumnName="id", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "ship_address", nullable = false)
    private Address ship_address;

    @OneToOne
    @JoinColumn(name = "bill_address", nullable = false)
    private Address bill_address;

    public Transaction() {
    }

    public Transaction(String transactionType, String transactionState, String dateTimeCreation,
                       String dateTimeModification, String stripeTransToken) {
        this.transactionType = transactionType;
        this.transactionState = transactionState;
        this.dateTimeCreation = dateTimeCreation;
        this.dateTimeModification = dateTimeModification;
        this.stripeTransToken = stripeTransToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }

    public String getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(String dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public String getDateTimeModification() {
        return dateTimeModification;
    }

    public void setDateTimeModification(String dateTimeModification) {
        this.dateTimeModification = dateTimeModification;
    }

    public String getStripeTransToken() {
        return stripeTransToken;
    }

    public void setStripeTransToken(String stripeTransToken) {
        this.stripeTransToken = stripeTransToken;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Address getShip_address() {
        return ship_address;
    }

    public void setShip_address(Address ship_address) {
        this.ship_address = ship_address;
    }

    public Address getBill_address() {
        return bill_address;
    }

    public void setBill_address(Address bill_address) {
        this.bill_address = bill_address;
    }
}
