package com.velozient.models;

public class Drone {
    private String name;
    private int maxWeight;

    public Drone(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
