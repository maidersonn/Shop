package com.maider.shop.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    private Long id;
    private String type;
    private int size;
    private String material;
    private String brand;
    private double price;
    public Article(Long id, String type, int size, String material, String brand, double price) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.material = material;
        this.brand = brand;
        this.price = price;
    }
    public Article() { }
    public Long getId() { return this.id; }
    public String getType() {
        return this.type;
    }
    public int getSize() {
        return size;
    }
    public String getMaterial() {
        return material;
    }
    public String getBrand() {
        return brand;
    }
    public double getPrice() {
        return price;
    }
}
