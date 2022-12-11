package com.gbrramos.lix.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sale")
public class Sale {

    @GeneratedValue
    @Id
    private long id;
    private long storeId;
    private long userId;
    private ArrayList<Product> lProducts;
    private String price;
    private long status;
    private LocalDateTime timestamp;

    public Sale () {
        this.timestamp = LocalDateTime.now();
    }
    
}
