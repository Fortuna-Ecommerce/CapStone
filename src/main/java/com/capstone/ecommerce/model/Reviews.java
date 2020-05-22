package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int rating;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private long user_id;

    @OneToOne
    @JoinColumn (name = "product_id")
    private Products product;


}
