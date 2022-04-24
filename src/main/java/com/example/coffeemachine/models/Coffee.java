package com.example.coffeemachine.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "coffee_types")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "espresso_id")
    Espresso espresso;
    @Column(name = "water_count")
    int waterCount;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "coffee_types_milk",
            joinColumns = {@JoinColumn(name = "coffee_id")},
            inverseJoinColumns = {@JoinColumn(name = "milk_id")})
    private Set<Milk> milks = new LinkedHashSet<>();

    public Coffee(){}
    public Coffee(int id, String name, Espresso espresso, int waterCount, Set<Milk> milks) {
        this.id = id;
        this.name = name;
        this.espresso = espresso;
        this.waterCount = waterCount;
        this.milks = milks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Espresso getEspresso() {
        return espresso;
    }

    public void setEspresso(Espresso espresso) {
        this.espresso = espresso;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }

    public Set<Milk> getMilks() {
        return milks;
    }

    public void setMilks(Set<Milk> milks) {
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
