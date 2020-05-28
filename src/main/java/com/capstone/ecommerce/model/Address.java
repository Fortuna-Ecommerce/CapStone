package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false)
        private String address_type;

        @Column(nullable = false, length = 75)
        private String first_name;

        @Column(nullable = false, length = 75)
        private String last_name;

        @Column(nullable = false, length = 75)
        private String street1;

        @Column(nullable = true, length = 75)
        private String street2;

        @Column(nullable = false, length = 40)
        private String city;

        @Column(nullable = false, length = 2)
        private String state;

        @Column(nullable = false, length = 5)
        private int zipCode;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        public Address() {
        }

        public Address(String address_type, String first_name, String last_name, String street1, String street2,
                       String city,
                       String state, int zipCode) {
                this.address_type = address_type;
                this.first_name = first_name;
                this.last_name = last_name;
                this.street1 = street1;
                this.street2 = street2;
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

        public String getAddress_type() {
                return address_type;
        }

        public void setAddress_type(String address_type) {
                this.address_type = address_type;
        }

        public String getStreet1() {
                return street1;
        }

        public void setStreet1(String street1) {
                this.street1 = street1;
        }

        public String getStreet2() {
                return street2;
        }

        public void setStreet2(String street2) {
                this.street2 = street2;
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

}

