package com.maider.shop.controllers.dto;

public class FilterDTO {
    private String type;
    private Integer sizeLessThan;
    private Integer sizeGreaterThan;
    private String material;
    private String brand;
    private Double priceLessThan;
    private Double priceGreaterThan;

    public FilterDTO(String type, Integer sizeLessThan, Integer sizeGreaterThan, String material, String brand, Double priceLessThan, Double priceGreaterThan) {
        this.type = type;
        this.sizeLessThan = sizeLessThan;
        this.sizeGreaterThan = sizeGreaterThan;
        this.material = material;
        this.brand = brand;
        this.priceLessThan = priceLessThan;
        this.priceGreaterThan = priceGreaterThan;
    }

    public String getType() {
        return type;
    }

    public Integer getSizeLessThan() {
        return sizeLessThan;
    }

    public Integer getSizeGreaterThan() {
        return sizeGreaterThan;
    }

    public String getMaterial() {
        return material;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPriceLessThan() {
        return priceLessThan;
    }

    public Double getPriceGreaterThan() {
        return priceGreaterThan;
    }
}
