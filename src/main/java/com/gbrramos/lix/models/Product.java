package com.gbrramos.lix.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable{

    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String description;
    private Double price;
    private Double special_price;

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
    public Double getSpecial_price() {
        return special_price;
    }
    public void setSpecial_price(Double special_price) {
        this.special_price = special_price;
    }

}
