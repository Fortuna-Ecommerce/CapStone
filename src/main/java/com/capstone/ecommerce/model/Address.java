package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, columnDefinition = "tinyint(1)")
        private Boolean type;//0 - shipping, 1 - billing

        @Column(nullable = false, length = 75)
        private String first_name;

        @Column(nullable = false, length = 75)
        private String last_name;

        @Column(nullable = false, length = 75)
        private String street;

        @Column(nullable = false, length = 12)
        private int houseNumber;

        @Column(nullable = false, length = 40)
        private String city;

        @Column(nullable = false, length = 2)
        private String state;

        @Column(nullable = false, length = 5)
        private int zipCode;

        @ManyToOne(fetch = FetchType.LAZY)
        private User user;

        @OneToOne
        private Transaction transaction;

        public Address() {
        }

        public Address(Boolean type, String first_name, String last_name, String street, int houseNumber, String city, String state, int zipCode) {
                this.type = type;
                this.first_name = first_name;
                this.last_name = last_name;
                this.street = street;
                this.houseNumber = houseNumber;
                this.city = city;
                this.state = state;
                this.zipCode = zipCode;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public Boolean getType() {
                return type;
        }

        public void setType(Boolean type) {
                this.type = type;
        }

        public String getFirst_name() {
                return first_name;
        }

        public void setFirst_name(String first_name) {
                this.first_name = first_name;
        }

        public String getLast_name() {
                return last_name;
        }

        public void setLast_name(String last_name) {
                this.last_name = last_name;
        }

        public String getStreet() {
                return street;
        }

        public void setStreet(String street) {
                this.street = street;
        }

        public int getHouseNumber() {
                return houseNumber;
        }

        public void setHouseNumber(int houseNumber) {
                this.houseNumber = houseNumber;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public int getZipCode() {
                return zipCode;
        }

        public void setZipCode(int zipCode) {
                this.zipCode = zipCode;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Transaction getTransaction() {
                return transaction;
        }

        public void setTransaction(Transaction transaction) {
                this.transaction = transaction;
        }
}

