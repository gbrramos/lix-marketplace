package com.gbrramos.lix.models;

public class ProductDTO{

    String name;
    String description;
    Double price;
    Double specialPrice;
    String code;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getSpecialPrice() {
        return specialPrice;
    }
    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}
