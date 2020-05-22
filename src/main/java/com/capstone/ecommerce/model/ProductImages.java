package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "ProductImages")
public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Products product;


}
