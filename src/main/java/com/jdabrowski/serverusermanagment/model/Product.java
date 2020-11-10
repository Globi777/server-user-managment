package com.jdabrowski.serverusermanagment.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private Long amount;

    public Product(){
    }

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
        this.amount = 1L;
    }

    public Product(String id, String name, Long amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }
}
