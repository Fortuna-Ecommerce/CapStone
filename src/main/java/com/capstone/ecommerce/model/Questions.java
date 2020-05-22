package com.capstone.ecommerce.model;

import javax.persistence.*;
import java.security.PrivateKey;

@Entity
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String question;

    @Column
    private String answer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
