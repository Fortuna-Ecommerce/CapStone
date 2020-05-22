package com.capstone.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(nullable = false)
    private char color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private String type;

    @Column(precision = 5, scale = 2)
    private float price;

    @Column
    private String description;

    @Column
    private int onSpecial;

    @Column(columnDefinition = "int UNSIGNED default 1", nullable = false)
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImages> images;

    @ManyToOne
    private Transaction transaction;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany
    @JoinColumn(name = "categories")
    private List<Tag> categories;

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

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Tag> getCategories() {
        return categories;
    }

    public void setCategories(List<Tag> categories) {
        this.categories = categories;
    }
}
