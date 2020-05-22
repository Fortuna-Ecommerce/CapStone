package com.capstone.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String size;

    @Column
    private float price;

    @Column
    private String description;

    @Column
    private int onSpecial;

    @Column(nullable = false)
    private long quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImages> images;

    @ManyToOne
    private Transactions transaction;

    @OneToMany
    private List<Reviews> reviews;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    //    @Column
//    private List<ProductsCategories> categories;


}
