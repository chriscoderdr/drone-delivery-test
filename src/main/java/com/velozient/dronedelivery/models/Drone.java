package com.velozient.dronedelivery.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Drone {
    private String name;
    private int maxWeightCapacity;

    private List<Trip> trips;

    public Drone(String name, int maxWeightCapacity) {
        this.name = name;
        this.maxWeightCapacity = maxWeightCapacity;
        this.trips = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public void setMaxWeightCapacity(int maxWeightCapacity) {
        this.maxWeightCapacity = maxWeightCapacity;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }

    public Trip getCurrentTrip() {
        if (trips.isEmpty()) {
            trips.add(new Trip());
        }
        return trips.get(trips.size() - 1);
    }

    public int getCurrentCapacity() {
        return getMaxWeightCapacity() - getCurrentTrip().getTotalWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return maxWeightCapacity == drone.maxWeightCapacity && name.equals(drone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxWeightCapacity);
    }
}
