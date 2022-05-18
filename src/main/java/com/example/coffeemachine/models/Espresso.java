package com.example.coffeemachine.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "espresso_recipes")
public class Espresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "coffeecount")
    int coffeeCount;
    @Column(name = "watercount")
    int waterCount;

    public Espresso() {
    }

    public Espresso(int id, int coffeeCount, int waterCount) {
        this.id = id;
        this.coffeeCount = coffeeCount;
        this.waterCount = waterCount;
    }

}
