package com.capstone.ecommerce.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double finalAmount;

    @Column(nullable = false)
    private String stripeTransID;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String transactionStatus;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private String created_at;

    @Column(nullable = true, columnDefinition = "DATETIME")
    private String modified_at;

    @Column(nullable = false)
    private double pre_tax_total;

    @Column(nullable = false)
    private double grand_total;

    public double getPre_tax_total() {
        return pre_tax_total;
    }

    public void setPre_tax_total(double pre_tax_total) {
        this.pre_tax_total = pre_tax_total;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<Product> product;


    public Order() {
    }

    public Order(String transactionType, String transactionStatus, String created_at,
                 String modified_at) {
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getStripeTransID() {
        return stripeTransID;
    }

    public void setStripeTransID(String stripeTransID) {
        this.stripeTransID = stripeTransID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<Product> getProduct() {
//        return product;
//    }
//
//    public void setProduct(List<Product> product) {
//        this.product = product;
//    }




}
