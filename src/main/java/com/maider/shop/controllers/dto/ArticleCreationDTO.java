package com.maider.shop.controllers.dto;

public class ArticleCreationDTO {
    private String type;
    private String material;
    private String brand;
    private int size;
    private double price;

    public ArticleCreationDTO(String type, String material, String brand, int size, double price) {
        this.type = type;
        this.material = material;
        this.brand = brand;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
