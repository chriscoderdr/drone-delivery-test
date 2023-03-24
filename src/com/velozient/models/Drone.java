package com.velozient.models;

import java.util.ArrayList;
import java.util.List;

public class Drone {
    private String name;
    private int maxWeight;

    private List<Trip> trips;

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

    public List<Trip> getTrips() {
        return trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public int getRemainingCapacity() {
        int usedCapacity = 0;
        for (Trip trip : trips) {
            usedCapacity += trip.getTotalWeight();
        }
        return maxWeight - usedCapacity;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
