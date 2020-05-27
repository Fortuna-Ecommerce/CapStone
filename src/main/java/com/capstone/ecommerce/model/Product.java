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

    @Column(nullable = false, length = 7)
    private String color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String type;

    @Column(precision = 5, scale = 2, nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean onSpecial;

    @Column(columnDefinition = "int UNSIGNED default 1", nullable = false)
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImages> images;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<Question> questions;


    @ManyToMany
            (fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_categories",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private List<Categories> categories;


    public Product() {
    }

    public Product(String name, String color, String size, String type, double price, String description,
                   Boolean onSpecial,
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

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOnSpecial() {
        return onSpecial;
    }

    public void setOnSpecial(Boolean onSpecial) {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}

