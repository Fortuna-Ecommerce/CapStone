//package com.capstone.ecommerce.model;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="product_images")
//public class ProductImages {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "path")
//    private String path;
//
//    @Column(name="type")
//    private String type;
//
//
//
//    public ProductImages(){}
//
//    public ProductImages(long id, String name, String type, String path){
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.path = path;
//    }
//
//    public ProductImages(String name, String type, String path) {
//        this.name = name;
//        this.type = type;
//        this.path = path;
//    }
//
//    public long getId(){
//        return this.id;
//    }
//
//    public void setId(long id){
//        this.id = id;
//    }
//
//    public String getName(){
//        return this.name;
//    }
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public String getType(){
//        return this.type;
//    }
//
//    public void setType(String type){
//        this.type = type;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//}
