package com.velozient.models;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    List<Location> locations;

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
