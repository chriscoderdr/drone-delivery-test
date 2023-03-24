package com.velozient.controller;

import com.velozient.models.Drone;
import com.velozient.models.Location;
import com.velozient.models.Trip;

import java.util.*;

public class DeliveryPlanner {
    public void assignDeliveries(List<Drone> drones, List<Location> locations) {
        // loop through the drones and assign locations
        for (Drone drone : drones) {
            while (!locations.isEmpty()) {
                int remainingCapacity = drone.getRemainingCapacity();
                List<Location> tripLocations = new ArrayList<>();
                int totalWeight = 0;

                // group the remaining locations into a trip that does not exceed the drone's capacity
                for (Location location : locations) {
                    if (location.getWeight() <= remainingCapacity) {
                        tripLocations.add(location);
                        totalWeight += location.getWeight();
                        remainingCapacity -= location.getWeight();
                    }
                }

                if (!tripLocations.isEmpty()) {
                    // remove the assigned locations from the list
                    locations.removeAll(tripLocations);
                    drone.addTrip(new Trip(tripLocations));
                } else {
                    break; // no more locations can be assigned to this drone
                }
            }
        }
    }
}
