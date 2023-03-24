package com.velozient.dronedelivery.models;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    final List<Location> locations;

    public Trip() {
        locations = new ArrayList<>();
    }

    public Trip(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (Location location : locations) {
            totalWeight += location.getWeight();
        }
        return totalWeight;
    }
}
