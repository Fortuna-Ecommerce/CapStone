package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
public class UserAddresses {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String street;

        private int houseNumber;

        private String city;

        private int zipCode;

        @ManyToOne(fetch = FetchType.LAZY)
        private User user;

    }

