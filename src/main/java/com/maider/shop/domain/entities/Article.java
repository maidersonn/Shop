package com.maider.shop.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int size_;
    private String material;
    private String brand;
    private double price;
    public Article(String type, int size, String material, String brand, double price) {
        this.type = type;
        this.size_ = size;
        this.material = material;
        this.brand = brand;
        this.price = price;
    }
    public Article() { }
    public Long getId() { return this.id; }
    public String getType() {
        return this.type;
    }
    public int getSize_() {
        return size_;
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
