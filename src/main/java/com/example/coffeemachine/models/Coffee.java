package com.example.coffeemachine.models;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "coffee_types")
@Data
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "espresso_id")
    Espresso espresso;
    @Column(name = "water_count")
    int waterCount;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "coffee_types_milk",
            joinColumns = {@JoinColumn(name = "coffee_id")},
            inverseJoinColumns = {@JoinColumn(name = "milk_id")})
    Set<Milk> milks = new LinkedHashSet<>();

    public Coffee(){}
    public Coffee(int id, String name, Espresso espresso, int waterCount, Set<Milk> milks) {
        this.id = id;
        this.name = name;
        this.espresso = espresso;
        this.waterCount = waterCount;
        this.milks = milks;
    }

    public int getSumCoffeeCount(){
        return espresso.getCoffeeCount();
    }

    public int getSumWaterCount(){
        return espresso.getWaterCount() + getWaterCount();
    }
    
    public int getSumMilkCount(){
        int sumMilkCount = 0;
        for(Milk milk : milks){
            sumMilkCount += milk.getCount();
        }
        return sumMilkCount;
    }
}
