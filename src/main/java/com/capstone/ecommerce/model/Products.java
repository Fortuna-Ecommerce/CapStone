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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<ProductImages> images;


//    @Column
//    private List<ProductsCategories> categories;


}
