package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "billing_addresses")
public class BillAddresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 75)
    private String first_name;

    @Column(nullable = false, length = 75)
    private String last_name;

    @Column(nullable = false, unique = true, length = 75)
    private String street;

    @Column(nullable = false, unique = true, length = 12)
    private int houseNumber;

    @Column(nullable = false, unique = true, length = 40)
    private String city;

    @Column(nullable = false, unique = true, length = 5)
    private int zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    private Transaction transaction;

}
