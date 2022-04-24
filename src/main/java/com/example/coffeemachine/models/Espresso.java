package com.example.coffeemachine.models;

import javax.persistence.*;

@Entity
@Table(name = "espresso_recipes")
public class Espresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "coffeecount")
    private int coffeeCount;
    @Column(name = "watercount")
    private int waterCount;

    public Espresso(){}
    public Espresso(int id, int coffeeCount, int waterCount) {
        this.id = id;
        this.coffeeCount = coffeeCount;
        this.waterCount = waterCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoffeeCount() {
        return coffeeCount;
    }

    public void setCoffeeCount(int coffeeCount) {
        this.coffeeCount = coffeeCount;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }
}
