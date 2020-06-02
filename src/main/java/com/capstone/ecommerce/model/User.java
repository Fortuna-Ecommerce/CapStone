package com.capstone.ecommerce.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Entity //Interacts with databases
@Table(name="users")//Creates Table in database
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userCache")
public class User {
    public static String getRoleAdmin() {
        return ROLE_ADMIN;
    }

    public static String getRoleUser() {
        return ROLE_USER;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 75)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String stripeToken;

    @Column(nullable = false)
    private String role;

    //Makes a new Table of Transactions which is based on columns from other tables (user), has a one to many relationship between user and transaction.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Transaction> transactions;

    //Declares when accessing addresses it distinguishes between billing and shipping and cascades each address respectively (if shipping address is changed, shipping address is updated)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Where(clause = "address_type = 'shipping'")
    private List<Address> ship_address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Where(clause = "address_type = 'billing'")
    private List<Address> bill_address;

    public User() {
    }

    //Creates the User object and specifies what the User object consists of
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //Creates a copy of the User object for user manipulation (update)
    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        role = copy.role;
    }





    // Getters and Setters for User Model


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

//    public Boolean getAdmin() {
//        return isAdmin;
//    }
//
//    public void setAdmin(Boolean admin) {
//        isAdmin = admin;
//    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Address> getShip_address() {
        return ship_address;
    }

    public void setShip_address(List<Address> ship_address) {
        this.ship_address = ship_address;
    }

    public List<Address> getBill_address() {
        return bill_address;
    }

    public void setBill_address(List<Address> bill_address) {
        this.bill_address = bill_address;
    }
}
