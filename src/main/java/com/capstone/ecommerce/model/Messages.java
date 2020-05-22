package com.capstone.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;
}
