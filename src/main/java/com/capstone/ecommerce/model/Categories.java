package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Boolean adult;

    @Column Boolean kid;

}
