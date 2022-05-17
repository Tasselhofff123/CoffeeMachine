package com.example.coffeemachine.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "count")
    int count;
    @Column(name = "max_count")
    int maxCount;
    public Ingredient(){}

    public Ingredient(int id, String name, int count, int maxCount) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.maxCount = maxCount;
    }


}
