package com.gbrramos.lix.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product{

    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String description;
    private Double price;
    private Double specialPrice;
    private String code;

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
