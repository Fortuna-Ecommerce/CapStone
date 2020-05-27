package com.capstone.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String type;

    @Column(precision = 5, scale = 2, nullable = false)
    private double price;

    @Column
    private String description;

    @Column
    private int onSpecial;
    //0 or number to discount item with - multiply it to get percentage

    @Column(columnDefinition = "int UNSIGNED default 1", nullable = false)
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImages> images;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<Question> questions;

//    @ManyToOne
//    private ShoppingCart shoppingCart;

    @ManyToMany
            (fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_genres",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private List<Genre> genres;

    public Product() {
    }

    public Product(String name, String color, String size, String type, double price, String description,
                   int onSpecial,
                   int quantity) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.type = type;
        this.price = price;
        this.description = description;
        this.onSpecial = onSpecial;
        this.quantity = quantity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOnSpecial() {
        return onSpecial;
    }

    public void setOnSpecial(int onSpecial) {
        this.onSpecial = onSpecial;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public List<ProductImages> getImages() {
        return images;
    }

    public void setImages(List<ProductImages> images) {
        this.images = images;
    }
//
//    public Transaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}

