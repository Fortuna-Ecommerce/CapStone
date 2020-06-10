package com.capstone.ecommerce.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 6)
    private String color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition="Decimal(6,2)", nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "tinyint(1) default 0", name = "on_special")
    private Boolean special;


    @Column(columnDefinition = "int default 1", nullable = false)
    private long quantity;

    @Column
    private String productImage;

    @Column
    private String image;

    @ManyToMany
            (fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(name = "product_categories",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private List<Categories> categories;


    public Product() {
    }

    public Product(String name, String color, String size, String type, double price, String description,
                   Boolean special, Long quantity) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.type = type;
        this.price = price;
        this.description = description;
        this.special = special;
        this.quantity = quantity;
//        this.image = image;
    }

    public Product( String productImage, List<Categories> categories, String name, String color, String size,
                   String type, double price, String description, Boolean onSpecial, Long quantity) {
        this.productImage = productImage;
        this.categories = categories;
        this.name = name;
        this.color = color;
        this.size = size;
        this.type = type;
        this.price = price;
        this.description = description;
        this.special = onSpecial;
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

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    //
//    public Transaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }


    public List<Categories> getCategories() {
        return categories;
    }


    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Objects.equals(name, product.name) &&
                Objects.equals(color, product.color) &&
                Objects.equals(size, product.size) &&
                Objects.equals(type, product.type) &&
                Objects.equals(description, product.description) &&
                Objects.equals(special, product.special);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, size, type, price, description, special, quantity);
    }
}

