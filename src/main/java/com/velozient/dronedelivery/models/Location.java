package com.velozient.dronedelivery.models;

import java.util.Objects;

public class Location {
    private String name;
    private int weight;

    public Location(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return weight == location.weight && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
