package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @JoinColumn(name = "product_id")
    private long productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private long userId;
}
