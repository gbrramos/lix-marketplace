package com.gbrramos.lix.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="store")
public class Store {


    @GeneratedValue
    @Id
    private long id;
    private String name;
    private long ownerUserId;
    private String email;
    
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
    public long getOwnerUserId() {
        return ownerUserId;
    }
    public void setOwnerUserId(long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    } 

}
