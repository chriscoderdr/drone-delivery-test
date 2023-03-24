package com.velozient.models;

import java.util.ArrayList;
import java.util.List;

public class Drone {
    private String name;
    private int maxWeight;

    private List<Location> trips;

    public Drone(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.trips = new ArrayList<>();
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

    public List<Location> getTrips() {
        return trips;
    }

    public void addTrip(Location location) {
        trips.add(location);
    }

    public int getRemainingCapacity() {
        int totalWeight = 0;
        for (Location location : trips) {
            totalWeight += location.getWeight();
        }
        return maxWeight - totalWeight;
    }
}
