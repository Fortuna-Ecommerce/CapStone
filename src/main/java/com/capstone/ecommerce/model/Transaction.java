package com.capstone.ecommerce.model;

import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transactions_details")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateTimeCreation;

    @Column(nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime dateTimeModification;

    @Column(nullable = false)
    private String stripeTransToken;

    @OneToOne
    @JoinColumn(name = "final_purchase", referencedColumnName="transaction", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "ship_address", nullable = false)
    private ShipAddresses ship_address;

    @OneToOne
    @JoinColumn(name = "bill_address", nullable = false)
    private BillAddresses bill_address;

    public Transaction() {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public ShipAddresses getShip_address() {
        return ship_address;
    }

    public void setShip_address(ShipAddresses ship_address) {
        this.ship_address = ship_address;
    }

    public BillAddresses getBill_address() {
        return bill_address;
    }

    public void setBill_address(BillAddresses bill_address) {
        this.bill_address = bill_address;
    }
}
