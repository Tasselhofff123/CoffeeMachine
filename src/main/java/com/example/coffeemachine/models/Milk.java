package com.example.coffeemachine.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "milk")
@Data
public class Milk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "count")
    int count;
    @Column(name = "whipped")
    boolean whipped;

    public Milk(){}
    public Milk(int id, int count, boolean whipped) {
        this.id = id;
        this.count = count;
        this.whipped = whipped;
    }
}
