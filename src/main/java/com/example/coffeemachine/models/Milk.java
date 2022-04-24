package com.example.coffeemachine.models;

import javax.persistence.*;

@Entity
@Table(name = "milk")
public class Milk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "count")
    private int count;
    @Column(name = "whipped")
    private boolean whipped;

    public Milk(){}
    public Milk(int id, int count, boolean whipped) {
        this.id = id;
        this.count = count;
        this.whipped = whipped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isWhipped() {
        return whipped;
    }

    public void setWhipped(boolean whipped) {
        this.whipped = whipped;
    }
}
